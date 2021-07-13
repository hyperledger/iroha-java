//
// Auto-generated file. DO NOT EDIT!
//
package jp.co.soramitsu.iroha2.generated.datamodel.domain

import java.lang.String
import java.util.Map
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.Account
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.AssetDefinitionEntry
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.DefinitionId
import jp.co.soramitsu.iroha2.generated.datamodel.domain.Domain.Id

/**
 * Domain
 *
 * Generated from 'iroha_data_model::domain::Domain' regular structure
 */
public class Domain(
  public val name: String,
  public val accounts: Map<Id, Account>,
  public val assetDefinitions: Map<DefinitionId, AssetDefinitionEntry>
)
