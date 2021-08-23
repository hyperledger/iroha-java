package jp.co.soramitsu.iroha2.testcontainers

import jp.co.soramitsu.iroha2.Instructions
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.genesis.GenesisTransaction
import jp.co.soramitsu.iroha2.generated.genesis.RawGenesisBlock
import jp.co.soramitsu.iroha2.generateKeyPair
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.toIrohaPublicKey

val DEFAULT_KEYPAIR = generateKeyPair()
val DEFAULT_ACCOUNT_ID = Id("alice", "wonderland")

fun defaultGenesis(): Genesis {
    return Genesis(
        RawGenesisBlock(
            mutableListOf(
                GenesisTransaction(
                    mutableListOf(
                        Instructions.registerDomain("wonderland", mutableMapOf(), mutableMapOf()),
                        Instructions.registerAccount(
                            DEFAULT_ACCOUNT_ID,
                            mutableListOf(DEFAULT_KEYPAIR.public.toIrohaPublicKey()),
                        ),
                        Instructions.registerAsset(
                            DefinitionId("val","wonderland"),
                            AssetValueType.Quantity()
                        )
                    )
                )
            )
        )
    )
}
