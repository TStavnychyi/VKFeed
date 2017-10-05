package com.tstv.vknewsfeed.rest.model.request;

/**
 * Created by tstv on 05.10.2017.
 */

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.annotations.SerializedName;
import com.tstv.vknewsfeed.consts.ApiConstants;

import org.json.JSONObject;

import java.util.Map;


//модель запроса регистрации устройства на сервере ВК как получателя push-сообщений

public class AccountRegisterDeviceRequest extends BaseRequestModel {
    @SerializedName(ApiConstants.TOKEN)
    String token;

    @SerializedName(ApiConstants.SYSTEM_VERSION)
    int systemVersion = 22;

    @SerializedName(ApiConstants.DEVICE_MODEL)
    String deviceModel = "android";

    @SerializedName(ApiConstants.DEVICE_ID)
    String deviceId;

    @SerializedName(ApiConstants.SETTINGS)
    JSONObject settings = ApiConstants.getDefaultPushSettings();

    public AccountRegisterDeviceRequest(String deviceId) {
        this.deviceId = deviceId;
        this.token = FirebaseInstanceId.getInstance().getToken();
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(int systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public JSONObject getSettings() {
        return settings;
    }

    public void setSettings(JSONObject settings) {
        this.settings = settings;
    }

    @Override
    public void onMapCreate(Map<String, String> map) {
        if (getToken() != null) {
            map.put(ApiConstants.TOKEN, getToken());
        }
        map.put(ApiConstants.SYSTEM_VERSION, String.valueOf(getSystemVersion()));
        map.put(ApiConstants.DEVICE_MODEL, getDeviceModel());
        map.put(ApiConstants.DEVICE_ID, getDeviceId());
        map.put(ApiConstants.SETTINGS, getSettings().toString());
    }
}