package com.tstv.vknewsfeed.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.consts.ApiConstants;

import java.util.Map;

/**
 * Created by tstv on 19.09.2017.
 */

public class WallGetByIdRequestModel extends BaseRequestModel {
    @SerializedName(ApiConstants.POSTS)
    private String posts;

    @SerializedName(ApiConstants.EXTENDED)
    private int extended = 1;

    public WallGetByIdRequestModel(int ownerId, int postId) {
        this.posts = ownerId + "_" + postId;
    }

    private String getPosts() {
        return posts;
    }

    public void setPosts(String posts) {
        this.posts = posts;
    }

    private int getExtended() {
        return extended;
    }

    public void setExtended(int extended) {
        this.extended = extended;
    }


    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(ApiConstants.POSTS, getPosts());
        map.put(ApiConstants.EXTENDED, String.valueOf(getExtended()));
    }
}