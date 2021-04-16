package jp.co.soramitsu.iroha2.model.instruction;

import jp.co.soramitsu.iroha2.model.expression.Expression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfer implements Instruction {

    @NonNull
    private Expression sourceId; // EvaluatesTo<IdBox>
    @NonNull
    private Expression object; // EvaluatesTo<Value>
    @NonNull
    private Expression destinationId; // valuatesTo<IdBox>

    @Override
    public int getIndex() {
        return 4;
    }
}
