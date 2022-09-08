package com.example.cs309android.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Static Hasher utility class designed to streamline the secure generation of
 * hashes from given passwords.
 * EXPERIMENT 3
 *
 * @author Mitch Hudson
 */
public class Hasher {
    /**
     * Static final SecureRandom object.
     */
    private static final Random RANDOM = new SecureRandom();
    /**
     * Number of iterations to hash before final hash.
     */
    private static final int ITERATIONS = 100;
    /**
     * Final length of the generated hash.
     */
    private static final int KEY_LENGTH = 256;

    /**
     * Static util class
     */
    private Hasher() {}

    /**
     * Randomly generates a 16 byte salt using the SecureRandom java class
     * @return  16 byte array of the generated salt
     */
    public static byte[] getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    /**
     * Hashes the given password and salt with PBEKeySpec
     *
     * @param plaintext     Password to hash
     * @param slt           Randomly generated salt to use for hash
     * @return              byte[] of salted hashed password
     */
    public static byte[] hash(char[] plaintext, byte[] slt) {
        PBEKeySpec spec = new PBEKeySpec(plaintext, slt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(plaintext, Character.MIN_VALUE);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while generating key: " + e.getMessage());
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Generates a new hash from a random salt and the given plaintext
     *
     * @param plaintext Plaintext to hash
     * @return          {@link Hash} object containing the salt used and the hashed value
     */
    public static Hash generateNewHash(char[] plaintext) {
        byte[] salt = getSalt();
        byte[] hash = hash(plaintext, salt);

        return new Hash(salt, hash);
    }
}
