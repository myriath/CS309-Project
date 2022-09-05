package com.example.cs309android.util;

/**
 * Data class containing the salt and hash values of a full hash
 *
 * @author Mitch Hudson
 */
public class Hash {
    /**
     * Byte array containing the hash's salt
     */
    private byte[] salt;
    /**
     * Byte array containing the hash
     */
    private byte[] hash;

    /**
     * Public constructor
     *
     * @param salt  byte array containing the salt used for the hash
     * @param hash  byte array containing the hash value
     */
    public Hash(byte[] salt, byte[] hash) {
        this.salt = salt;
        this.hash = hash;
    }

    /**
     * Getter for the salt
     * @return  Salt byte array
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * Getter for the hash
     * @return  Hash byte array
     */
    public byte[] getHash() {
        return hash;
    }
}
