// Do not change. Autogenerated code
package jp.co.soramitsu.schema.generated.datamodel.query

import io.emeraldpay.polkaj.scale.ScaleCodecReader
import io.emeraldpay.polkaj.scale.ScaleCodecWriter
import io.emeraldpay.polkaj.scale.ScaleReader
import io.emeraldpay.polkaj.scale.ScaleWriter
import kotlin.Int
import kotlin.Unit

/**
 * QueryBox
 *
 * Generated from 'iroha_data_model::query::QueryBox' enum
 */
public sealed class QueryBox {
  /**
   * @return Discriminator of variant in enum
   */
  public abstract fun discriminant(): Int

  /**
   * 'FindAllAccounts' variant
   */
  public class FindAllAccounts(
    private val findAllAccounts:
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAllAccounts
  ) : QueryBox() {
    public override fun discriminant(): Int = 0

    public companion object CODEC : ScaleReader<FindAllAccounts>, ScaleWriter<FindAllAccounts> {
      public override fun read(reader: ScaleCodecReader): FindAllAccounts =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAllAccounts(jp.co.soramitsu.schema.generated.datamodel.query.account.FindAllAccounts.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAccounts): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAllAccounts.write(writer,
            instance.findAllAccounts)
      }
    }
  }

  /**
   * 'FindAccountById' variant
   */
  public class FindAccountById(
    private val findAccountById:
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountById
  ) : QueryBox() {
    public override fun discriminant(): Int = 1

    public companion object CODEC : ScaleReader<FindAccountById>, ScaleWriter<FindAccountById> {
      public override fun read(reader: ScaleCodecReader): FindAccountById =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAccountById(jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountById.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountById): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountById.write(writer,
            instance.findAccountById)
      }
    }
  }

  /**
   * 'FindAccountKeyValueByIdAndKey' variant
   */
  public class FindAccountKeyValueByIdAndKey(
    private val findAccountKeyValueByIdAndKey:
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
  ) : QueryBox() {
    public override fun discriminant(): Int = 2

    public companion object CODEC : ScaleReader<FindAccountKeyValueByIdAndKey>,
        ScaleWriter<FindAccountKeyValueByIdAndKey> {
      public override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAccountKeyValueByIdAndKey(jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountKeyValueByIdAndKey):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey.write(writer,
            instance.findAccountKeyValueByIdAndKey)
      }
    }
  }

  /**
   * 'FindAccountsByName' variant
   */
  public class FindAccountsByName(
    private val findAccountsByName:
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByName
  ) : QueryBox() {
    public override fun discriminant(): Int = 3

    public companion object CODEC : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName>
        {
      public override fun read(reader: ScaleCodecReader): FindAccountsByName =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAccountsByName(jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByName.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByName.write(writer,
            instance.findAccountsByName)
      }
    }
  }

  /**
   * 'FindAccountsByDomainName' variant
   */
  public class FindAccountsByDomainName(
    private val findAccountsByDomainName:
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByDomainName
  ) : QueryBox() {
    public override fun discriminant(): Int = 4

    public companion object CODEC : ScaleReader<FindAccountsByDomainName>,
        ScaleWriter<FindAccountsByDomainName> {
      public override fun read(reader: ScaleCodecReader): FindAccountsByDomainName =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAccountsByDomainName(jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByDomainName.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainName):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.account.FindAccountsByDomainName.write(writer,
            instance.findAccountsByDomainName)
      }
    }
  }

  /**
   * 'FindAllAssets' variant
   */
  public class FindAllAssets(
    private val findAllAssets: jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssets
  ) : QueryBox() {
    public override fun discriminant(): Int = 5

    public companion object CODEC : ScaleReader<FindAllAssets>, ScaleWriter<FindAllAssets> {
      public override fun read(reader: ScaleCodecReader): FindAllAssets =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAllAssets(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssets.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAssets): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssets.write(writer,
            instance.findAllAssets)
      }
    }
  }

  /**
   * 'FindAllAssetsDefinitions' variant
   */
  public class FindAllAssetsDefinitions(
    private val findAllAssetsDefinitions:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssetsDefinitions
  ) : QueryBox() {
    public override fun discriminant(): Int = 6

    public companion object CODEC : ScaleReader<FindAllAssetsDefinitions>,
        ScaleWriter<FindAllAssetsDefinitions> {
      public override fun read(reader: ScaleCodecReader): FindAllAssetsDefinitions =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAllAssetsDefinitions(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssetsDefinitions.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAssetsDefinitions):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAllAssetsDefinitions.write(writer,
            instance.findAllAssetsDefinitions)
      }
    }
  }

  /**
   * 'FindAssetById' variant
   */
  public class FindAssetById(
    private val findAssetById: jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetById
  ) : QueryBox() {
    public override fun discriminant(): Int = 7

    public companion object CODEC : ScaleReader<FindAssetById>, ScaleWriter<FindAssetById> {
      public override fun read(reader: ScaleCodecReader): FindAssetById =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetById(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetById.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetById): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetById.write(writer,
            instance.findAssetById)
      }
    }
  }

  /**
   * 'FindAssetsByName' variant
   */
  public class FindAssetsByName(
    private val findAssetsByName:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByName
  ) : QueryBox() {
    public override fun discriminant(): Int = 8

    public companion object CODEC : ScaleReader<FindAssetsByName>, ScaleWriter<FindAssetsByName> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByName =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByName(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByName.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByName): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByName.write(writer,
            instance.findAssetsByName)
      }
    }
  }

  /**
   * 'FindAssetsByAccountId' variant
   */
  public class FindAssetsByAccountId(
    private val findAssetsByAccountId:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = 9

    public companion object CODEC : ScaleReader<FindAssetsByAccountId>,
        ScaleWriter<FindAssetsByAccountId> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByAccountId =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByAccountId(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountId.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAccountId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountId.write(writer,
            instance.findAssetsByAccountId)
      }
    }
  }

  /**
   * 'FindAssetsByAssetDefinitionId' variant
   */
  public class FindAssetsByAssetDefinitionId(
    private val findAssetsByAssetDefinitionId:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = 10

    public companion object CODEC : ScaleReader<FindAssetsByAssetDefinitionId>,
        ScaleWriter<FindAssetsByAssetDefinitionId> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByAssetDefinitionId =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByAssetDefinitionId(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAssetDefinitionId):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId.write(writer,
            instance.findAssetsByAssetDefinitionId)
      }
    }
  }

  /**
   * 'FindAssetsByDomainName' variant
   */
  public class FindAssetsByDomainName(
    private val findAssetsByDomainName:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainName
  ) : QueryBox() {
    public override fun discriminant(): Int = 11

    public companion object CODEC : ScaleReader<FindAssetsByDomainName>,
        ScaleWriter<FindAssetsByDomainName> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByDomainName =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByDomainName(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainName.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByDomainName): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainName.write(writer,
            instance.findAssetsByDomainName)
      }
    }
  }

  /**
   * 'FindAssetsByAccountIdAndAssetDefinitionId' variant
   */
  public class FindAssetsByAccountIdAndAssetDefinitionId(
    private val findAssetsByAccountIdAndAssetDefinitionId:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountIdAndAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = 12

    public companion object CODEC : ScaleReader<FindAssetsByAccountIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByAccountIdAndAssetDefinitionId> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByAccountIdAndAssetDefinitionId
          =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByAccountIdAndAssetDefinitionId(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountIdAndAssetDefinitionId.read(reader))

      public override fun write(writer: ScaleCodecWriter,
          instance: FindAssetsByAccountIdAndAssetDefinitionId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByAccountIdAndAssetDefinitionId.write(writer,
            instance.findAssetsByAccountIdAndAssetDefinitionId)
      }
    }
  }

  /**
   * 'FindAssetsByDomainNameAndAssetDefinitionId' variant
   */
  public class FindAssetsByDomainNameAndAssetDefinitionId(
    private val findAssetsByDomainNameAndAssetDefinitionId:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = 13

    public companion object CODEC : ScaleReader<FindAssetsByDomainNameAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainNameAndAssetDefinitionId> {
      public override fun read(reader: ScaleCodecReader): FindAssetsByDomainNameAndAssetDefinitionId
          =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetsByDomainNameAndAssetDefinitionId(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId.read(reader))

      public override fun write(writer: ScaleCodecWriter,
          instance: FindAssetsByDomainNameAndAssetDefinitionId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId.write(writer,
            instance.findAssetsByDomainNameAndAssetDefinitionId)
      }
    }
  }

  /**
   * 'FindAssetQuantityById' variant
   */
  public class FindAssetQuantityById(
    private val findAssetQuantityById:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetQuantityById
  ) : QueryBox() {
    public override fun discriminant(): Int = 14

    public companion object CODEC : ScaleReader<FindAssetQuantityById>,
        ScaleWriter<FindAssetQuantityById> {
      public override fun read(reader: ScaleCodecReader): FindAssetQuantityById =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetQuantityById(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetQuantityById.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetQuantityById.write(writer,
            instance.findAssetQuantityById)
      }
    }
  }

  /**
   * 'FindAssetKeyValueByIdAndKey' variant
   */
  public class FindAssetKeyValueByIdAndKey(
    private val findAssetKeyValueByIdAndKey:
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
  ) : QueryBox() {
    public override fun discriminant(): Int = 15

    public companion object CODEC : ScaleReader<FindAssetKeyValueByIdAndKey>,
        ScaleWriter<FindAssetKeyValueByIdAndKey> {
      public override fun read(reader: ScaleCodecReader): FindAssetKeyValueByIdAndKey =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAssetKeyValueByIdAndKey(jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetKeyValueByIdAndKey):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey.write(writer,
            instance.findAssetKeyValueByIdAndKey)
      }
    }
  }

  /**
   * 'FindAllDomains' variant
   */
  public class FindAllDomains(
    private val findAllDomains:
        jp.co.soramitsu.schema.generated.datamodel.query.domain.FindAllDomains
  ) : QueryBox() {
    public override fun discriminant(): Int = 16

    public companion object CODEC : ScaleReader<FindAllDomains>, ScaleWriter<FindAllDomains> {
      public override fun read(reader: ScaleCodecReader): FindAllDomains =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAllDomains(jp.co.soramitsu.schema.generated.datamodel.query.domain.FindAllDomains.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAllDomains): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.domain.FindAllDomains.write(writer,
            instance.findAllDomains)
      }
    }
  }

  /**
   * 'FindDomainByName' variant
   */
  public class FindDomainByName(
    private val findDomainByName:
        jp.co.soramitsu.schema.generated.datamodel.query.domain.FindDomainByName
  ) : QueryBox() {
    public override fun discriminant(): Int = 17

    public companion object CODEC : ScaleReader<FindDomainByName>, ScaleWriter<FindDomainByName> {
      public override fun read(reader: ScaleCodecReader): FindDomainByName =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindDomainByName(jp.co.soramitsu.schema.generated.datamodel.query.domain.FindDomainByName.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindDomainByName): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.domain.FindDomainByName.write(writer,
            instance.findDomainByName)
      }
    }
  }

  /**
   * 'FindAllPeers' variant
   */
  public class FindAllPeers(
    private val findAllPeers: jp.co.soramitsu.schema.generated.datamodel.query.peer.FindAllPeers
  ) : QueryBox() {
    public override fun discriminant(): Int = 18

    public companion object CODEC : ScaleReader<FindAllPeers>, ScaleWriter<FindAllPeers> {
      public override fun read(reader: ScaleCodecReader): FindAllPeers =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindAllPeers(jp.co.soramitsu.schema.generated.datamodel.query.peer.FindAllPeers.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindAllPeers): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.peer.FindAllPeers.write(writer,
            instance.findAllPeers)
      }
    }
  }

  /**
   * 'FindTransactionsByAccountId' variant
   */
  public class FindTransactionsByAccountId(
    private val findTransactionsByAccountId:
        jp.co.soramitsu.schema.generated.datamodel.query.transaction.FindTransactionsByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = 19

    public companion object CODEC : ScaleReader<FindTransactionsByAccountId>,
        ScaleWriter<FindTransactionsByAccountId> {
      public override fun read(reader: ScaleCodecReader): FindTransactionsByAccountId =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindTransactionsByAccountId(jp.co.soramitsu.schema.generated.datamodel.query.transaction.FindTransactionsByAccountId.read(reader))

      public override fun write(writer: ScaleCodecWriter, instance: FindTransactionsByAccountId):
          Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.transaction.FindTransactionsByAccountId.write(writer,
            instance.findTransactionsByAccountId)
      }
    }
  }

  /**
   * 'FindPermissionTokensByAccountId' variant
   */
  public class FindPermissionTokensByAccountId(
    private val findPermissionTokensByAccountId:
        jp.co.soramitsu.schema.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = 20

    public companion object CODEC : ScaleReader<FindPermissionTokensByAccountId>,
        ScaleWriter<FindPermissionTokensByAccountId> {
      public override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId =
          jp.co.soramitsu.schema.generated.datamodel.query.QueryBox.FindPermissionTokensByAccountId(jp.co.soramitsu.schema.generated.datamodel.query.permissions.FindPermissionTokensByAccountId.read(reader))

      public override fun write(writer: ScaleCodecWriter,
          instance: FindPermissionTokensByAccountId): Unit {
        jp.co.soramitsu.schema.generated.datamodel.query.permissions.FindPermissionTokensByAccountId.write(writer,
            instance.findPermissionTokensByAccountId)
      }
    }
  }

  public companion object CODEC : ScaleReader<QueryBox>, ScaleWriter<QueryBox> {
    public override fun read(reader: ScaleCodecReader): QueryBox = when(reader.readUByte()) {
    	0 -> FindAllAccounts.read(reader)
    	1 -> FindAccountById.read(reader)
    	2 -> FindAccountKeyValueByIdAndKey.read(reader)
    	3 -> FindAccountsByName.read(reader)
    	4 -> FindAccountsByDomainName.read(reader)
    	5 -> FindAllAssets.read(reader)
    	6 -> FindAllAssetsDefinitions.read(reader)
    	7 -> FindAssetById.read(reader)
    	8 -> FindAssetsByName.read(reader)
    	9 -> FindAssetsByAccountId.read(reader)
    	10 -> FindAssetsByAssetDefinitionId.read(reader)
    	11 -> FindAssetsByDomainName.read(reader)
    	12 -> FindAssetsByAccountIdAndAssetDefinitionId.read(reader)
    	13 -> FindAssetsByDomainNameAndAssetDefinitionId.read(reader)
    	14 -> FindAssetQuantityById.read(reader)
    	15 -> FindAssetKeyValueByIdAndKey.read(reader)
    	16 -> FindAllDomains.read(reader)
    	17 -> FindDomainByName.read(reader)
    	18 -> FindAllPeers.read(reader)
    	19 -> FindTransactionsByAccountId.read(reader)
    	20 -> FindPermissionTokensByAccountId.read(reader)
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
    }

    public override fun write(writer: ScaleCodecWriter, instance: QueryBox): Unit {
      when(instance.discriminant()) {
      	0 -> FindAllAccounts.write(writer, instance as FindAllAccounts)
      	1 -> FindAccountById.write(writer, instance as FindAccountById)
      	2 -> FindAccountKeyValueByIdAndKey.write(writer, instance as FindAccountKeyValueByIdAndKey)
      	3 -> FindAccountsByName.write(writer, instance as FindAccountsByName)
      	4 -> FindAccountsByDomainName.write(writer, instance as FindAccountsByDomainName)
      	5 -> FindAllAssets.write(writer, instance as FindAllAssets)
      	6 -> FindAllAssetsDefinitions.write(writer, instance as FindAllAssetsDefinitions)
      	7 -> FindAssetById.write(writer, instance as FindAssetById)
      	8 -> FindAssetsByName.write(writer, instance as FindAssetsByName)
      	9 -> FindAssetsByAccountId.write(writer, instance as FindAssetsByAccountId)
      	10 -> FindAssetsByAssetDefinitionId.write(writer, instance as FindAssetsByAssetDefinitionId)
      	11 -> FindAssetsByDomainName.write(writer, instance as FindAssetsByDomainName)
      	12 -> FindAssetsByAccountIdAndAssetDefinitionId.write(writer, instance as
          FindAssetsByAccountIdAndAssetDefinitionId)
      	13 -> FindAssetsByDomainNameAndAssetDefinitionId.write(writer, instance as
          FindAssetsByDomainNameAndAssetDefinitionId)
      	14 -> FindAssetQuantityById.write(writer, instance as FindAssetQuantityById)
      	15 -> FindAssetKeyValueByIdAndKey.write(writer, instance as FindAssetKeyValueByIdAndKey)
      	16 -> FindAllDomains.write(writer, instance as FindAllDomains)
      	17 -> FindDomainByName.write(writer, instance as FindDomainByName)
      	18 -> FindAllPeers.write(writer, instance as FindAllPeers)
      	19 -> FindTransactionsByAccountId.write(writer, instance as FindTransactionsByAccountId)
      	20 -> FindPermissionTokensByAccountId.write(writer, instance as
          FindPermissionTokensByAccountId)
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant")
      }
    }
  }
}