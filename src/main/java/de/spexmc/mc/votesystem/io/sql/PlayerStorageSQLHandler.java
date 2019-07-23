package de.spexmc.mc.votesystem.io.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.spexmc.mc.votesystem.storage.Const;
import de.spexmc.mc.votesystem.util.Messenger;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
class PlayerStorageSQLHandler extends SQLConnector {
  Map<UUID, String> getPlayers() {
    final Map<UUID, String> players = new HashMap<>();
    try (final PreparedStatement statement = getConnection()
        .prepareStatement(
            "SELECT UUID, name FROM " + Const.PLAYERTABLE);
         final ResultSet resultSet = statement.executeQuery()) {
      while (resultSet.next()) {
        final String uuid = resultSet.getString(1);
        final String name = resultSet.getString(2);
        players.put(UUID.fromString(uuid), name);
      }

    } catch (final SQLException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
    return players;
  }
}
