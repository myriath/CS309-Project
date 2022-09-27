package com.example.cs309android.models.Nutritionix.instant;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Photo class from Nutritionix API
 *
 * @author Mitch Hudson
 */
public class Photo implements Parcelable {
    /**
     * URL to the thumbnail image
     */
    private final String thumb;
    /**
     * URL to the highres image
     */
    private final String highres;
    /**
     * True if the photo is uploaded by a user
     */
    private final Boolean is_user_uploaded;

    /**
     * Public constructor to be used by GSON
     *
     * @param thumb Thumbnail URL
     * @param highres Highres URL
     * @param is_user_uploaded True if the image is uploaded by a user
     */
    public Photo(String thumb, String highres, boolean is_user_uploaded) {
        this.thumb = thumb;
        this.highres = highres;
        this.is_user_uploaded = is_user_uploaded;
    }

    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    public Photo(Parcel in) {
        thumb = in.readString();
        highres = in.readString();
        is_user_uploaded = in.readBoolean();
    }

    /**
     * Getter for thumb
     * @return Thumbnail URL
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * Getter for highres
     * @return Highres URL
     */
    public String getHighres() {
        return highres;
    }

    /**
     * Getter for is_user_uploaded
     * @return true if the image is uploaded by a user
     */
    public Boolean getIsUserUploaded() {
        return is_user_uploaded;
    }

    /**
     * Creator class from Parcelable
     */
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    /**
     * Parcel method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes this object to a parcel
     * @param parcel Parcel to write to
     * @param i flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(thumb);
        parcel.writeString(highres);
        parcel.writeBoolean(is_user_uploaded != null && is_user_uploaded);
    }

    /**
     * toString method for debugging
     * @return String of this object
     */
    @NonNull
    @Override
    public String toString() {
        return "Photo{" +
                "thumb='" + thumb + '\'' +
                ", highres='" + highres + '\'' +
                ", is_user_uploaded=" + is_user_uploaded +
                '}';
    }
}
