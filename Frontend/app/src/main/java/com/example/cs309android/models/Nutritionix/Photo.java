package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Photo implements Parcelable {
    private final String thumb;
    private final String highres;
    private final Boolean is_user_uploaded;

    public Photo(JSONObject json) {
        thumb = json.optString("thumb");
        highres = json.optString("highres");
        is_user_uploaded = json.optBoolean("is_user_uploaded");
    }

    public Photo(Parcel in) {
        thumb = in.readString();
        highres = in.readString();
        is_user_uploaded = in.readBoolean();
    }

    public String getThumb() {
        return thumb;
    }

    public String getHighres() {
        return highres;
    }

    public Boolean getIsUserUploaded() {
        return is_user_uploaded;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(thumb);
        parcel.writeString(highres);
        parcel.writeBoolean(is_user_uploaded);
    }
}
