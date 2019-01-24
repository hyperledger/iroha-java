package jp.co.soramitsu.iroha.java.routers;

import iroha.protocol.Commands.Command;
import iroha.protocol.Commands.Command.CommandCase;
import jp.co.soramitsu.iroha.java.detail.router.Router;

public class CmdRouter extends Router<Command, CommandCase> {

  public CmdRouter() {
    super(Command::getCommandCase);
  }
}
