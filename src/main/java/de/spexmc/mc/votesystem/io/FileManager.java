package de.spexmc.mc.votesystem.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.spexmc.mc.votesystem.Votesystem;
import de.spexmc.mc.votesystem.storage.Messages;

/**
 * Created by Lara on 26.02.2019 for votesystem
 */
public final class FileManager {
  private static final Logger logger;

  static {
    logger = Logger.getLogger(FileManager.class.getName());
  }

  public static Map<String, String> loadConfig(File file) {
    final Properties config = new Properties();
    try (final InputStream input = new FileInputStream(file.getPath())) {
      config.load(input);
      return collectData(config);

    } catch (FileNotFoundException ex) {
      logger.log(Level.SEVERE, Messages.MYSQL_DATA_NOT_SET);
      Votesystem.getInstance().onDisable();

    } catch (IOException ex) {
      logger.log(Level.SEVERE, ex.getMessage());
    }
    return null;
  }

  public static File getDataFolder() {
    return new File("plugins" + File.separator + "config");
  }

  private static Map<String, String> collectData(Properties config) {
    final Map<String, String> propertyMap = new HashMap<>();
    for (String key : config.stringPropertyNames()) {
      propertyMap.put(key, config.getProperty(key));
    }

    return propertyMap;
  }
}

