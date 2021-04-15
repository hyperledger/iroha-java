package jp.co.soramitsu.iroha2.model.events.reject;

import jp.co.soramitsu.iroha2.model.instruction.Instruction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructionExecution implements TransactionRejectionReason {

    @NonNull
    private Instruction instruction;

    @NonNull
    private String reason;
}
