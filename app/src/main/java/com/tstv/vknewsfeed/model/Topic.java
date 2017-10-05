package com.tstv.vknewsfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.vk.sdk.api.model.Identifiable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tstv on 17.09.2017.
 */

public class Topic extends RealmObject implements Identifiable{

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public int id;


    @SerializedName("title")
    @Expose
    public String title;

    @SerializedName("comments")
    @Expose
    public int comments;

    public int groupId;



    @Override
    public int getId() {
        return 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
