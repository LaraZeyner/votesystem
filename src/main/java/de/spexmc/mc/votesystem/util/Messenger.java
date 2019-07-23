package de.spexmc.mc.votesystem.util;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.mcutils.UUIDUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
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
        sendMessage(player, "§c§l" + msg);
      }
    }

    logger.log(Level.INFO, msg);
  }

  public static void administratorMessage(String msg, Exception ex) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      if (player.isOp()) {
        sendMessage(player, "§a§l" + msg + "\n" + ex.getMessage());
      }
    }

    logger.log(Level.SEVERE, msg, ex);
  }

  public static void sendMessage(Player player, String msg) {
    player.sendMessage(Messages.PREFIX + msg);
  }

  public static void sendMessage(UUID uuid, String msg) {
    UUIDUtils.getPlayer(uuid).sendMessage(Messages.PREFIX + msg);
  }

  public static void sendMessage(UUID uuid, String message, String url) {
    final TextComponent text = new TextComponent(message);
    text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
    UUIDUtils.getPlayer(uuid).spigot().sendMessage(text);
  }

  public static void broadcast(String msg) {
    Bukkit.broadcastMessage(Messages.PREFIX + msg);
  }

}
