package com.gmail.pavlovsv93.notepadx.domain;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

public class Notes implements Parcelable {

    private String id;
    private String title;
    private String massage;
    private String time;
    private boolean done;

    public Notes(String id, String title, String massage, String time, boolean done) {
        this.id = id;
        this.title = title;
        this.massage = massage;
        this.time = time;
        this.done = done;
    }


    protected Notes(Parcel in) {
        id = in.readString();
        title = in.readString();
        massage = in.readString();
        time = in.readString();
        done = in.readByte() != 0;
    }

    public static final Creator<Notes> CREATOR = new Creator<Notes>() {
        @Override
        public Notes createFromParcel(Parcel in) {
            return new Notes(in);
        }

        @Override
        public Notes[] newArray(int size) {
            return new Notes[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(massage);
        dest.writeString(time);
        dest.writeByte((byte) (done ? 1 : 0));
    }
}
