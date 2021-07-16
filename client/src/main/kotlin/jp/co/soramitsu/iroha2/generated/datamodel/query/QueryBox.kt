//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.query

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
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAllAccounts
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAllAccounts>, ScaleWriter<FindAllAccounts> {
      public const val DISCRIMINANT: Int = 0

      public override fun read(reader: ScaleCodecReader): FindAllAccounts {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAccounts): Unit {
      }
    }
  }

  /**
   * 'FindAccountById' variant
   */
  public class FindAccountById(
    private val findAccountById:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountById
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAccountById>, ScaleWriter<FindAccountById> {
      public const val DISCRIMINANT: Int = 1

      public override fun read(reader: ScaleCodecReader): FindAccountById {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountById): Unit {
      }
    }
  }

  /**
   * 'FindAccountKeyValueByIdAndKey' variant
   */
  public class FindAccountKeyValueByIdAndKey(
    private val findAccountKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountKeyValueByIdAndKey
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAccountKeyValueByIdAndKey>,
        ScaleWriter<FindAccountKeyValueByIdAndKey> {
      public const val DISCRIMINANT: Int = 2

      public override fun read(reader: ScaleCodecReader): FindAccountKeyValueByIdAndKey {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountKeyValueByIdAndKey):
          Unit {
      }
    }
  }

  /**
   * 'FindAccountsByName' variant
   */
  public class FindAccountsByName(
    private val findAccountsByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByName
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAccountsByName>, ScaleWriter<FindAccountsByName> {
      public const val DISCRIMINANT: Int = 3

      public override fun read(reader: ScaleCodecReader): FindAccountsByName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByName): Unit {
      }
    }
  }

  /**
   * 'FindAccountsByDomainName' variant
   */
  public class FindAccountsByDomainName(
    private val findAccountsByDomainName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.account.FindAccountsByDomainName
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAccountsByDomainName>,
        ScaleWriter<FindAccountsByDomainName> {
      public const val DISCRIMINANT: Int = 4

      public override fun read(reader: ScaleCodecReader): FindAccountsByDomainName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAccountsByDomainName):
          Unit {
      }
    }
  }

  /**
   * 'FindAllAssets' variant
   */
  public class FindAllAssets(
    private val findAllAssets: jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssets
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAllAssets>, ScaleWriter<FindAllAssets> {
      public const val DISCRIMINANT: Int = 5

      public override fun read(reader: ScaleCodecReader): FindAllAssets {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAssets): Unit {
      }
    }
  }

  /**
   * 'FindAllAssetsDefinitions' variant
   */
  public class FindAllAssetsDefinitions(
    private val findAllAssetsDefinitions:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAllAssetsDefinitions
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAllAssetsDefinitions>,
        ScaleWriter<FindAllAssetsDefinitions> {
      public const val DISCRIMINANT: Int = 6

      public override fun read(reader: ScaleCodecReader): FindAllAssetsDefinitions {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAllAssetsDefinitions):
          Unit {
      }
    }
  }

  /**
   * 'FindAssetById' variant
   */
  public class FindAssetById(
    private val findAssetById: jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetById
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetById>, ScaleWriter<FindAssetById> {
      public const val DISCRIMINANT: Int = 7

      public override fun read(reader: ScaleCodecReader): FindAssetById {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetById): Unit {
      }
    }
  }

  /**
   * 'FindAssetsByName' variant
   */
  public class FindAssetsByName(
    private val findAssetsByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByName
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByName>, ScaleWriter<FindAssetsByName> {
      public const val DISCRIMINANT: Int = 8

      public override fun read(reader: ScaleCodecReader): FindAssetsByName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByName): Unit {
      }
    }
  }

  /**
   * 'FindAssetsByAccountId' variant
   */
  public class FindAssetsByAccountId(
    private val findAssetsByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByAccountId>, ScaleWriter<FindAssetsByAccountId>
        {
      public const val DISCRIMINANT: Int = 9

      public override fun read(reader: ScaleCodecReader): FindAssetsByAccountId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAccountId): Unit {
      }
    }
  }

  /**
   * 'FindAssetsByAssetDefinitionId' variant
   */
  public class FindAssetsByAssetDefinitionId(
    private val findAssetsByAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByAssetDefinitionId>,
        ScaleWriter<FindAssetsByAssetDefinitionId> {
      public const val DISCRIMINANT: Int = 10

      public override fun read(reader: ScaleCodecReader): FindAssetsByAssetDefinitionId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByAssetDefinitionId):
          Unit {
      }
    }
  }

  /**
   * 'FindAssetsByDomainName' variant
   */
  public class FindAssetsByDomainName(
    private val findAssetsByDomainName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainName
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByDomainName>,
        ScaleWriter<FindAssetsByDomainName> {
      public const val DISCRIMINANT: Int = 11

      public override fun read(reader: ScaleCodecReader): FindAssetsByDomainName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetsByDomainName): Unit {
      }
    }
  }

  /**
   * 'FindAssetsByAccountIdAndAssetDefinitionId' variant
   */
  public class FindAssetsByAccountIdAndAssetDefinitionId(
    private val findAssetsByAccountIdAndAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByAccountIdAndAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByAccountIdAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByAccountIdAndAssetDefinitionId> {
      public const val DISCRIMINANT: Int = 12

      public override fun read(reader: ScaleCodecReader):
          FindAssetsByAccountIdAndAssetDefinitionId {
      }

      public override fun write(writer: ScaleCodecWriter,
          instance: FindAssetsByAccountIdAndAssetDefinitionId): Unit {
      }
    }
  }

  /**
   * 'FindAssetsByDomainNameAndAssetDefinitionId' variant
   */
  public class FindAssetsByDomainNameAndAssetDefinitionId(
    private val findAssetsByDomainNameAndAssetDefinitionId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetsByDomainNameAndAssetDefinitionId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetsByDomainNameAndAssetDefinitionId>,
        ScaleWriter<FindAssetsByDomainNameAndAssetDefinitionId> {
      public const val DISCRIMINANT: Int = 13

      public override fun read(reader: ScaleCodecReader):
          FindAssetsByDomainNameAndAssetDefinitionId {
      }

      public override fun write(writer: ScaleCodecWriter,
          instance: FindAssetsByDomainNameAndAssetDefinitionId): Unit {
      }
    }
  }

  /**
   * 'FindAssetQuantityById' variant
   */
  public class FindAssetQuantityById(
    private val findAssetQuantityById:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetQuantityById
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetQuantityById>, ScaleWriter<FindAssetQuantityById>
        {
      public const val DISCRIMINANT: Int = 14

      public override fun read(reader: ScaleCodecReader): FindAssetQuantityById {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetQuantityById): Unit {
      }
    }
  }

  /**
   * 'FindAssetKeyValueByIdAndKey' variant
   */
  public class FindAssetKeyValueByIdAndKey(
    private val findAssetKeyValueByIdAndKey:
        jp.co.soramitsu.iroha2.generated.datamodel.query.asset.FindAssetKeyValueByIdAndKey
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAssetKeyValueByIdAndKey>,
        ScaleWriter<FindAssetKeyValueByIdAndKey> {
      public const val DISCRIMINANT: Int = 15

      public override fun read(reader: ScaleCodecReader): FindAssetKeyValueByIdAndKey {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAssetKeyValueByIdAndKey):
          Unit {
      }
    }
  }

  /**
   * 'FindAllDomains' variant
   */
  public class FindAllDomains(
    private val findAllDomains:
        jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindAllDomains
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAllDomains>, ScaleWriter<FindAllDomains> {
      public const val DISCRIMINANT: Int = 16

      public override fun read(reader: ScaleCodecReader): FindAllDomains {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAllDomains): Unit {
      }
    }
  }

  /**
   * 'FindDomainByName' variant
   */
  public class FindDomainByName(
    private val findDomainByName:
        jp.co.soramitsu.iroha2.generated.datamodel.query.domain.FindDomainByName
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindDomainByName>, ScaleWriter<FindDomainByName> {
      public const val DISCRIMINANT: Int = 17

      public override fun read(reader: ScaleCodecReader): FindDomainByName {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindDomainByName): Unit {
      }
    }
  }

  /**
   * 'FindAllPeers' variant
   */
  public class FindAllPeers(
    private val findAllPeers: jp.co.soramitsu.iroha2.generated.datamodel.query.peer.FindAllPeers
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindAllPeers>, ScaleWriter<FindAllPeers> {
      public const val DISCRIMINANT: Int = 18

      public override fun read(reader: ScaleCodecReader): FindAllPeers {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindAllPeers): Unit {
      }
    }
  }

  /**
   * 'FindTransactionsByAccountId' variant
   */
  public class FindTransactionsByAccountId(
    private val findTransactionsByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.transaction.FindTransactionsByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindTransactionsByAccountId>,
        ScaleWriter<FindTransactionsByAccountId> {
      public const val DISCRIMINANT: Int = 19

      public override fun read(reader: ScaleCodecReader): FindTransactionsByAccountId {
      }

      public override fun write(writer: ScaleCodecWriter, instance: FindTransactionsByAccountId):
          Unit {
      }
    }
  }

  /**
   * 'FindPermissionTokensByAccountId' variant
   */
  public class FindPermissionTokensByAccountId(
    private val findPermissionTokensByAccountId:
        jp.co.soramitsu.iroha2.generated.datamodel.query.permissions.FindPermissionTokensByAccountId
  ) : QueryBox() {
    public override fun discriminant(): Int = DISCRIMINANT

    public companion object : ScaleReader<FindPermissionTokensByAccountId>,
        ScaleWriter<FindPermissionTokensByAccountId> {
      public const val DISCRIMINANT: Int = 20

      public override fun read(reader: ScaleCodecReader): FindPermissionTokensByAccountId {
      }

      public override fun write(writer: ScaleCodecWriter,
          instance: FindPermissionTokensByAccountId): Unit {
      }
    }
  }

  public companion object : ScaleReader<QueryBox>, ScaleWriter<QueryBox> {
    public override fun read(reader: ScaleCodecReader): QueryBox = when(val discriminant =
        reader.readUByte()) {
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
    	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}

    public override fun write(writer: ScaleCodecWriter, instance: QueryBox): Unit {
      writer.directWrite(instance.discriminant())
      when(val discriminant = instance.discriminant()) {
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
      	else -> throw RuntimeException("Unresolved discriminant of the enum variant: $discriminant")}
    }
  }
}
