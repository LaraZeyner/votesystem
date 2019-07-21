package de.spexmc.mc.votesystem.objects;

import java.util.Date;
import java.util.UUID;

import de.spexmc.mc.votesystem.storage.Const;
import de.spexmc.mc.votesystem.storage.Data;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.CalendarUtils;
import de.spexmc.mc.votesystem.util.Messenger;
import de.spexmc.mc.votesystem.util.mcutils.UUIDUtils;
import de.spexmc.mc.votesystem.util.objectmanager.VoteManager;
import org.bukkit.entity.Player;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class Voter extends VoterImpl {
  private static final long serialVersionUID = 7131460093302339809L;

  public Voter(UUID uuid) {
    this(uuid, 0, 0, new Date());
  }

  public Voter(UUID uuid, int amount, int streak, Date date) {
    super(uuid, amount, streak, date);
  }

  public void vote() {
    setAmount(getAmount() + 1);
    setStreak(checkStreak() ? getStreak() + 1 : 1);
    setLastVote(new Date());
    Data.getInstance().getSql().setVoter(this);


    Messenger.sendMessage(getPlayer(), "Du hast bereits " + getAmount() + " Mal gevotet (" + getStreak() +
        "-er Streak).");
    final int coins = VoteManager.determineCoins(getAmount());
    Messenger.sendMessage(getPlayer(), "Du hast " + coins + " Coins erhalten.");
    //TODO (Abgie) 21.07.2019: Belohnungen vergeben
  }


  public void sendVoteMessage() {
    if (canVote()) {
      Messenger.sendMessage(getUuid(), Messages.VOTE_LINK, Const.VOTE_URL);
    }
  }

  public boolean canVote() {
    return !checkVotedToday();
  }

  private boolean checkVotedToday() {
    final short dayOfVote = CalendarUtils.getDayOfDate(getLastVote());
    final short dayOfToday = CalendarUtils.getDayOfDate(new Date());
    if (dayOfVote == dayOfToday) {
      Messenger.sendMessage(getUuid(), Messages.ALREADY_VOTED_TODAY);
      return true;
    }
    return false;
  }

  private boolean checkStreak() {
    final short dayOfVote = CalendarUtils.getDayOfDate(getLastVote());
    final short dayOfToday = CalendarUtils.getDayOfDate(new Date());
    return dayOfToday - dayOfVote < 2;
  }

  public Player getPlayer() {
    return UUIDUtils.getPlayer(getUuid());
  }
}
