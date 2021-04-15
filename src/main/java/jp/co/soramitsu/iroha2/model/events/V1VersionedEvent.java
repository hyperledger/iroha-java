package jp.co.soramitsu.iroha2.model.events;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class V1VersionedEvent implements VersionedEvent {

    int version;

    @NonNull
    private Event content;
}
