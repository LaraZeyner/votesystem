package de.spexmc.mc.votesystem.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.spexmc.mc.votesystem.io.sql.SQLManager;
import de.spexmc.mc.votesystem.model.Voter;
import de.spexmc.mc.votesystem.util.mcutils.Votifier;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public final class Data {
  private static Data instance;

  /**
   * Singleton-Muster: nur eine Instanz im gesamten Programm
   *
   * @return Instanz
   */
  public static Data getInstance() {
    if (instance == null) {
      instance = new Data();
    }
    return instance;
  }

  private final List<Voter> voters;
  private final Map<UUID, String> cache;
  private final SQLManager sql;
  private final Votifier votifier;

  private Data() {
    this.cache = new HashMap<>();
    this.voters = new ArrayList<>();
    this.sql = new SQLManager();
    this.votifier = new Votifier();
  }

  //<editor-fold desc="getter and setter">
  public Map<UUID, String> getCache() {
    return cache;
  }

  public SQLManager getSql() {
    return sql;
  }

  public List<Voter> getVoters() {
    return voters;
  }

  public Votifier getVotifier() {
    return votifier;
  }

  //</editor-fold>
}
