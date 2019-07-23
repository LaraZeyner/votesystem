package de.spexmc.mc.votesystem.util.mcutils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import de.spexmc.mc.votesystem.io.IOUtils;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.Messenger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
final class NameFetcher {
  static UUID getUUID(String playerName) {
    final String url = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
    try {
      final String uuidJson = callURL(url);
      if (!uuidJson.isEmpty()) {
        final JSONObject uuidObject = (JSONObject) JSONValue.parseWithException(uuidJson);
        return UUID.fromString(uuidObject.get("id").toString());
      }

    } catch (ParseException ex) {
      ex.printStackTrace();
    }

    return null;
  }

  private static String callURL(String urlString) {
    final StringBuilder builder = new StringBuilder();
    try {
      final URL url = new URL(urlString);
      final URLConnection connection = url.openConnection();
      connection.setReadTimeout(60 * 1000);
      IOUtils.readURL(builder, connection);

    } catch (FileNotFoundException ignored) {
      if (urlString.startsWith("https://api.mojang.com/")) {
        final String playername = urlString.split("minecraft/")[1];
        Messenger.administratorMessage(Messages.PREFIX + "Der Spieler §c" + playername +
            "§e existiert nicht.");
      }
    } catch (IOException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }

    return builder.toString();
  }

}