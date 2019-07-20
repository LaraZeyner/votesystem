package de.spexmc.mc.votesystem.commands;

import de.spexmc.mc.votesystem.util.objectmanager.VoteManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for votesystem
 * /testcommand
 */
public class Vote implements CommandExecutor {
  @Override
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
    if (commandSender instanceof Player) {
      final Player target = (Player) commandSender;
      VoteManager.determineVoter(target).sendVoteMessage();
    }

    return false;
  }
}
