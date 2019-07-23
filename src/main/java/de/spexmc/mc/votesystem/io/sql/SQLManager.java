package de.spexmc.mc.votesystem.io.sql;

import java.util.List;

import de.spexmc.mc.votesystem.model.Voter;
import de.spexmc.mc.votesystem.storage.Data;

/**
 * Created by Lara on 13.01.2019 for votesystem
 */
public class SQLManager extends VoterStorageSQLHandler {
  public SQLManager() {
    init(connect());
  }

  @Override
  public void disconnect() {
    updateOnStop();
    super.disconnect();
  }

  public void updateOnStart() {
    final Data data = Data.getInstance();
    data.getCache().putAll(getPlayers());
    final List<Voter> voters = data.getVoters();
    voters.addAll(getVoters());
  }

  private void updateOnStop() {
    final List<Voter> voters = Data.getInstance().getVoters();
    voters.forEach(this::setVoter);
    Data.getInstance().getVotifier().onDisable();
  }
}
