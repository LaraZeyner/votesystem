package de.spexmc.mc.votesystem.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public class VoterImpl implements Serializable {
  private static final long serialVersionUID = -5374138726813928477L;
  /**
   * Primary-Key
   */
  private final UUID uuid;
  private Date lastVote;
  private short amount, streak;

  public VoterImpl(UUID uuid, short amount, short streak, Date lastVote) {
    this.uuid = uuid;
    this.amount = amount;
    this.streak = streak;
    this.lastVote = lastVote;
  }

  //<editor-fold desc="getter and setter">
  public short getAmount() {
    return amount;
  }

  public void setAmount(short amount) {
    this.amount = amount;
  }

  public Date getLastVote() {
    return lastVote;
  }

  public void setLastVote(Date lastVote) {
    this.lastVote = lastVote;
  }

  public short getStreak() {
    return streak;
  }

  public void setStreak(short streak) {
    this.streak = streak;
  }

  public UUID getUuid() {
    return uuid;
  }
  //</editor-fold>


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof VoterImpl)) return false;
    final VoterImpl voter = (VoterImpl) o;
    return amount == voter.amount &&
        streak == voter.streak &&
        uuid.equals(voter.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uuid, amount, streak);
  }

  @Override
  public String toString() {
    return "Voter{" +
        "uuid=" + uuid +
        ", amount=" + amount +
        ", streak=" + streak +
        '}';
  }
}
