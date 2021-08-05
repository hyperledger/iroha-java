package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Asset
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValue
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId
import jp.co.soramitsu.iroha2.generated.datamodel.asset.Id as AssetId

object Instructions {

    fun registerAccount(
        name: String,
        domainName: String,
        signatories: MutableList<PublicKey>,
        metadata: Metadata = Metadata(mutableMapOf())
    ): Instruction {
        return registerAccount(AccountId(name, domainName), signatories, metadata)
    }

    fun registerAccount(
        id: AccountId,
        signatories: MutableList<PublicKey>,
        metadata: Metadata = Metadata(mutableMapOf())
    ): Instruction {
        return registerSome {
            IdentifiableBox.NewAccount(
                NewAccount(id, signatories, metadata)
            )
        }
    }

    fun registerAsset(
        id: DefinitionId,
        assetValueType: AssetValueType
    ): Instruction {
        return registerSome {
            IdentifiableBox.AssetDefinition(
                AssetDefinition(assetValueType, id)
            )
        }
    }

    fun registerAsset(
        assetName: String,
        domainName: String,
        assetValueType: AssetValueType
    ): Instruction {
        return registerAsset(
            DefinitionId(assetName, domainName),
            assetValueType
        )
    }
    private inline fun registerSome(idBox: () -> IdentifiableBox): Instruction {
        return Instruction.Register(
            RegisterBox(
                EvaluatesTo(
                    Expression.Raw(
                        Value.Identifiable(idBox())
                    )
                )
            )
        )
    }

    fun registerDomain(
        domainName: String,
        accounts: MutableMap<AccountId, Account> = mutableMapOf(),
        assetDefinitions: MutableMap<DefinitionId, AssetDefinitionEntry> = mutableMapOf()
    ): Instruction {
        return registerSome {
            IdentifiableBox.Domain(
                Domain(domainName, accounts, assetDefinitions)
            )
        }
    }
}
