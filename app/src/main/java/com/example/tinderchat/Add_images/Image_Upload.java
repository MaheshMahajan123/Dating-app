package com.example.tinderchat.Add_images;

import com.google.firebase.database.Exclude;

public class Image_Upload {
    private String mImageUrl;
    private String mKey;

    public Image_Upload() {
        //empty constructor needed
    }

    public Image_Upload( String imageUrl) {
//        if (name.trim().equals("")) {
//            name = "No Name";
//        }
        mImageUrl = imageUrl;
    }


public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

}