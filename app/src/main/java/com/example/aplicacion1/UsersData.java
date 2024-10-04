package com.example.aplicacion1;

public class UsersData {
    private String name,profile,uid;

    public UsersData(String name, String profile, String uid) {
        this.name = name;
        this.profile = profile;
        this.uid = uid;
    }

    public UsersData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
