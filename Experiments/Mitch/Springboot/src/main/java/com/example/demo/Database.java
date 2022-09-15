package com.example.demo;

import org.apache.tomcat.util.codec.binary.Base64;
import java.util.HashMap;

/**
 * Database class that uses a hashmap
 * TODO: Replace with actual database calls
 * EXPERIMENT 4
 *
 * @author Mitch Hudson
 */
public class Database {
    /**
     * HashMap containing user data
     */
    private static final HashMap<String, User> db = new HashMap<>();

    /**
     * Adds a new user to the database
     *
     * @param user  User to add to the database
     */
    public static void put(User user) {
        db.put(user.username, user);
    }

    /**
     * Gets a user from the database with the given username
     * @param username  Username of the user to get
     * @return          User object containing all of the user's data
     */
    public static User get(String username) {
        return db.get(username);
    }

    // TODO: Remove
    public static HashMap<String, User> getDB() {
        return db;
    }

    /**
     * Represents a user in the database, containing all of it's login data
     */
    public static class User {
        /**
         * The user's username
         */
        private final String username;
        /**
         * The user's email address
         */
        private final String email;
        /**
         * The user's salt
         */
        private final byte[] salt;
        /**
         * The user's hashed password + salt
         */
        private final byte[] hash;

        /**
         * Public constructor
         *
         * @param username  The username of the new user
         * @param email     The email of the new user
         * @param salt      The new user's salt
         * @param hash      The hashed password of the new user
         */
        public User(String username, String email, byte[] salt, byte[] hash) {
            this.username = username;
            this.email = email;
            this.salt = salt;
            this.hash = hash;
        }

        /**
         * Getter for the username
         * @return  String
         */
        public String getUsername() {
            return username;
        }

        /**
         * Getter for the email
         * @return  String
         */
        public String getEmail() {
            return email;
        }

        /**
         * Getter for the Salt
         * @return  Base64 encoded String
         */
        public String getEncodedSalt() {
            return Base64.encodeBase64String(salt);
        }

        /**
         * Getter for the Hash
         * @return  Base64 encoded String
         */
        public String getEncodedHash() {
            return Base64.encodeBase64String(hash);
        }

        @Override
        public String toString() {
            return "[" + email + "," + username + "]: " + getEncodedHash() + ";" + getEncodedSalt() + "\n";
        }
    }
}
