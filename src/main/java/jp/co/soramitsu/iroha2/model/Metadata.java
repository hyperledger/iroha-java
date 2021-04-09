package jp.co.soramitsu.iroha2.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Map;

@Data
public class Metadata {

    @NonNull
    private Map<String, Value> map;
}
