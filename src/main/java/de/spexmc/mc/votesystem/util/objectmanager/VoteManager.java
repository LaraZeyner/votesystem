package de.spexmc.mc.votesystem.util.objectmanager;

import java.util.UUID;

import de.spexmc.mc.votesystem.objects.Voter;
import de.spexmc.mc.votesystem.storage.Data;
import de.spexmc.mc.votesystem.util.Messenger;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public final class VoteManager {

  public static Voter determineVoter(Player votePlayer) {
    final UUID voterUuid = votePlayer.getUniqueId();
    return Data.getInstance().getVoters().stream().filter(voter -> voter.getUuid().equals(voterUuid))
        .findFirst().orElse(null);
  }

  public static void handleVote(Player player, short amount, short streak) {
    Messenger.sendMessage(player, "Du hast bereits " + amount + " Mal gevotet (" + streak + "er Streak).");
    int coins = 100;
    if (amount % 5 == 0) {
      coins += amount * 10;
    }
    Messenger.sendMessage(player, "Du hast " + coins + " Coins erhalten.");
    //Belohnungen vergeben
  }
}
