package com.tstv.vknewsfeed.rest.model.request;

import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.consts.ApiConstants;
import com.vk.sdk.api.VKApiConst;

import java.util.Map;

/**
 * Created by tstv on 17.09.2017.
 */

public class GroupsgetByIdRequestModel extends BaseRequestModel {

    @SerializedName(VKApiConst.GROUP_ID)
    int groupId;

    @SerializedName(VKApiConst.FIELDS)
    String fields = ApiConstants.DEFAULT_GROUP_FIELDS;

    public GroupsgetByIdRequestModel(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public int getGroupId() {
        return groupId;
    }

    public String getFields() {
        return fields;
    }

    public void setGroupId(int groupId) {
        this.groupId = Math.abs(groupId);
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        map.put(VKApiConst.GROUP_ID, String.valueOf(getGroupId()));
        map.put(VKApiConst.FIELDS, getFields());
    }
}
