package com.tstv.vknewsfeed.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.CurrentUser;
import com.tstv.vknewsfeed.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tstv on 02.09.2017.
 */
public abstract class BaseRequestModel {

    @SerializedName(VKApiConst.VERSION)
    Double version = ApiConstants.DEFAULT_VERSION;

    @SerializedName(VKApiConst.ACCESS_TOKEN)
    String accessToken = CurrentUser.getAccessToken();

    private Double getVersion() {
        return version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(VKApiConst.VERSION, String.valueOf(getVersion()));
        if (accessToken != null) {
            map.put(VKApiConst.ACCESS_TOKEN, getAccessToken());
        }

        onMapCreate(map);

        return map;
    }

    public abstract void onMapCreate(Map<String, String> map);
}
