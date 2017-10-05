package com.tstv.vknewsfeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.model.attachment.ApiAttachment;
import com.tstv.vknewsfeed.model.countable.Likes;
import com.tstv.vknewsfeed.model.countable.Reposts;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tstv on 22.09.2017.
 */

public class CommentItem extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("from_id")
    @Expose
    public int senderId;

    @SerializedName("place")
    @Expose
    public Place place;


    public String senderName;

    public String senderPhoto;

    @SerializedName("date")
    @Expose
    public int date;
    @SerializedName("text")
    @Expose
    public String text;

    @SerializedName("attachments")
    @Expose
    public RealmList<ApiAttachment> attachments = new RealmList<>();

    public String attachmentsString;

    @SerializedName("likes")
    @Expose
    public Likes likes;


    @SerializedName("reposts")
    @Expose
    public Reposts reposts;

    public boolean isFromTopic = false;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return senderId;
    }



    public Integer getDate() {
        return date;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDisplayText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RealmList<ApiAttachment> getAttachments() {
        return attachments;
    }




    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public Reposts getReposts() {
        return reposts;
    }

    public void setReposts(Reposts reposts) {
        this.reposts = reposts;
    }


    public String getDisplayAttachmentsString() {
        return attachmentsString;
    }





    public void setAttachmentsString(String attachmentsString) {
        this.attachmentsString = attachmentsString;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getSenderPhoto() {
        return senderPhoto;
    }



    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderPhoto(String senderPhoto) {
        this.senderPhoto = senderPhoto;
    }



    public String getPhoto() {
        return senderPhoto;
    }


    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }




    public void setIsFromTopic(boolean isTopic) {
        this.isFromTopic = isTopic;
    }
}