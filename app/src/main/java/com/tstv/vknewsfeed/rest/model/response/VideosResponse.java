package com.tstv.vknewsfeed.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.model.attachment.video.Video;

import java.util.List;

/**
 * Created by tstv on 20.09.2017.
 */

public class VideosResponse {
    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;
}