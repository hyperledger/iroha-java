package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.asValue
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.testcontainers.genesis.Genesis
import jp.co.soramitsu.iroha2.toIrohaPublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

/**
 * Default genesis where just one domain and one user Alice in it
 */
open class DefaultGenesis : Genesis(rawGenesisBlock())

/**
 * Default genesis plus Alice has 100 XOR and permission to burn
 */
open class AliceHas100XorAndPermissionToBurn : DefaultGenesis() {
    override val genesisBlock: RawGenesisBlock = super.genesisBlock.apply {
        val transaction =
            this.transactions.firstOrNull() ?: GenesisTransaction(mutableListOf()).also { this.transactions.add(it) }
        transaction.isi.addAll(
            listOf(
                Instructions.registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity()),
                Instructions.mintAsset(DEFAULT_ASSET_ID, 100U),
                Instructions.grantBurnAssetWithDefinitionId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
            )
        )
    }
}

open class StoreAssetWithMetadata : DefaultGenesis() {
    companion object {
        const val ASSET_KEY = "key"
        val ASSET_VALUE = "value".asValue()
        val DEFINITION_ID = DefinitionId("foo", DEFAULT_DOMAIN_NAME)
        val ASSET_ID = AssetId(DEFINITION_ID, ALICE_ACCOUNT_ID)
    }

    override val genesisBlock = super.genesisBlock.plus(
        Instructions.registerAsset(
            DEFINITION_ID,
            AssetValueType.Store(),
            Metadata(mutableMapOf(ASSET_KEY to ASSET_VALUE))
        ),
        Instructions.storeAsset(ASSET_ID, ASSET_KEY, ASSET_VALUE)
    )
}

open class XorAndValAssets : DefaultGenesis() {
    companion object {
        const val XOR_QUANTITY = 1U
        const val VAL_QUANTITY = 1U
        val XOR_DEFINITION_ID = DefinitionId("xor", DEFAULT_DOMAIN_NAME)
        val VAL_DEFINITION_ID = DefinitionId("val", DEFAULT_DOMAIN_NAME)
    }

    override val genesisBlock = super.genesisBlock.plus(
        Instructions.registerAsset(XOR_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(XOR_DEFINITION_ID, ALICE_ACCOUNT_ID), XOR_QUANTITY),

        Instructions.registerAsset(VAL_DEFINITION_ID, AssetValueType.Quantity()),
        Instructions.mintAsset(AssetId(VAL_DEFINITION_ID, ALICE_ACCOUNT_ID), VAL_QUANTITY)
    )
}

open class NewAccountWithMetadata : DefaultGenesis() {
    companion object {
        const val ACCOUNT_NAME = "foo"
        const val KEY = "key"

        val VALUE = "value".asValue()
        val ACCOUNT_ID = AccountId(ACCOUNT_NAME, DEFAULT_DOMAIN_NAME)
        val KEYPAIR = generateKeyPair()
    }

    override val genesisBlock = super.genesisBlock.plus(
        Instructions.registerAccount(
            id = ACCOUNT_ID,
            signatories = mutableListOf(KEYPAIR.public.toIrohaPublicKey()),
            metadata = Metadata(mutableMapOf(KEY to VALUE))
        )
    )
}

open class NewDomain : DefaultGenesis() {
    companion object {
        const val DOMAIN_NAME = "foo_domain"
    }

    override val genesisBlock: RawGenesisBlock = super.genesisBlock.plus(
        Instructions.registerDomain(DOMAIN_NAME, mutableMapOf(), mutableMapOf())
    )
}

fun rawGenesisBlock(): RawGenesisBlock {
    return RawGenesisBlock(
        mutableListOf(
            GenesisTransaction(
                mutableListOf(
                    Instructions.registerDomain(DEFAULT_DOMAIN_NAME, mutableMapOf(), mutableMapOf()),
                    Instructions.registerAccount(
                        ALICE_ACCOUNT_ID,
                        mutableListOf(ALICE_KEYPAIR.public.toIrohaPublicKey()),
                    ),
                )
            )
        )
    )
}

fun RawGenesisBlock.plus(vararg instructions: Instruction): RawGenesisBlock {
    // get or create genesis transaction
    val genesisTransaction = when (transactions.isEmpty()) {
        true -> GenesisTransaction(mutableListOf()).apply { transactions.add(this) }
        false -> transactions.first()
    }
    genesisTransaction.isi.addAll(instructions)
    return this
}
