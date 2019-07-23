package de.spexmc.mc.votesystem.model;

/**
 * A model for a vote.
 */
public class PlayerVote {
  private final String serviceName, timeStamp, username;

  public PlayerVote(String serviceName, String username, String timeStamp) {
    this.serviceName = serviceName;
    this.timeStamp = timeStamp;
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  String getServiceName() {
    return serviceName;
  }

  @Override
  public String toString() {
    return "PlayerVote (from:" + serviceName + " username:" + username + " timeStamp:" + timeStamp + ")";
  }
}
