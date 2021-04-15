package jp.co.soramitsu.iroha2.model.events.reject;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
@JsonSubTypes({
        @Type(value = InstructionExecution.class, name = "InstructionExecution"),
        @Type(value = NotPermitted.class, name = "NotPermitted"),
        @Type(value = SignatureVerification.class, name = "SignatureVerification"),
        @Type(value = UnexpectedGenesisAccountSignature.class, name = "UnexpectedGenesisAccountSignature"),
        @Type(value = UnsatisfiedSignatureCondition.class, name = "UnsatisfiedSignatureCondition"),
})
public interface TransactionRejectionReason extends RejectionReason {
}
