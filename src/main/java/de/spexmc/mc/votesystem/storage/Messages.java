package de.spexmc.mc.votesystem.storage;

/**
 * Created by Lara on 20.07.2019 for votesystem
 */
public final class Messages {
  // Log-Messages
  public static final String CONFIG_ERROR = "Fehler in der Config";
  public static final String DISABLING = "Votesystem fährt herunter...";
  public static final String ENABLING = "Votesystem startet...";
  public static final String MYSQL_CONNECTION_FAILED = "Verbindung zur Datenbank konnte nicht aufgebaut werden";
  public static final String MYSQL_DATA_NOT_SET = "Datenbank-Konfigurationsdatei benötigt";
  public static final String SUCCESSFULLY_DISABLED = "Votesystem wurde erfolgreich heruntergefahren.";
  public static final String SUCCESSFULLY_ENABLED = "Votesystem wurde erfolgreich gestartet.";

  // General
  public static final String PREFIX = "§6Votesystem §8| §7";
  public static final String ALREADY_VOTED_TODAY = "Du hast bereits gevotet.";
  public static final String VOTE_LINK = "§b§lHier §7§rkannst du für uns voten.";
  public static final String GENERATE_KEY_PAIR = "Votesystem is generating an RSA key pair...";
  public static final String ERROR_RECEIVE_VOTES = "Error receiving votes";
  public static final String ERROR_DECODE_RSA = "Unable to decode RSA";
  public static final String ERROR_DECRYPT_KEY = "Unable to decrypt vote record. Make sure that that your public key matches the one you gave the server list.";
  public static final String ERROR_INITIALIZE_VOTE_RECEIVER = "Error initializing vote receiver. Please verify that the IP address is not in use.";
}

