package de.spexmc.mc.votesystem.util.objectmanager;

import java.util.UUID;

import de.spexmc.mc.votesystem.objects.Voter;
import de.spexmc.mc.votesystem.storage.Data;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public final class VoteManager {

  public static Voter determineVoter(UUID voterUuid) {
    Voter voter = Data.getInstance().getVoters().stream().filter(v -> v.getUuid().equals(voterUuid))
        .findFirst().orElse(null);

    if (voter == null) {
      voter = new Voter(voterUuid);
      Data.getInstance().getVoters().add(voter);
      Data.getInstance().getSql().addVoter(voter);
    }

    return voter;
  }

  public static Voter determineVoter(Player votePlayer) {
    return determineVoter(votePlayer.getUniqueId());
  }

  public static int determineCoins(int amount) {
    int coins = 100;
    if (amount % 5 == 0) {
      coins += amount * 10;
    }
    return coins;
  }
}
