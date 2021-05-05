package com.example.moviejson;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModelClass implements Parcelable {
    String id;
    String name;
    String img;

    public MovieModelClass(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public MovieModelClass() {
    }

    @Override
    public String toString() {
        return "MovieModelClass{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    protected MovieModelClass(Parcel in) {
        id = in.readString();
        name = in.readString();
        img = in.readString();
    }

    public static final Creator<MovieModelClass> CREATOR = new Creator<MovieModelClass>() {
        @Override
        public MovieModelClass createFromParcel(Parcel in) {
            return new MovieModelClass(in);
        }

        @Override
        public MovieModelClass[] newArray(int size) {
            return new MovieModelClass[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(img);
    }
}
