package com.tstv.vknewsfeed.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tstv on 16.09.2017.
 */

public class Member extends RealmObject {
    public static final String ID = "id";
    public static final String GROUP_ID = "group_id";
    public static final String PHOTO = "photo_100";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";

    @PrimaryKey
    @SerializedName(ID)
    public int id;

    @SerializedName(GROUP_ID)
    public int groupId;

    @SerializedName(PHOTO)
    public String photo;

    @SerializedName(FIRST_NAME)
    public String firstName;

    @SerializedName(LAST_NAME)
    public String lastName;



    public Member() {

    }

    public Member(Profile profile) {
        this.id = profile.getId();
        this.photo = profile.getPhoto();
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}