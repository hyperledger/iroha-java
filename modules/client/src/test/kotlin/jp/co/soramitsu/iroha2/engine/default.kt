package jp.co.soramitsu.iroha2.engine

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.testcontainers.genesis.Genesis
import jp.co.soramitsu.iroha2.toIrohaPublicKey

fun defaultGenesis(): Genesis {
    return Genesis(rawGenesisBlock())
}

fun rawGenesisBlock(): RawGenesisBlock {
    return RawGenesisBlock(
        mutableListOf(
            GenesisTransaction(
                mutableListOf(
                    Instructions.registerDomain("wonderland", mutableMapOf(), mutableMapOf()),
                    Instructions.registerAccount(
                        ALICE_ACCOUNT_ID,
                        mutableListOf(ALICE_KEYPAIR.public.toIrohaPublicKey()),
                    ),
                    Instructions.registerAsset(
                        DefinitionId("val", "wonderland"),
                        AssetValueType.Quantity()
                    )
                )
            )
        )
    )
}
