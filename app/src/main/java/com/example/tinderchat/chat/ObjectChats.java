package com.example.tinderchat.chat;

public class ObjectChats {
private String message;
private Boolean currentUser;

    private String TAG = "ObjectChat";
    public ObjectChats(String message, Boolean currentUser) {
        this.message = message;
        this.currentUser = currentUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Boolean currentUser) {
        this.currentUser = currentUser;
    }
}
