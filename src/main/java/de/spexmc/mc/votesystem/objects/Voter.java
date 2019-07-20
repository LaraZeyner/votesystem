package de.spexmc.mc.votesystem.objects;

import java.util.Date;
import java.util.UUID;

import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.CalendarUtils;
import de.spexmc.mc.votesystem.util.Messenger;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class Voter extends VoterImpl {
  public Voter(UUID uuid, short amount, short streak, Date date) {
    super(uuid, amount, streak, date);
  }

  public void vote() {
    if (canVote()) {

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
    } else {
      return false;
    }
    return true;
  }
}
