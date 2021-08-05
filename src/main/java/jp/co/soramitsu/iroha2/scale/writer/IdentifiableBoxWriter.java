package jp.co.soramitsu.iroha2.scale.writer;

import io.emeraldpay.polkaj.scale.ScaleCodecWriter;
import io.emeraldpay.polkaj.scale.ScaleWriter;
import io.emeraldpay.polkaj.scale.writer.UnionWriter;
import java.io.IOException;
import jp.co.soramitsu.iroha2.model.IdentifiableBox;

public class IdentifiableBoxWriter implements ScaleWriter<IdentifiableBox> {

  private static final UnionWriter<IdentifiableBox> IDENTIFIABLE_BOX_WRITER = new UnionWriter<>(
      new AccountWriter(), // 0 - Account
      new NewAccountWriter(), // 1 - NewAccount
      new AssetWriter(), // 2 - Asset
      new AssetDefinitionWriter(), // 3 - AssetDefinition
      new DomainWriter(), // 4 - Domain
      new PeerWriter(), // 5 - Peer
      new NopWriter<>() // 6 - World
  );

  @Override
  public void write(ScaleCodecWriter writer, IdentifiableBox value) throws IOException {
    writer.write(IDENTIFIABLE_BOX_WRITER, new EnumerationUnionValue<>(value));
  }

}
