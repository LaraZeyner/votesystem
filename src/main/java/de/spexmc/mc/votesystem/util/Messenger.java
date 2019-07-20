package de.spexmc.mc.votesystem.util;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.votesystem.storage.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public final class Messenger {
  private static final Logger logger;

  static {
    logger = Logger.getLogger(Messenger.class.getName());
  }

  public static void administratorMessage(String msg) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.isOp()) {
        sendMessage(player, "&a&l" + msg);
      }
    }

    logger.log(Level.INFO, msg);
  }

  public static void sendMessage(Player player, String msg) {
    player.sendMessage(Messages.PREFIX + msg);
  }

  public static void sendMessage(UUID uuid, String msg) {
    Bukkit.getPlayer(uuid).sendMessage(Messages.PREFIX + msg);
  }

  public static void broadcast(String msg) {
    Bukkit.broadcastMessage(Messages.PREFIX + msg);
  }

}
