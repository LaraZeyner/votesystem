package de.spexmc.mc.votesystem.util.mcutils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import de.spexmc.mc.votesystem.Votesystem;
import de.spexmc.mc.votesystem.io.IOUtils;
import de.spexmc.mc.votesystem.storage.Const;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.Messenger;
import de.spexmc.mc.votesystem.util.objectmanager.VoteReceiver;
import org.bukkit.Bukkit;

/**
 * Votifier implementation
 */
public class Votifier {
  private KeyPair keyPair;
  private VoteReceiver voteReceiver;

  public Votifier() {
    if (Objects.requireNonNull(Const.RSA_DIRECTORY.list()).length == 0) {
      Messenger.administratorMessage("First time initialization...");
      Messenger.administratorMessage(
          "First time initialization...\n--------------------------------------------------------\n " +
              "Assigning Votesystem to listen on port 8192. Chances are\n that your hosting provider will assign " +
              "a different port.\n--------------------------------------------------------");
      try {
        this.keyPair = IOUtils.generate(2048);
        IOUtils.save(Const.RSA_DIRECTORY, keyPair);
      } catch (IOException | NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
        Messenger.administratorMessage(Messages.CONFIG_ERROR, ex);
        Votesystem.getInstance().onDisable();
      }
    }

    try {
      voteReceiver = new VoteReceiver(Bukkit.getServer().getIp(), 8192);
      voteReceiver.start();
    } catch (IOException ex) {
      Messenger.administratorMessage(Messages.ERROR_RECEIVE_VOTES, ex);
      Votesystem.getInstance().onDisable();
    }
  }

  public KeyPair getKeyPair() {
    return keyPair;
  }

  public void onDisable() {
    Objects.requireNonNull(voteReceiver).shutdown();
  }

}