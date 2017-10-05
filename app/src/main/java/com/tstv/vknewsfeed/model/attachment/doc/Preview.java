package com.tstv.vknewsfeed.model.attachment.doc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.model.attachment.video.Video;

import io.realm.RealmObject;

/**
 * Created by tstv on 20.09.2017.
 */

public class Preview extends RealmObject {
    @SerializedName("photo")
    @Expose
    public PhotoPreview photo;
    @SerializedName("video")
    @Expose
    public Video video;


    public PhotoPreview getPhoto() {
        return photo;
    }

    public void setPhoto(PhotoPreview photo) {
        this.photo = photo;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }



}