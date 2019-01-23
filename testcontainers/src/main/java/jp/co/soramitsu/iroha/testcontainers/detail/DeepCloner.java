package jp.co.soramitsu.iroha.testcontainers.detail;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class DeepCloner<T> {

  private static final ObjectMapper mapper = new ObjectMapper();

  final Class<T> type;

  public T clone(T t) throws IOException {
    val tree = mapper.valueToTree(t);
    return mapper.treeToValue(tree, type);
  }
}
