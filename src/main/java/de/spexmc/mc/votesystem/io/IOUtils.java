package de.spexmc.mc.votesystem.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import de.spexmc.mc.votesystem.storage.Messages;
import de.spexmc.mc.votesystem.util.Messenger;

/**
 * Created by Lara on 23.07.2019 for votesystem
 */
public final class IOUtils {
  public static void readURL(StringBuilder builder, URLConnection connection) throws IOException {
    if (connection != null && connection.getInputStream() != null) {
      try (final InputStreamReader streamReader =
               new InputStreamReader(connection.getInputStream(), Charset.defaultCharset());
           final BufferedReader bufferedReader = new BufferedReader(streamReader)) {
        int cp;
        while ((cp = bufferedReader.read()) != -1) {
          builder.append((char) cp);
        }
      }
    }
  }

  public static byte[] decrypt(byte[] data, PrivateKey key)
      throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
      BadPaddingException, IllegalBlockSizeException {
    final Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, key);
    return cipher.doFinal(data);
  }

  public static KeyPair generate(int bits) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
    Messenger.administratorMessage(Messages.GENERATE_KEY_PAIR);
    final KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
    final RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(bits, RSAKeyGenParameterSpec.F4);
    keygen.initialize(spec);
    return keygen.generateKeyPair();
  }

  public static void save(File directory, KeyPair keyPair) throws IOException {
    final PrivateKey privateKey = keyPair.getPrivate();
    final PublicKey publicKey = keyPair.getPublic();

    storePublicKey(directory, publicKey);
    storePrivateKey(directory, privateKey);
  }

  private static void storePrivateKey(File directory, PrivateKey privateKey) throws IOException {
    final PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
    try (final FileOutputStream out = new FileOutputStream(directory + "/private.key")) {
      out.write(DatatypeConverter.printBase64Binary(privateSpec.getEncoded())
          .getBytes());
    }
  }

  private static void storePublicKey(File directory, PublicKey publicKey) throws IOException {
    final X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKey.getEncoded());
    try (final FileOutputStream out = new FileOutputStream(directory + "/public.key")) {
      out.write(DatatypeConverter.printBase64Binary(publicSpec.getEncoded())
          .getBytes());
    }
  }

  public static String readString(byte[] data, int offset) {
    final StringBuilder builder = new StringBuilder();
    for (int i = offset; i < data.length; i++) {
      if (data[i] != '\n') {
        builder.append((char) data[i]);
      }
    }

    return builder.toString();
  }
}
