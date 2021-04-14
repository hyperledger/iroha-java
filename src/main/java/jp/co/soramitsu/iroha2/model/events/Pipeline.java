package jp.co.soramitsu.iroha2.model.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME)
public class Pipeline implements EventType {

    @NonNull
    private EntityType entityType;
    @NonNull
    private Status status;
    @NonNull
    private byte[] hash;
}