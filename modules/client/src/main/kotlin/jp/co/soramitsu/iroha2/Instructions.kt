package jp.co.soramitsu.iroha2

import jp.co.soramitsu.iroha2.generated.crypto.PublicKey
import jp.co.soramitsu.iroha2.generated.datamodel.IdBox
import jp.co.soramitsu.iroha2.generated.datamodel.IdentifiableBox
import jp.co.soramitsu.iroha2.generated.datamodel.Value
import jp.co.soramitsu.iroha2.generated.datamodel.account.Account
import jp.co.soramitsu.iroha2.generated.datamodel.account.NewAccount
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinition
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.asset.AssetValueType
import jp.co.soramitsu.iroha2.generated.datamodel.asset.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain
import jp.co.soramitsu.iroha2.generated.datamodel.expression.EvaluatesTo
import jp.co.soramitsu.iroha2.generated.datamodel.expression.Expression
import jp.co.soramitsu.iroha2.generated.datamodel.isi.Instruction
import jp.co.soramitsu.iroha2.generated.datamodel.isi.MintBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.RegisterBox
import jp.co.soramitsu.iroha2.generated.datamodel.isi.SetKeyValueBox
import jp.co.soramitsu.iroha2.generated.datamodel.metadata.Metadata
import java.math.BigInteger
import jp.co.soramitsu.iroha2.generated.datamodel.account.Id as AccountId

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

    fun storeAsset(
        assetName: String,
        assetDomain: String,
        accountName: String,
        accountDomain: String = assetDomain,
        key: String,
        value: Value
    ): Instruction {
        val assetId = AssetId(
            DefinitionId(assetName, assetDomain),
            AccountId(accountName, accountDomain),
        )
        return storeAsset(assetId, key, value)
    }

    fun storeAsset(
        assetId: AssetId,
        key: String,
        value: Value
    ): Instruction {
        return Instruction.SetKeyValue(
            SetKeyValueBox(
                objectId = EvaluatesTo(Expression.Raw(Value.Id(IdBox.AssetId(assetId)))),
                key = EvaluatesTo(Expression.Raw(key.asValue())),
                value = EvaluatesTo(Expression.Raw(value))
            )
        )
    }

    fun mintAsset(
        assetName: String,
        assetDomain: String,
        accountName: String,
        accountDomain: String = assetDomain,
        quantity: BigInteger
    ): Instruction {
        val assetId = AssetId(
            DefinitionId(assetName, assetDomain),
            AccountId(accountName, accountDomain),
        )
        return mintAssetInternal(assetId, AssetValue.BigQuantity(quantity))
    }

    fun mintAsset(
        assetId: AssetId,
        quantity: BigInteger
    ): Instruction {
        return mintAssetInternal(assetId, AssetValue.BigQuantity(quantity))
    }

    fun mintAsset(
        assetName: String,
        assetDomain: String,
        accountName: String,
        accountDomain: String = assetDomain,
        quantity: UInt
    ): Instruction {
        val assetId = AssetId(
            DefinitionId(assetName, assetDomain),
            AccountId(accountName, accountDomain),
        )
        return mintAssetInternal(assetId, AssetValue.Quantity(quantity))
    }

    fun mintAsset(
        assetId: AssetId,
        quantity: UInt
    ): Instruction {
        return mintAssetInternal(assetId, AssetValue.Quantity(quantity))
    }

    /**
     * For assets with `AssetValue.Quantity` or AssetValue.BigQuantity`. For `AssetValue.Store` use `storeAsset()`
     */
    private fun mintAssetInternal(
        assetId: AssetId,
        assetValue: AssetValue
    ): Instruction {
        return Instruction.Mint(
            MintBox(
                `object` = EvaluatesTo(
                    Expression.Raw(
                        Value.Identifiable(
                            IdentifiableBox.Asset(
                                Asset(
                                    id = assetId,
                                    value = assetValue
                                )
                            )
                        )
                    )
                ),
                destinationId = EvaluatesTo(
                    Expression.Raw(
                        Value.Id(
                            IdBox.AssetId(assetId)
                        )
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
}

fun String.asValue() = Value.String(this)

fun Int.asValue() = this.toUInt().asValue()

fun UInt.asValue() = Value.U32(this)

fun Boolean.asValue() = Value.Bool(this)
