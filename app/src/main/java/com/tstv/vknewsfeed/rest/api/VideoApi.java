package com.tstv.vknewsfeed.rest.api;

import com.tstv.vknewsfeed.rest.model.response.Full;
import com.tstv.vknewsfeed.rest.model.response.VideosResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tstv on 20.09.2017.
 */

public interface VideoApi {
    @GET(ApiMethods.VIDEO_GET)
    Observable<Full<VideosResponse>> get(@QueryMap Map<String, String> map);
}