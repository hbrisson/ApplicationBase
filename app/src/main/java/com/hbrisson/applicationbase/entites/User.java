package com.hbrisson.applicationbase.entites;

/**
 * Created by hbrisson on 02/02/2015.
 */
public class User {

    private int mId;
    private String mName;
    private String mSurname;
    private String mPassword;
    private String mMail;
    private String mPhoto;

    public User() {
    }

    public User(int id, String name, String surname, String password, String mail, String photo) {
        mId = id;
        mName = name;
        mSurname = surname;
        mPassword = password;
        mMail = mail;
        mPhoto = photo;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }
}
