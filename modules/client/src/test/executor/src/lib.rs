//! Runtime Executor for client tests
#![no_std]

extern crate alloc;
#[cfg(not(test))]
extern crate panic_halt;

use iroha_executor::{default::default_permission_token_schema, prelude::*, smart_contract};
use lol_alloc::{FreeListAllocator, LockedAllocator};

#[global_allocator]
static ALLOC: LockedAllocator<FreeListAllocator> = LockedAllocator::new(FreeListAllocator::new());

/// Executor that replaces some of [`Validate`]'s methods with sensible defaults
///
/// # Warning
///
/// The defaults are not guaranteed to be stable.
#[derive(Clone, Constructor, Debug, ValidateEntrypoints, ExpressionEvaluator, Validate, Visit)]
pub struct Executor {
    verdict: Result,
    block_height: u64,
    host: smart_contract::Host,
}

/// Migrate previous executor to the current version.
/// Called by Iroha once just before upgrading executor.
///
/// # Errors
///
/// Concrete errors are specific to the implementation.
///
/// If `migrate()` entrypoint fails then the whole `Upgrade` instruction
/// will be denied and previous executor will stay unchanged.
#[entrypoint]
pub fn migrate(_block_height: u64) -> MigrationResult {
    let schema = default_permission_token_schema();
    let (token_ids, schema_str) = schema.serialize();

    iroha_executor::set_permission_token_schema(
        &iroha_executor::data_model::permission::PermissionTokenSchema::new(token_ids, schema_str),
    );

    Ok(())
}
