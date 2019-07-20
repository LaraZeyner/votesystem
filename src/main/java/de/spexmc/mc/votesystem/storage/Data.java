package de.spexmc.mc.votesystem.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.spexmc.mc.votesystem.io.sql.SQLManager;

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
      instance.getSql().updateOnStart();
    }
    return instance;
  }

  private final Map<UUID, String> cache;
  private final SQLManager sql;

  private Data() {
    this.cache = new HashMap<>();
    this.sql = new SQLManager();
  }

  //<editor-fold desc="getter and setter">
  public Map<UUID, String> getCache() {
    return cache;
  }

  public SQLManager getSql() {
    return sql;
  }
  //</editor-fold>
}
