package com.tstv.vknewsfeed.rest.api;

import com.tstv.vknewsfeed.model.Group;
import com.tstv.vknewsfeed.model.Member;
import com.tstv.vknewsfeed.rest.model.response.BaseItemResponse;
import com.tstv.vknewsfeed.rest.model.response.Full;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tstv on 16.09.2017.
 */

public interface GroupsApi {

    @GET(ApiMethods.GROUPS_GET_MEMBERS)
    Observable<Full<BaseItemResponse<Member>>> getMembers(@QueryMap Map<String, String> map);

    @GET(ApiMethods.GROUPS_GET_BY_ID)
    Observable<Full<List<Group>>> getById(@QueryMap Map<String, String> map);
}
