package de.spexmc.mc.votesystem;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.votesystem.io.sql.SQLManager;
import de.spexmc.mc.votesystem.storage.Data;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.Registerer;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class Votesystem extends JavaPlugin {
  private static Votesystem instance;
  private final Logger logger = Logger.getLogger(getClass().getName());

  public static Votesystem getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    logger.log(Level.INFO, Messages.ENABLING);
    instance = this;
    final Data data = Data.getInstance();
    data.getCache().putAll(data.getSql().getPlayers());

    Registerer.performRegistration();
    logger.log(Level.INFO, Messages.SUCCESSFULLY_ENABLED);
  }

  @Override
  public void onDisable() {
    logger.log(Level.INFO, Messages.DISABLING);
    final SQLManager sql = Data.getInstance().getSql();
    sql.disconnect();
    logger.log(Level.INFO, Messages.SUCCESSFULLY_DISABLED);
  }

}