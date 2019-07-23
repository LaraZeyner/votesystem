package de.spexmc.mc.votesystem.storage;

import java.io.File;

import de.spexmc.mc.votesystem.io.FileManager;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public final class Const {
  public static final File KEY_FILE = new File(FileManager.getDataFolder() + File.separator + "public.key");
  public static final File SQL_CONFIG = new File(FileManager.getDataFolder() + File.separator +
      "sql.properties");

  public static final String PLAYERTABLE = "playerstatus";
  public static final String VOTE_URL = "https://www.spexmc.de/vote/";
}