// Placeholder for EncryptionUtil.java
package com.dpaywallet.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {

    private static final String ENCRYPTION_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int KEY_LENGTH_BIT = 256;

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(KEY_LENGTH_BIT, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        new SecureRandom().nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        byte[] encryptedIvAndData = new byte[IV_LENGTH_BYTE + encryptedData.length];
        System.arraycopy(iv, 0, encryptedIvAndData, 0, IV_LENGTH_BYTE);
        System.arraycopy(encryptedData, 0, encryptedIvAndData, IV_LENGTH_BYTE, encryptedData.length);
        return Base64.getEncoder().encodeToString(encryptedIvAndData);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGO);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, decodedData, 0, IV_LENGTH_BYTE);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedData = cipher.doFinal(decodedData, IV_LENGTH_BYTE, decodedData.length - IV_LENGTH_BYTE);
        return new String(decryptedData);
    }
}
