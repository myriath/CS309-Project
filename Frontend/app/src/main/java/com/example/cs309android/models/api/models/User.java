package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * User class returned by various methods from api
 *
 * @author Mitch Hudson
 */
public class User implements Parcelable {
    /**
     * Username of the user
     */
    @Expose
    private final String username;
    /**
     * Type of the user
     * From {@link com.example.cs309android.util.Constants.UserType}
     */
    @Expose
    private final int userType;
    /**
     * Bio of the user
     */
    @Expose
    private final String bio;

    /**
     * Public constructor
     *
     * @param username username of the user
     * @param userType user type
     * @param bio      bio of the user
     */
    public User(String username, int userType, String bio) {
        this.username = username;
        this.userType = userType;
        this.bio = bio;
    }

    protected User(Parcel in) {
        username = in.readString();
        userType = in.readInt();
        bio = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(userType);
        dest.writeString(bio);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Getter for the username
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the user type
     *
     * @return user type
     */
    public int getUserType() {
        return userType;
    }

    /**
     * Getter for the bio
     *
     * @return bio
     */
    public String getBio() {
        return bio;
    }
}
