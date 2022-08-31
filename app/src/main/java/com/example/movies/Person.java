package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Person {

    // person id
    @SerializedName("id")
    private long id;
    // person name
    @SerializedName("name")
    private String name;
    // person photo
    @SerializedName("photo")
    private String photoUrl;
    // role name
    @SerializedName("description")
    private String description;
    // profession (actor or director)
    @SerializedName("enProfession")
    private String profession;

    public Person(long id, String name, String photoUrl, String description, String profession) {
        this.id = id;
        this.name = name;
        this.photoUrl = photoUrl;
        this.description = description;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public String getProfession() {
        return profession;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", description='" + description + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
