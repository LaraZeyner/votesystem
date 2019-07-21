package de.spexmc.mc.votesystem.io.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import de.spexmc.mc.votesystem.objects.Voter;
import de.spexmc.mc.votesystem.storage.Data;
import de.spexmc.mc.votesystem.util.Messenger;

/**
 * Created by Lara on 21.07.2019 for votesystem
 */
public class VoterStorageSQLHandler extends PlayerStorageSQLHandler {
  private final SQLManager sqlManager = Data.getInstance().getSql();
  private final Connection connection = sqlManager.getSqlData().getConnection();

  public List<Voter> getVoters() {
    final List<Voter> voters = new ArrayList<>();
    try (final PreparedStatement stmt = connection.prepareStatement("SELECT * FROM voter");
         final ResultSet resultSet = stmt.executeQuery()) {

      while (resultSet.next()) {
        final String uuid = resultSet.getString(1);
        final int amount = resultSet.getInt(2);
        final int streak = resultSet.getInt(3);
        final Timestamp lastVote = resultSet.getTimestamp(4);
        final Voter voter = new Voter(UUID.fromString(uuid), amount, streak, new Date(lastVote.getTime()));
        voters.add(voter);
      }

    } catch (final SQLException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
    return voters;
  }

  public void addVoter(Voter voter) {
    try (final PreparedStatement stmt = connection.prepareStatement(
        "INSERT INTO voter (uuid, amount, streak, lastVote) " +
            "VALUES (?, ?, ?, ?)")) {

      stmt.setString(1, voter.getUuid().toString());
      stmt.setInt(2, voter.getAmount());
      stmt.setInt(3, voter.getStreak());
      stmt.setTimestamp(4, new Timestamp(voter.getLastVote().getTime()));
      stmt.executeUpdate();

    } catch (final SQLException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
  }

  public void setVoter(Voter voter) {
    try (final PreparedStatement stmt = connection.prepareStatement(
        "UPDATE voter " +
            "SET  amount = ?," +
            "     streak = ?," +
            "     lastVote = ?" +
            "WHERE uuid = " + voter.getUuid())) {

      stmt.setInt(1, voter.getAmount());
      stmt.setInt(2, voter.getStreak());
      stmt.setTimestamp(3, new Timestamp(voter.getLastVote().getTime()));
      stmt.executeUpdate();

    } catch (final SQLException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
  }

}
