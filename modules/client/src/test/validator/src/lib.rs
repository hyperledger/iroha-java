//! Runtime Validator for client tests

#![no_std]

extern crate alloc;

use alloc::string::String;

use iroha_schema::IntoSchema;
use iroha_validator::{
    data_model::evaluate::{EvaluationError, ExpressionEvaluator},
    default,
    permission::Token as _,
    prelude::*,
};
use parity_scale_codec::{Decode, Encode};
use serde::{Deserialize, Serialize};

#[cfg(not(test))]
extern crate panic_halt;

use alloc::format;

mod token {
    //! Module with custom token.

    use super::*;

    /// Token to identify if user can register domains.
    #[derive(Token, ValidateGrantRevoke, Decode, Encode, IntoSchema, Serialize, Deserialize)]
    #[validate(iroha_validator::permission::OnlyGenesis)]
    pub struct CanRegisterDomains;
}

struct CustomValidator(DefaultValidator);

macro_rules! delegate {
    ( $($visitor:ident$(<$bound:ident>)?($operation:ty)),+ $(,)? ) => { $(
        fn $visitor $(<$bound>)?(&mut self, authority: &AccountId, operation: $operation) {
            self.0.$visitor(authority, operation);
        } )+
    }
}

impl Visit for CustomValidator {
    fn visit_transaction(
        &mut self,
        authority: &AccountId,
        transaction: &VersionedSignedTransaction,
    ) {
        default::visit_transaction(self, authority, transaction);
    }

    fn visit_instruction(&mut self, authority: &AccountId, isi: &InstructionBox) {
        default::visit_instruction(self, authority, isi);
    }

    fn visit_expression<V>(&mut self, authority: &AccountId, isi: &EvaluatesTo<V>) {
        default::visit_expression(self, authority, isi)
    }

    fn visit_sequence(&mut self, authority: &AccountId, isi: &SequenceBox) {
        default::visit_sequence(self, authority, isi)
    }

    fn visit_if(&mut self, authority: &AccountId, isi: &Conditional) {
        default::visit_if(self, authority, isi)
    }

    fn visit_pair(&mut self, authority: &AccountId, isi: &Pair) {
        default::visit_pair(self, authority, isi)
    }

    fn visit_grant_account_permission(
        &mut self,
        authority: &AccountId,
        isi: Grant<Account, PermissionToken>,
    ) {
        let token = isi.object.clone();

        if self.block_height() == 0 {
            // FIXME: default validator doesn't allow genesis to grant tokens to other accounts
            pass!(self);
        }

        if let Ok(token) = token::CanRegisterDomains::try_from(token) {
            if let Err(error) = iroha_validator::permission::ValidateGrantRevoke::validate_grant(
                &token,
                authority,
                self.block_height(),
            ) {
                deny!(self, error);
            }

            pass!(self);
        } else {
            default::permission_token::visit_grant_account_permission(self, authority, isi);
        }
    }

    fn visit_register_domain(&mut self, authority: &AccountId, _isi: Register<Domain>) {
        // FIXME: default validator doesn't allow genesis to grant tokens to other accounts
        if self.block_height() == 0 || token::CanRegisterDomains.is_owned_by(authority) {
            pass!(self);
        }

        deny!(self, "You don't have permission to register a new domain");
    }

    delegate! {
        // Peer validation
        visit_unregister_peer(Unregister<Peer>),

        // Domain validation
        visit_unregister_domain(Unregister<Domain>),
        visit_set_domain_key_value(SetKeyValue<Domain>),
        visit_remove_domain_key_value(RemoveKeyValue<Domain>),

        // Account validation
        visit_unregister_account(Unregister<Account>),
        visit_mint_account_public_key(Mint<Account, PublicKey>),
        visit_burn_account_public_key(Burn<Account, PublicKey>),
        visit_mint_account_signature_check_condition(Mint<Account, SignatureCheckCondition>),
        visit_set_account_key_value(SetKeyValue<Account>),
        visit_remove_account_key_value(RemoveKeyValue<Account>),

        // Asset validation
        visit_register_asset(Register<Asset>),
        visit_unregister_asset(Unregister<Asset>),
        visit_mint_asset(Mint<Asset, NumericValue>),
        visit_burn_asset(Burn<Asset, NumericValue>),
        visit_transfer_asset(Transfer<Asset, NumericValue, Account>),
        visit_set_asset_key_value(SetKeyValue<Asset>),
        visit_remove_asset_key_value(RemoveKeyValue<Asset>),

        // AssetDefinition validation
        visit_unregister_asset_definition(Unregister<AssetDefinition>),
        visit_transfer_asset_definition(Transfer<Account, AssetDefinition, Account>),
        visit_set_asset_definition_key_value(SetKeyValue<AssetDefinition>),
        visit_remove_asset_definition_key_value(RemoveKeyValue<AssetDefinition>),

        // Permission validation
        visit_revoke_account_permission(Revoke<Account, PermissionToken>),

        // Role validation
        visit_register_role(Register<Role>),
        visit_unregister_role(Unregister<Role>),
        visit_grant_account_role(Grant<Account, RoleId>),
        visit_revoke_account_role(Revoke<Account, RoleId>),

        // Trigger validation
        visit_unregister_trigger(Unregister<Trigger<TriggeringFilterBox, Executable>>),
        visit_mint_trigger_repetitions(Mint<Trigger<TriggeringFilterBox, Executable>, u32>),
        visit_execute_trigger(ExecuteTrigger),

        // Parameter validation
        visit_set_parameter(SetParameter),
        visit_new_parameter(NewParameter),

        // Upgrade validation
        visit_upgrade_validator(Upgrade<iroha_validator::data_model::validator::Validator>),
    }
}

impl Validate for CustomValidator {
    /// Migration should be applied on blockchain with [`DefaultValidator`]
    fn migrate(_block_height: u64) -> MigrationResult {
        let mut schema = DefaultValidator::permission_token_schema();
        schema.insert::<token::CanRegisterDomains>();

        let (token_ids, schema_str) = schema.serialize();
        iroha_validator::iroha_wasm::set_permission_token_schema(
            &iroha_validator::data_model::permission::PermissionTokenSchema::new(
                token_ids, schema_str,
            ),
        );

        Ok(())
    }

    fn verdict(&self) -> &Result {
        self.0.verdict()
    }

    fn block_height(&self) -> u64 {
        self.0.block_height()
    }

    fn deny(&mut self, reason: ValidationFail) {
        self.0.deny(reason);
    }
}

impl ExpressionEvaluator for CustomValidator {
    fn evaluate<E: Evaluate>(
        &self,
        expression: &E,
    ) -> core::result::Result<E::Value, EvaluationError> {
        self.0.evaluate(expression)
    }
}

#[entrypoint]
pub fn migrate(block_height: u64) -> MigrationResult {
    CustomValidator::migrate(block_height)
}

#[entrypoint]
pub fn validate_transaction(
    authority: AccountId,
    transaction: VersionedSignedTransaction,
    block_height: u64,
) -> Result {
    let mut validator = CustomValidator(DefaultValidator::new(block_height));
    validator.visit_transaction(&authority, &transaction);
    validator.0.verdict
}

#[entrypoint]
pub fn validate_instruction(
    authority: AccountId,
    instruction: InstructionBox,
    block_height: u64,
) -> Result {
    let mut validator = CustomValidator(DefaultValidator::new(block_height));
    validator.visit_instruction(&authority, &instruction);
    validator.0.verdict
}

#[entrypoint]
pub fn validate_query(authority: AccountId, query: QueryBox, block_height: u64) -> Result {
    let mut validator = CustomValidator(DefaultValidator::new(block_height));
    validator.visit_query(&authority, &query);
    validator.0.verdict
}
