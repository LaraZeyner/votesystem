package de.spexmc.mc.votesystem.listener;

import de.spexmc.mc.votesystem.model.PlayerVote;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * {@code VotifierEvent} is a custom Bukkit event class that is sent
 * synchronously to CraftBukkit's main thread allowing other plugins to listener
 * for votes.
 */
public class VotifierEvent extends Event {
  private static final HandlerList handlers;

  public static HandlerList getHandlerList() {
    return handlers;
  }

  private final PlayerVote playerVote;

  static {
    handlers = new HandlerList();
  }

  public VotifierEvent(final PlayerVote playerVote) {
    this.playerVote = playerVote;
  }

  PlayerVote getPlayerVote() {
    return playerVote;
  }

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
