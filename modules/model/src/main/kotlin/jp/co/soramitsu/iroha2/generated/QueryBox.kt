//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated

import jp.co.soramitsu.iroha2.ModelEnum
import jp.co.soramitsu.iroha2.codec.ScaleCodecReader
import jp.co.soramitsu.iroha2.codec.ScaleCodecWriter
import jp.co.soramitsu.iroha2.codec.ScaleReader
import jp.co.soramitsu.iroha2.codec.ScaleWriter
import jp.co.soramitsu.iroha2.wrapException
import kotlin.Int
import kotlin.Unit

/**
 * QueryBox
 *
 * Generated from 'QueryBox' enum
 */
public sealed class QueryBox : ModelEnum {
    /**
     * @return Discriminator of variant in enum
     */
    public abstract fun discriminant(): Int

    /**
     * 'FindAllAccounts' variant
     */
    public data class FindAllAccounts(
        public val findAllAccounts: jp.co.soramitsu.iroha2.generated.FindAllAccounts,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAccounts>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAccounts> {
            public const val DISCRIMINANT: Int = 0

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAccounts = try {
                FindAllAccounts(
                    jp.co.soramitsu.iroha2.generated.FindAllAccounts.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAccounts,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllAccounts.write(writer, instance.findAllAccounts)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAccountById' variant
     */
    public data class FindAccountById(
        public val findAccountById: jp.co.soramitsu.iroha2.generated.FindAccountById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountById> {
            public const val DISCRIMINANT: Int = 1

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountById = try {
                FindAccountById(
                    jp.co.soramitsu.iroha2.generated.FindAccountById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAccountById.write(writer, instance.findAccountById)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAccountKeyValueByIdAndKey' variant
     */
    public data class FindAccountKeyValueByIdAndKey(
        public val findAccountKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.FindAccountKeyValueByIdAndKey,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountKeyValueByIdAndKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountKeyValueByIdAndKey> {
            public const val DISCRIMINANT: Int = 2

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountKeyValueByIdAndKey = try {
                FindAccountKeyValueByIdAndKey(
                    jp.co.soramitsu.iroha2.generated.FindAccountKeyValueByIdAndKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountKeyValueByIdAndKey,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindAccountKeyValueByIdAndKey.write(
                        writer,
                        instance.findAccountKeyValueByIdAndKey,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindAccountsByName' variant
     */
    public data class FindAccountsByName(
        public val findAccountsByName: jp.co.soramitsu.iroha2.generated.FindAccountsByName,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByName>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByName> {
            public const val DISCRIMINANT: Int = 3

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByName = try {
                FindAccountsByName(
                    jp.co.soramitsu.iroha2.generated.FindAccountsByName.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByName,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAccountsByName.write(
                    writer,
                    instance.findAccountsByName,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAccountsByDomainId' variant
     */
    public data class FindAccountsByDomainId(
        public val findAccountsByDomainId: jp.co.soramitsu.iroha2.generated.FindAccountsByDomainId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByDomainId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByDomainId> {
            public const val DISCRIMINANT: Int = 4

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByDomainId = try {
                FindAccountsByDomainId(
                    jp.co.soramitsu.iroha2.generated.FindAccountsByDomainId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsByDomainId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAccountsByDomainId.write(
                    writer,
                    instance.findAccountsByDomainId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAccountsWithAsset' variant
     */
    public data class FindAccountsWithAsset(
        public val findAccountsWithAsset: jp.co.soramitsu.iroha2.generated.FindAccountsWithAsset,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsWithAsset>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsWithAsset> {
            public const val DISCRIMINANT: Int = 5

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsWithAsset = try {
                FindAccountsWithAsset(
                    jp.co.soramitsu.iroha2.generated.FindAccountsWithAsset.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAccountsWithAsset,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAccountsWithAsset.write(
                    writer,
                    instance.findAccountsWithAsset,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllAssets' variant
     */
    public data class FindAllAssets(
        public val findAllAssets: jp.co.soramitsu.iroha2.generated.FindAllAssets,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssets>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssets> {
            public const val DISCRIMINANT: Int = 6

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssets = try {
                FindAllAssets(
                    jp.co.soramitsu.iroha2.generated.FindAllAssets.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssets,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllAssets.write(writer, instance.findAllAssets)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllAssetsDefinitions' variant
     */
    public data class FindAllAssetsDefinitions(
        public val findAllAssetsDefinitions: jp.co.soramitsu.iroha2.generated.FindAllAssetsDefinitions,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssetsDefinitions>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssetsDefinitions> {
            public const val DISCRIMINANT: Int = 7

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssetsDefinitions = try {
                FindAllAssetsDefinitions(
                    jp.co.soramitsu.iroha2.generated.FindAllAssetsDefinitions.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllAssetsDefinitions,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllAssetsDefinitions.write(
                    writer,
                    instance.findAllAssetsDefinitions,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetById' variant
     */
    public data class FindAssetById(
        public val findAssetById: jp.co.soramitsu.iroha2.generated.FindAssetById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetById> {
            public const val DISCRIMINANT: Int = 8

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetById = try {
                FindAssetById(
                    jp.co.soramitsu.iroha2.generated.FindAssetById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetById.write(writer, instance.findAssetById)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetDefinitionById' variant
     */
    public data class FindAssetDefinitionById(
        public val findAssetDefinitionById: jp.co.soramitsu.iroha2.generated.FindAssetDefinitionById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionById> {
            public const val DISCRIMINANT: Int = 9

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionById = try {
                FindAssetDefinitionById(
                    jp.co.soramitsu.iroha2.generated.FindAssetDefinitionById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetDefinitionById.write(
                    writer,
                    instance.findAssetDefinitionById,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetsByName' variant
     */
    public data class FindAssetsByName(
        public val findAssetsByName: jp.co.soramitsu.iroha2.generated.FindAssetsByName,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByName>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByName> {
            public const val DISCRIMINANT: Int = 10

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByName = try {
                FindAssetsByName(
                    jp.co.soramitsu.iroha2.generated.FindAssetsByName.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByName,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetsByName.write(writer, instance.findAssetsByName)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetsByAccountId' variant
     */
    public data class FindAssetsByAccountId(
        public val findAssetsByAccountId: jp.co.soramitsu.iroha2.generated.FindAssetsByAccountId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAccountId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAccountId> {
            public const val DISCRIMINANT: Int = 11

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAccountId = try {
                FindAssetsByAccountId(
                    jp.co.soramitsu.iroha2.generated.FindAssetsByAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAccountId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetsByAccountId.write(
                    writer,
                    instance.findAssetsByAccountId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetsByAssetDefinitionId' variant
     */
    public data class FindAssetsByAssetDefinitionId(
        public val findAssetsByAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.FindAssetsByAssetDefinitionId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAssetDefinitionId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAssetDefinitionId> {
            public const val DISCRIMINANT: Int = 12

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAssetDefinitionId = try {
                FindAssetsByAssetDefinitionId(
                    jp.co.soramitsu.iroha2.generated.FindAssetsByAssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByAssetDefinitionId,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindAssetsByAssetDefinitionId.write(
                        writer,
                        instance.findAssetsByAssetDefinitionId,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindAssetsByDomainId' variant
     */
    public data class FindAssetsByDomainId(
        public val findAssetsByDomainId: jp.co.soramitsu.iroha2.generated.FindAssetsByDomainId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainId> {
            public const val DISCRIMINANT: Int = 13

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainId = try {
                FindAssetsByDomainId(
                    jp.co.soramitsu.iroha2.generated.FindAssetsByDomainId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetsByDomainId.write(
                    writer,
                    instance.findAssetsByDomainId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetsByDomainIdAndAssetDefinitionId' variant
     */
    public data class FindAssetsByDomainIdAndAssetDefinitionId(
        public val findAssetsByDomainIdAndAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.FindAssetsByDomainIdAndAssetDefinitionId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainIdAndAssetDefinitionId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainIdAndAssetDefinitionId> {
            public const val DISCRIMINANT: Int = 14

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainIdAndAssetDefinitionId = try {
                FindAssetsByDomainIdAndAssetDefinitionId(
                    jp.co.soramitsu.iroha2.generated.FindAssetsByDomainIdAndAssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetsByDomainIdAndAssetDefinitionId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetsByDomainIdAndAssetDefinitionId.write(
                    writer,
                    instance.findAssetsByDomainIdAndAssetDefinitionId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetQuantityById' variant
     */
    public data class FindAssetQuantityById(
        public val findAssetQuantityById: jp.co.soramitsu.iroha2.generated.FindAssetQuantityById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetQuantityById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetQuantityById> {
            public const val DISCRIMINANT: Int = 15

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetQuantityById = try {
                FindAssetQuantityById(
                    jp.co.soramitsu.iroha2.generated.FindAssetQuantityById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetQuantityById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetQuantityById.write(
                    writer,
                    instance.findAssetQuantityById,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindTotalAssetQuantityByAssetDefinitionId' variant
     */
    public data class FindTotalAssetQuantityByAssetDefinitionId(
        public val findTotalAssetQuantityByAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.FindTotalAssetQuantityByAssetDefinitionId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTotalAssetQuantityByAssetDefinitionId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTotalAssetQuantityByAssetDefinitionId> {
            public const val DISCRIMINANT: Int = 16

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTotalAssetQuantityByAssetDefinitionId = try {
                FindTotalAssetQuantityByAssetDefinitionId(
                    jp.co.soramitsu.iroha2.generated.FindTotalAssetQuantityByAssetDefinitionId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTotalAssetQuantityByAssetDefinitionId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindTotalAssetQuantityByAssetDefinitionId.write(
                    writer,
                    instance.findTotalAssetQuantityByAssetDefinitionId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'IsAssetDefinitionOwner' variant
     */
    public data class IsAssetDefinitionOwner(
        public val isAssetDefinitionOwner: jp.co.soramitsu.iroha2.generated.IsAssetDefinitionOwner,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.IsAssetDefinitionOwner>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.IsAssetDefinitionOwner> {
            public const val DISCRIMINANT: Int = 17

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.IsAssetDefinitionOwner = try {
                IsAssetDefinitionOwner(
                    jp.co.soramitsu.iroha2.generated.IsAssetDefinitionOwner.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.IsAssetDefinitionOwner,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.IsAssetDefinitionOwner.write(
                    writer,
                    instance.isAssetDefinitionOwner,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAssetKeyValueByIdAndKey' variant
     */
    public data class FindAssetKeyValueByIdAndKey(
        public val findAssetKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.FindAssetKeyValueByIdAndKey,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetKeyValueByIdAndKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetKeyValueByIdAndKey> {
            public const val DISCRIMINANT: Int = 18

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetKeyValueByIdAndKey = try {
                FindAssetKeyValueByIdAndKey(
                    jp.co.soramitsu.iroha2.generated.FindAssetKeyValueByIdAndKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetKeyValueByIdAndKey,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindAssetKeyValueByIdAndKey.write(
                        writer,
                        instance.findAssetKeyValueByIdAndKey,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindAssetDefinitionKeyValueByIdAndKey' variant
     */
    public data class FindAssetDefinitionKeyValueByIdAndKey(
        public val findAssetDefinitionKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.FindAssetDefinitionKeyValueByIdAndKey,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionKeyValueByIdAndKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionKeyValueByIdAndKey> {
            public const val DISCRIMINANT: Int = 19

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionKeyValueByIdAndKey = try {
                FindAssetDefinitionKeyValueByIdAndKey(
                    jp.co.soramitsu.iroha2.generated.FindAssetDefinitionKeyValueByIdAndKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAssetDefinitionKeyValueByIdAndKey,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAssetDefinitionKeyValueByIdAndKey.write(
                    writer,
                    instance.findAssetDefinitionKeyValueByIdAndKey,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllDomains' variant
     */
    public data class FindAllDomains(
        public val findAllDomains: jp.co.soramitsu.iroha2.generated.FindAllDomains,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllDomains>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllDomains> {
            public const val DISCRIMINANT: Int = 20

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllDomains = try {
                FindAllDomains(
                    jp.co.soramitsu.iroha2.generated.FindAllDomains.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllDomains,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllDomains.write(writer, instance.findAllDomains)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindDomainById' variant
     */
    public data class FindDomainById(
        public val findDomainById: jp.co.soramitsu.iroha2.generated.FindDomainById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainById> {
            public const val DISCRIMINANT: Int = 21

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainById = try {
                FindDomainById(
                    jp.co.soramitsu.iroha2.generated.FindDomainById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindDomainById.write(writer, instance.findDomainById)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindDomainKeyValueByIdAndKey' variant
     */
    public data class FindDomainKeyValueByIdAndKey(
        public val findDomainKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.FindDomainKeyValueByIdAndKey,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainKeyValueByIdAndKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainKeyValueByIdAndKey> {
            public const val DISCRIMINANT: Int = 22

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainKeyValueByIdAndKey = try {
                FindDomainKeyValueByIdAndKey(
                    jp.co.soramitsu.iroha2.generated.FindDomainKeyValueByIdAndKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindDomainKeyValueByIdAndKey,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindDomainKeyValueByIdAndKey.write(
                        writer,
                        instance.findDomainKeyValueByIdAndKey,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindAllPeers' variant
     */
    public data class FindAllPeers(
        public val findAllPeers: jp.co.soramitsu.iroha2.generated.FindAllPeers,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllPeers>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllPeers> {
            public const val DISCRIMINANT: Int = 23

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllPeers = try {
                FindAllPeers(
                    jp.co.soramitsu.iroha2.generated.FindAllPeers.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllPeers,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllPeers.write(writer, instance.findAllPeers)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllBlocks' variant
     */
    public data class FindAllBlocks(
        public val findAllBlocks: jp.co.soramitsu.iroha2.generated.FindAllBlocks,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlocks>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlocks> {
            public const val DISCRIMINANT: Int = 24

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlocks = try {
                FindAllBlocks(
                    jp.co.soramitsu.iroha2.generated.FindAllBlocks.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlocks,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllBlocks.write(writer, instance.findAllBlocks)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllBlockHeaders' variant
     */
    public data class FindAllBlockHeaders(
        public val findAllBlockHeaders: jp.co.soramitsu.iroha2.generated.FindAllBlockHeaders,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlockHeaders>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlockHeaders> {
            public const val DISCRIMINANT: Int = 25

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlockHeaders = try {
                FindAllBlockHeaders(
                    jp.co.soramitsu.iroha2.generated.FindAllBlockHeaders.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllBlockHeaders,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllBlockHeaders.write(
                    writer,
                    instance.findAllBlockHeaders,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindBlockHeaderByHash' variant
     */
    public data class FindBlockHeaderByHash(
        public val findBlockHeaderByHash: jp.co.soramitsu.iroha2.generated.FindBlockHeaderByHash,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindBlockHeaderByHash>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindBlockHeaderByHash> {
            public const val DISCRIMINANT: Int = 26

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindBlockHeaderByHash = try {
                FindBlockHeaderByHash(
                    jp.co.soramitsu.iroha2.generated.FindBlockHeaderByHash.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindBlockHeaderByHash,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindBlockHeaderByHash.write(
                    writer,
                    instance.findBlockHeaderByHash,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllTransactions' variant
     */
    public data class FindAllTransactions(
        public val findAllTransactions: jp.co.soramitsu.iroha2.generated.FindAllTransactions,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllTransactions>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllTransactions> {
            public const val DISCRIMINANT: Int = 27

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllTransactions = try {
                FindAllTransactions(
                    jp.co.soramitsu.iroha2.generated.FindAllTransactions.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllTransactions,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllTransactions.write(
                    writer,
                    instance.findAllTransactions,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindTransactionsByAccountId' variant
     */
    public data class FindTransactionsByAccountId(
        public val findTransactionsByAccountId:
        jp.co.soramitsu.iroha2.generated.FindTransactionsByAccountId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionsByAccountId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionsByAccountId> {
            public const val DISCRIMINANT: Int = 28

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionsByAccountId = try {
                FindTransactionsByAccountId(
                    jp.co.soramitsu.iroha2.generated.FindTransactionsByAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionsByAccountId,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindTransactionsByAccountId.write(
                        writer,
                        instance.findTransactionsByAccountId,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindTransactionByHash' variant
     */
    public data class FindTransactionByHash(
        public val findTransactionByHash: jp.co.soramitsu.iroha2.generated.FindTransactionByHash,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionByHash>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionByHash> {
            public const val DISCRIMINANT: Int = 29

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionByHash = try {
                FindTransactionByHash(
                    jp.co.soramitsu.iroha2.generated.FindTransactionByHash.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTransactionByHash,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindTransactionByHash.write(
                    writer,
                    instance.findTransactionByHash,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindPermissionTokensByAccountId' variant
     */
    public data class FindPermissionTokensByAccountId(
        public val findPermissionTokensByAccountId:
        jp.co.soramitsu.iroha2.generated.FindPermissionTokensByAccountId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokensByAccountId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokensByAccountId> {
            public const val DISCRIMINANT: Int = 30

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokensByAccountId = try {
                FindPermissionTokensByAccountId(
                    jp.co.soramitsu.iroha2.generated.FindPermissionTokensByAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokensByAccountId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindPermissionTokensByAccountId.write(
                    writer,
                    instance.findPermissionTokensByAccountId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindPermissionTokenSchema' variant
     */
    public data class FindPermissionTokenSchema(
        public val findPermissionTokenSchema:
        jp.co.soramitsu.iroha2.generated.FindPermissionTokenSchema,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokenSchema>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokenSchema> {
            public const val DISCRIMINANT: Int = 31

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokenSchema = try {
                FindPermissionTokenSchema(
                    jp.co.soramitsu.iroha2.generated.FindPermissionTokenSchema.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindPermissionTokenSchema,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindPermissionTokenSchema.write(
                    writer,
                    instance.findPermissionTokenSchema,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'DoesAccountHavePermissionToken' variant
     */
    public data class DoesAccountHavePermissionToken(
        public val doesAccountHavePermissionToken:
        jp.co.soramitsu.iroha2.generated.DoesAccountHavePermissionToken,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.DoesAccountHavePermissionToken>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.DoesAccountHavePermissionToken> {
            public const val DISCRIMINANT: Int = 32

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.DoesAccountHavePermissionToken = try {
                DoesAccountHavePermissionToken(
                    jp.co.soramitsu.iroha2.generated.DoesAccountHavePermissionToken.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.DoesAccountHavePermissionToken,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.DoesAccountHavePermissionToken.write(
                    writer,
                    instance.doesAccountHavePermissionToken,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllActiveTriggerIds' variant
     */
    public data class FindAllActiveTriggerIds(
        public val findAllActiveTriggerIds: jp.co.soramitsu.iroha2.generated.FindAllActiveTriggerIds,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllActiveTriggerIds>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllActiveTriggerIds> {
            public const val DISCRIMINANT: Int = 33

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllActiveTriggerIds = try {
                FindAllActiveTriggerIds(
                    jp.co.soramitsu.iroha2.generated.FindAllActiveTriggerIds.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllActiveTriggerIds,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllActiveTriggerIds.write(
                    writer,
                    instance.findAllActiveTriggerIds,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindTriggerById' variant
     */
    public data class FindTriggerById(
        public val findTriggerById: jp.co.soramitsu.iroha2.generated.FindTriggerById,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerById>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerById> {
            public const val DISCRIMINANT: Int = 34

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerById = try {
                FindTriggerById(
                    jp.co.soramitsu.iroha2.generated.FindTriggerById.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerById,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindTriggerById.write(writer, instance.findTriggerById)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindTriggerKeyValueByIdAndKey' variant
     */
    public data class FindTriggerKeyValueByIdAndKey(
        public val findTriggerKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.FindTriggerKeyValueByIdAndKey,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerKeyValueByIdAndKey>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerKeyValueByIdAndKey> {
            public const val DISCRIMINANT: Int = 35

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerKeyValueByIdAndKey = try {
                FindTriggerKeyValueByIdAndKey(
                    jp.co.soramitsu.iroha2.generated.FindTriggerKeyValueByIdAndKey.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggerKeyValueByIdAndKey,
            ): Unit =
                try {
                    jp.co.soramitsu.iroha2.generated.FindTriggerKeyValueByIdAndKey.write(
                        writer,
                        instance.findTriggerKeyValueByIdAndKey,
                    )
                } catch (ex: Exception) {
                    throw wrapException(ex)
                }
        }
    }

    /**
     * 'FindTriggersByDomainId' variant
     */
    public data class FindTriggersByDomainId(
        public val findTriggersByDomainId: jp.co.soramitsu.iroha2.generated.FindTriggersByDomainId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggersByDomainId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggersByDomainId> {
            public const val DISCRIMINANT: Int = 36

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggersByDomainId = try {
                FindTriggersByDomainId(
                    jp.co.soramitsu.iroha2.generated.FindTriggersByDomainId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindTriggersByDomainId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindTriggersByDomainId.write(
                    writer,
                    instance.findTriggersByDomainId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllRoles' variant
     */
    public data class FindAllRoles(
        public val findAllRoles: jp.co.soramitsu.iroha2.generated.FindAllRoles,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoles>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoles> {
            public const val DISCRIMINANT: Int = 37

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoles = try {
                FindAllRoles(
                    jp.co.soramitsu.iroha2.generated.FindAllRoles.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoles,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllRoles.write(writer, instance.findAllRoles)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllRoleIds' variant
     */
    public data class FindAllRoleIds(
        public val findAllRoleIds: jp.co.soramitsu.iroha2.generated.FindAllRoleIds,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoleIds>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoleIds> {
            public const val DISCRIMINANT: Int = 38

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoleIds = try {
                FindAllRoleIds(
                    jp.co.soramitsu.iroha2.generated.FindAllRoleIds.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllRoleIds,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllRoleIds.write(writer, instance.findAllRoleIds)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindRoleByRoleId' variant
     */
    public data class FindRoleByRoleId(
        public val findRoleByRoleId: jp.co.soramitsu.iroha2.generated.FindRoleByRoleId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindRoleByRoleId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindRoleByRoleId> {
            public const val DISCRIMINANT: Int = 39

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindRoleByRoleId = try {
                FindRoleByRoleId(
                    jp.co.soramitsu.iroha2.generated.FindRoleByRoleId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindRoleByRoleId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindRoleByRoleId.write(writer, instance.findRoleByRoleId)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindRolesByAccountId' variant
     */
    public data class FindRolesByAccountId(
        public val findRolesByAccountId: jp.co.soramitsu.iroha2.generated.FindRolesByAccountId,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindRolesByAccountId>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindRolesByAccountId> {
            public const val DISCRIMINANT: Int = 40

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindRolesByAccountId = try {
                FindRolesByAccountId(
                    jp.co.soramitsu.iroha2.generated.FindRolesByAccountId.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindRolesByAccountId,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindRolesByAccountId.write(
                    writer,
                    instance.findRolesByAccountId,
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    /**
     * 'FindAllParameters' variant
     */
    public data class FindAllParameters(
        public val findAllParameters: jp.co.soramitsu.iroha2.generated.FindAllParameters,
    ) : QueryBox() {
        override fun discriminant(): Int = DISCRIMINANT

        public companion object :
            ScaleReader<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllParameters>,
            ScaleWriter<jp.co.soramitsu.iroha2.generated.QueryBox.FindAllParameters> {
            public const val DISCRIMINANT: Int = 41

            override fun read(reader: ScaleCodecReader): jp.co.soramitsu.iroha2.generated.QueryBox.FindAllParameters = try {
                FindAllParameters(
                    jp.co.soramitsu.iroha2.generated.FindAllParameters.read(reader),
                )
            } catch (ex: Exception) {
                throw wrapException(ex)
            }

            override fun write(
                writer: ScaleCodecWriter,
                instance: jp.co.soramitsu.iroha2.generated.QueryBox.FindAllParameters,
            ): Unit = try {
                jp.co.soramitsu.iroha2.generated.FindAllParameters.write(writer, instance.findAllParameters)
            } catch (ex: Exception) {
                throw wrapException(ex)
            }
        }
    }

    public companion object : ScaleReader<QueryBox>, ScaleWriter<QueryBox> {
        override fun read(reader: ScaleCodecReader): QueryBox = when (
            val discriminant =
                reader.readUByte()
        ) {
            0 -> FindAllAccounts.read(reader)
            1 -> FindAccountById.read(reader)
            2 -> FindAccountKeyValueByIdAndKey.read(reader)
            3 -> FindAccountsByName.read(reader)
            4 -> FindAccountsByDomainId.read(reader)
            5 -> FindAccountsWithAsset.read(reader)
            6 -> FindAllAssets.read(reader)
            7 -> FindAllAssetsDefinitions.read(reader)
            8 -> FindAssetById.read(reader)
            9 -> FindAssetDefinitionById.read(reader)
            10 -> FindAssetsByName.read(reader)
            11 -> FindAssetsByAccountId.read(reader)
            12 -> FindAssetsByAssetDefinitionId.read(reader)
            13 -> FindAssetsByDomainId.read(reader)
            14 -> FindAssetsByDomainIdAndAssetDefinitionId.read(reader)
            15 -> FindAssetQuantityById.read(reader)
            16 -> FindTotalAssetQuantityByAssetDefinitionId.read(reader)
            17 -> IsAssetDefinitionOwner.read(reader)
            18 -> FindAssetKeyValueByIdAndKey.read(reader)
            19 -> FindAssetDefinitionKeyValueByIdAndKey.read(reader)
            20 -> FindAllDomains.read(reader)
            21 -> FindDomainById.read(reader)
            22 -> FindDomainKeyValueByIdAndKey.read(reader)
            23 -> FindAllPeers.read(reader)
            24 -> FindAllBlocks.read(reader)
            25 -> FindAllBlockHeaders.read(reader)
            26 -> FindBlockHeaderByHash.read(reader)
            27 -> FindAllTransactions.read(reader)
            28 -> FindTransactionsByAccountId.read(reader)
            29 -> FindTransactionByHash.read(reader)
            30 -> FindPermissionTokensByAccountId.read(reader)
            31 -> FindPermissionTokenSchema.read(reader)
            32 -> DoesAccountHavePermissionToken.read(reader)
            33 -> FindAllActiveTriggerIds.read(reader)
            34 -> FindTriggerById.read(reader)
            35 -> FindTriggerKeyValueByIdAndKey.read(reader)
            36 -> FindTriggersByDomainId.read(reader)
            37 -> FindAllRoles.read(reader)
            38 -> FindAllRoleIds.read(reader)
            39 -> FindRoleByRoleId.read(reader)
            40 -> FindRolesByAccountId.read(reader)
            41 -> FindAllParameters.read(reader)
            else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }

        override fun write(writer: ScaleCodecWriter, instance: QueryBox) {
            writer.directWrite(instance.discriminant())
            when (val discriminant = instance.discriminant()) {
                0 -> FindAllAccounts.write(writer, instance as FindAllAccounts)
                1 -> FindAccountById.write(writer, instance as FindAccountById)
                2 -> FindAccountKeyValueByIdAndKey.write(writer, instance as FindAccountKeyValueByIdAndKey)
                3 -> FindAccountsByName.write(writer, instance as FindAccountsByName)
                4 -> FindAccountsByDomainId.write(writer, instance as FindAccountsByDomainId)
                5 -> FindAccountsWithAsset.write(writer, instance as FindAccountsWithAsset)
                6 -> FindAllAssets.write(writer, instance as FindAllAssets)
                7 -> FindAllAssetsDefinitions.write(writer, instance as FindAllAssetsDefinitions)
                8 -> FindAssetById.write(writer, instance as FindAssetById)
                9 -> FindAssetDefinitionById.write(writer, instance as FindAssetDefinitionById)
                10 -> FindAssetsByName.write(writer, instance as FindAssetsByName)
                11 -> FindAssetsByAccountId.write(writer, instance as FindAssetsByAccountId)
                12 -> FindAssetsByAssetDefinitionId.write(writer, instance as FindAssetsByAssetDefinitionId)
                13 -> FindAssetsByDomainId.write(writer, instance as FindAssetsByDomainId)
                14 -> FindAssetsByDomainIdAndAssetDefinitionId.write(
                    writer,
                    instance as
                        FindAssetsByDomainIdAndAssetDefinitionId,
                )
                15 -> FindAssetQuantityById.write(writer, instance as FindAssetQuantityById)
                16 -> FindTotalAssetQuantityByAssetDefinitionId.write(
                    writer,
                    instance as
                        FindTotalAssetQuantityByAssetDefinitionId,
                )
                17 -> IsAssetDefinitionOwner.write(writer, instance as IsAssetDefinitionOwner)
                18 -> FindAssetKeyValueByIdAndKey.write(writer, instance as FindAssetKeyValueByIdAndKey)
                19 -> FindAssetDefinitionKeyValueByIdAndKey.write(
                    writer,
                    instance as
                        FindAssetDefinitionKeyValueByIdAndKey,
                )
                20 -> FindAllDomains.write(writer, instance as FindAllDomains)
                21 -> FindDomainById.write(writer, instance as FindDomainById)
                22 -> FindDomainKeyValueByIdAndKey.write(writer, instance as FindDomainKeyValueByIdAndKey)
                23 -> FindAllPeers.write(writer, instance as FindAllPeers)
                24 -> FindAllBlocks.write(writer, instance as FindAllBlocks)
                25 -> FindAllBlockHeaders.write(writer, instance as FindAllBlockHeaders)
                26 -> FindBlockHeaderByHash.write(writer, instance as FindBlockHeaderByHash)
                27 -> FindAllTransactions.write(writer, instance as FindAllTransactions)
                28 -> FindTransactionsByAccountId.write(writer, instance as FindTransactionsByAccountId)
                29 -> FindTransactionByHash.write(writer, instance as FindTransactionByHash)
                30 -> FindPermissionTokensByAccountId.write(
                    writer,
                    instance as
                        FindPermissionTokensByAccountId,
                )
                31 -> FindPermissionTokenSchema.write(writer, instance as FindPermissionTokenSchema)
                32 -> DoesAccountHavePermissionToken.write(
                    writer,
                    instance as
                        DoesAccountHavePermissionToken,
                )
                33 -> FindAllActiveTriggerIds.write(writer, instance as FindAllActiveTriggerIds)
                34 -> FindTriggerById.write(writer, instance as FindTriggerById)
                35 -> FindTriggerKeyValueByIdAndKey.write(writer, instance as FindTriggerKeyValueByIdAndKey)
                36 -> FindTriggersByDomainId.write(writer, instance as FindTriggersByDomainId)
                37 -> FindAllRoles.write(writer, instance as FindAllRoles)
                38 -> FindAllRoleIds.write(writer, instance as FindAllRoleIds)
                39 -> FindRoleByRoleId.write(writer, instance as FindRoleByRoleId)
                40 -> FindRolesByAccountId.write(writer, instance as FindRolesByAccountId)
                41 -> FindAllParameters.write(writer, instance as FindAllParameters)
                else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant") }
        }
    }
}
