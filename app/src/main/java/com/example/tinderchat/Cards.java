package com.example.tinderchat;

public class Cards {

    private String userId;
    private String name;
    private String Image;
    private String date;
    private String city;

    public Cards(String userId, String name,String Image,String date,String city) {
        this.userId = userId;
        this.name = name;
        this.Image = Image;
        this.date = date;
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
       this.Image = Image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}