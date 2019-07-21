package de.spexmc.mc.votesystem.util;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.mcutils.UUIDUtils;
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
    UUIDUtils.getPlayer(uuid).sendMessage(Messages.PREFIX + msg);
  }

  public static void sendMessage(UUID uuid, String message, String url) {
    final String commandLine = "/tellraw " + UUIDUtils.getPlayer(uuid).getName() + " {text:\"" + message +
        "\",clickEvent:{action:open_url,value:\"" + url + "\"}}";
    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), commandLine);
  }

  public static void broadcast(String msg) {
    Bukkit.broadcastMessage(Messages.PREFIX + msg);
  }



}
