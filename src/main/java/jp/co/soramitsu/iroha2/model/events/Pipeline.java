package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Pipeline implements Event {

    @NonNull
    private EntityType entityType;
    @NonNull
    private Status status;
    @NonNull
    private byte[] hash;
}