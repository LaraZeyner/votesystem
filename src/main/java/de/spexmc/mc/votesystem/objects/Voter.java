package de.spexmc.mc.votesystem.objects;

import java.util.Date;
import java.util.UUID;

import de.spexmc.mc.votesystem.storage.Const;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.CalendarUtils;
import de.spexmc.mc.votesystem.util.Messenger;
import de.spexmc.mc.votesystem.util.objectmanager.VoteManager;
import org.bukkit.Bukkit;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class Voter extends VoterImpl {
  private static final long serialVersionUID = 7131460093302339809L;

  public Voter(UUID uuid) {
    this(uuid, (short) 0, (short) 0, new Date(0));
  }

  private Voter(UUID uuid, short amount, short streak, Date date) {
    super(uuid, amount, streak, date);
  }

  public void vote() {
    VoteManager.handleVote(Bukkit.getPlayer(getUuid()), getAmount(), getStreak());
  }

  public void sendVoteMessage() {
    if (canVote()) {
      Messenger.sendMessage(getUuid(), Messages.VOTE_LINK, Const.VOTE_URL);
    }
  }

  private boolean canVote() {
    final short dayOfVote = CalendarUtils.getDayOfDate(getLastVote());
    final short dayOfToday = CalendarUtils.getDayOfDate(new Date());
    return !checkVotedToday(dayOfVote, dayOfToday);
  }

  private boolean checkVotedToday(short dayOfVote, short dayOfToday) {
    if (dayOfVote == dayOfToday) {
      Messenger.sendMessage(getUuid(), Messages.ALREADY_VOTED_TODAY);
      return true;
    }
    return false;
  }

  private void save() {

  }
}
