package de.spexmc.mc.votesystem.storage;

import java.io.File;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public final class Const {
  public static final File SQL_CONFIG = new File("plugins" + File.separator + "config" + File.separator + "sql.properties");

  public static final String PLAYERTABLE = "Playerstatus";
  public static final String VOTE_URL = "http://www.spexmc.de/";
}