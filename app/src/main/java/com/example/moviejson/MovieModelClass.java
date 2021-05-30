package com.example.moviejson;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModelClass implements Parcelable {
    String id;
    String year;
    String name;
    String img;
    String description;
    String premiere;
    String tickets;

    public MovieModelClass(String id,String year, String name, String img, String description, String premiere, String tickets) {
        this.id = id;
        this.year=year;
        this.name = name;
        this.img = img;
        this.description = description;
        this.premiere = premiere;
        this.tickets = tickets;
    }

    public MovieModelClass() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPremiere() {
        return premiere;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
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
        year = in.readString();
        name = in.readString();
        img = in.readString();
        description = in.readString();
        premiere = in.readString();
        tickets = in.readString();
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
        dest.writeString(year);
        dest.writeString(name);
        dest.writeString(img);
        dest.writeString(description);
        dest.writeString(premiere);
        dest.writeString(tickets);
    }
}
