package jp.co.soramitsu.iroha2.model.instruction;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jp.co.soramitsu.iroha2.model.Enumeration;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Burn.class, name = "Burn"),
        @JsonSubTypes.Type(value = Fail.class, name = "Fail"),
        @JsonSubTypes.Type(value = If.class, name = "If"),
        @JsonSubTypes.Type(value = Mint.class, name = "Mint"),
        @JsonSubTypes.Type(value = Pair.class, name = "Pair"),
        @JsonSubTypes.Type(value = Register.class, name = "Register"),
        @JsonSubTypes.Type(value = RemoveKeyValue.class, name = "RemoveKeyValue"),
        @JsonSubTypes.Type(value = Sequence.class, name = "Sequence"),
        @JsonSubTypes.Type(value = SetKeyValue.class, name = "SetKeyValue"),
        @JsonSubTypes.Type(value = Transfer.class, name = "Transfer"),
        @JsonSubTypes.Type(value = Unregister.class, name = "Unregister"),
})
public interface Instruction extends Enumeration {

}
