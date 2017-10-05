package com.tstv.vknewsfeed.rest.api;

import com.tstv.vknewsfeed.model.Profile;
import com.tstv.vknewsfeed.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tstv on 14.09.2017.
 */

public interface UsersApi {

    @GET(ApiMethods.USERS_GET)
    Observable<Full<List<Profile>>> get(@QueryMap Map<String, String> map);
}

