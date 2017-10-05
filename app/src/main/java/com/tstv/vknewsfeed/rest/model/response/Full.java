package com.tstv.vknewsfeed.rest.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tstv on 01.09.2017.
 */

public class Full<T> {
    @SerializedName("response")
    @Expose
    public T response;
}
