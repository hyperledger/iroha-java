//! Smartcontract which creates new nft for every user
//!
//! This module isn't included in the build-tree,
//! but instead it is being built by a `client/build.rs`
#![no_std]

extern crate alloc;
#[cfg(not(test))]
extern crate panic_halt;

use alloc::{format, string::ToString, vec::Vec};
use lol_alloc::{FreeListAllocator, LockedAllocator};

#[global_allocator]
static ALLOC: LockedAllocator<FreeListAllocator> = LockedAllocator::new(FreeListAllocator::new());

use iroha_trigger::{data_model::prelude::*, debug::DebugUnwrapExt, smart_contract::QueryHost};

#[iroha_trigger::main]
fn smartcontract_entry_point(_id: TriggerId, _owner: AccountId, _event: Event) {
    let account_id: AccountId = "alice@wonderland".parse().unwrap();
    let limits = MetadataLimits::new(256, 256);
    let mut metadata = Metadata::new();

    let name = format!(
        "nft_for_{}_in_{}",
        account_id.name(),
        account_id.domain_id()
    )
    .parse()
    .dbg_unwrap();
    metadata
        .insert_with_limits(name, true.into(), limits)
        .dbg_unwrap();

    let nft_id = generate_new_nft_id(&account_id);
    let nft_definition = AssetDefinition::store(nft_id.clone())
        .mintable_once()
        .with_metadata(metadata);

    RegisterExpr::new(nft_definition).execute().dbg_unwrap();
    SetKeyValueExpr::new(
        AssetId::new(nft_id, account_id),
        "has_this_nft".parse::<Name>().dbg_unwrap(),
        Value::Bool(true),
    )
    .execute()
    .dbg_unwrap();
}

fn generate_new_nft_id(account_id: &AccountId) -> AssetDefinitionId {
    let query = FindAssetsByAccountId::new(account_id.clone());
    let assets: Vec<Asset> = query.execute().dbg_unwrap();

    let new_number = assets
        .into_iter()
        .filter(|asset| asset.id().definition_id().to_string().starts_with("nft_"))
        .count()
        .checked_add(1)
        .dbg_unwrap();

    format!(
        "nft_number_{}_for_{}#{}",
        new_number,
        account_id.name(),
        account_id.domain_id()
    )
    .parse()
    .dbg_unwrap()
}
