package com.example.tinderchat.Vartical;

public class virtical_Objectmatches {
    private String userId,name,Image;
    private String TAG = "virtical_Objectmatches";
    public virtical_Objectmatches(String userId, String name, String Image) {
        this.userId = userId;
        this.name = name;
        this.Image = Image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
