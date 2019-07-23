package de.spexmc.mc.votesystem.util.objectmanager;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import de.spexmc.mc.votesystem.Votesystem;
import de.spexmc.mc.votesystem.io.IOUtils;
import de.spexmc.mc.votesystem.listener.VotifierEvent;
import de.spexmc.mc.votesystem.model.PlayerVote;
import de.spexmc.mc.votesystem.storage.Data;
import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.Messenger;
import org.bukkit.Bukkit;

/**
 * The vote receiving server.
 */
public class VoteReceiver extends Thread {
  private boolean running = true;
  private final ServerSocket server;

  public VoteReceiver(String host, int port) throws IOException {
    try {
      server = new ServerSocket();
      server.bind(new InetSocketAddress(host, port));
    } catch (IOException ex) {
      Messenger.administratorMessage(Messages.ERROR_INITIALIZE_VOTE_RECEIVER, ex);
      throw new IOException(ex);
    }
  }

  public void shutdown() {
    this.running = false;
    if (server != null) {
      try {
        server.close();

      } catch (IOException ex) {
        Messenger.administratorMessage(ex.getMessage());
      }
    }
  }

  @Override
  public void run() {
    while (running) {
      try (final Socket socket = server.accept();
           final InputStream in = socket.getInputStream()) {
        socket.setSoTimeout(5_000);

        byte[] block = new byte[256];
        in.read(block, 0, block.length);
        block = IOUtils.decrypt(block, Data.getInstance().getVotifier().getKeyPair().getPrivate());
        int position = 0;

        final String opcode = IOUtils.readString(block, position);
        position += opcode.length() + 1;
        if (!opcode.equals("VOTE")) {
          Messenger.administratorMessage(Messages.ERROR_DECODE_RSA);
        }

        final String serviceName = IOUtils.readString(block, position);
        position += serviceName.length() + 1;
        final String username = IOUtils.readString(block, position);
        position += username.length() + 1;
        final String address = IOUtils.readString(block, position);
        position += address.length() + 1;
        final String timeStamp = IOUtils.readString(block, position);

        final PlayerVote playerVote = new PlayerVote(serviceName, username, timeStamp);

        Votesystem.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Votesystem.getInstance(), () ->
            Bukkit.getServer().getPluginManager().callEvent(new VotifierEvent(playerVote)));

      } catch (SocketException ex) {
        Messenger.administratorMessage("Protocol error. Ignoring packet - " + ex.getLocalizedMessage());

      } catch (BadPaddingException ex) {
        Messenger.administratorMessage(Messages.ERROR_DECRYPT_KEY, ex);

      } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
          IllegalBlockSizeException ex) {
        Messenger.administratorMessage(Messages.ERROR_RECEIVE_VOTES, ex);
      }
    }
  }
}
