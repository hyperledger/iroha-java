//! Runtime Executor for client tests
#![no_std]

extern crate alloc;
#[cfg(not(test))]
extern crate panic_halt;

use iroha_executor::{prelude::*, DataModelBuilder};
use dlmalloc::GlobalDlmalloc;

#[global_allocator]
static ALLOC: GlobalDlmalloc = GlobalDlmalloc;

/// Executor that replaces some of [`Validate`]'s methods with sensible defaults
///
/// # Warning
///
/// The defaults are not guaranteed to be stable.
#[derive(Debug, Clone, Constructor, Visit, Validate, ValidateEntrypoints)]
pub struct Executor {
    verdict: Result,
    block_height: u64,
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
pub fn migrate(_block_height: u64) {
    DataModelBuilder::with_default_permissions().build_and_set();
}
