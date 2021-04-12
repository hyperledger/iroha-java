package jp.co.soramitsu.iroha2.model;

import jp.co.soramitsu.iroha2.model.events.SubscriptionRequest;
import lombok.Data;
import lombok.NonNull;

@Data
public class V1SubscriptionRequest implements VersionedSubscriptionRequest {

    @NonNull
    private SubscriptionRequest subscriptionRequest;
    @Override
    public int getIndex() {
        return 0;
    }
}
