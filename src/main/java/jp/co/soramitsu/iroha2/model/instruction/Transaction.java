package jp.co.soramitsu.iroha2.model.instruction;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jp.co.soramitsu.iroha2.model.Payload;
import jp.co.soramitsu.iroha2.model.Signature;
import jp.co.soramitsu.iroha2.scale.writer.instruction.PayloadWriter;
import lombok.Data;
import lombok.NonNull;
import org.bouncycastle.jcajce.provider.digest.Blake2b.Blake2b256;

@Data
public class Transaction {

  @NonNull
  private Payload payload;
  @NonNull
  private List<Signature> signatures = new ArrayList<>();

  public Transaction(Payload payload) {
    this.payload = payload;
  }

  public byte[] getHash() throws IOException {
    ByteArrayOutputStream hashBuf = new ByteArrayOutputStream();
    ScaleCodecWriter hashCodec = new ScaleCodecWriter(hashBuf);
    hashCodec.write(new PayloadWriter(), payload);
    Blake2b256 hash = new Blake2b256();
    return hash.digest(hashBuf.toByteArray());
  }
}
