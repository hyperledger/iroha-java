package jp.co.soramitsu.iroha2.util

import jp.co.soramitsu.iroha2.ACCOUNT_ID_DELIMITER
import jp.co.soramitsu.iroha2.ASSET_ID_DELIMITER
import jp.co.soramitsu.iroha2.asAccountId
import jp.co.soramitsu.iroha2.asAssetDefinitionId
import jp.co.soramitsu.iroha2.asAssetId
import jp.co.soramitsu.iroha2.asString
import org.junit.jupiter.api.Test

class ExtensionTest {

    @Test
    fun `account ID conversion`() {
        val id = "account${ACCOUNT_ID_DELIMITER}domain".asAccountId()
        id.asString()
    }

    @Test
    fun `asset definition ID conversion`() {
        val id = "asset${ASSET_ID_DELIMITER}domain".asAssetDefinitionId()
        id.asString()
    }

    @Test
    fun `asset ID conversion`() {
        val definitionId = "asset${ASSET_ID_DELIMITER}domain".asAssetDefinitionId()
        val accountId = "account${ACCOUNT_ID_DELIMITER}domain".asAccountId()
        val assetId = (definitionId.asString() + ASSET_ID_DELIMITER + accountId.asString()).asAssetId()
        assetId.asString()
    }

    @Test
    fun `asset ID with account domain conversion`() {
        val accountId = "account${ACCOUNT_ID_DELIMITER}domain".asAccountId()
        val assetId = "asset$ASSET_ID_DELIMITER$ASSET_ID_DELIMITER${accountId.asString()}".asAssetId()
        assetId.asString()
    }
}
