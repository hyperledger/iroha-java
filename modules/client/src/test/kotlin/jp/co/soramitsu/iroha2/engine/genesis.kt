package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.testcontainers.genesis.Genesis
import jp.co.soramitsu.iroha2.toIrohaPublicKey

/**
 * Default genesis where just one domain and one user Alice in it
 */
open class DefaultGenesis : Genesis(rawGenesisBlock())

/**
 * Default genesis plus Alice has 100 XOR and permission to burn
 */
open class AliceHas100XorAndPermissionToBurn : DefaultGenesis() {
    override val genesisBlock: RawGenesisBlock = super.genesisBlock.apply {
        val transaction = this.transactions.firstOrNull() ?: GenesisTransaction(mutableListOf()).also { this.transactions.add(it) }
        transaction.isi.addAll(
            listOf(
                Instructions.registerAsset(DEFAULT_ASSET_DEFINITION_ID, AssetValueType.Quantity()),
                Instructions.mintAsset(DEFAULT_ASSET_ID, 100U),
                Instructions.grantBurnAssetWithDefinitionId(DEFAULT_ASSET_DEFINITION_ID, ALICE_ACCOUNT_ID)
            )
        )
    }
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
