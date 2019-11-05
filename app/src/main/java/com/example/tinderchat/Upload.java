package com.example.tinderchat;

import android.net.Uri;

public class Upload {
    private String email, phone, Password,sex;

    public Upload(String email, String phone, String password, String sex) {
        this.email = email;
        this.phone = phone;
        Password = password;

        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



}
