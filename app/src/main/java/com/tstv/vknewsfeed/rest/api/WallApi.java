package com.tstv.vknewsfeed.rest.api;


import com.tstv.vknewsfeed.model.CommentItem;
import com.tstv.vknewsfeed.rest.model.response.Full;
import com.tstv.vknewsfeed.rest.model.response.GetWallByIdResponse;
import com.tstv.vknewsfeed.rest.model.response.ItemWithSendersResponse;
import com.tstv.vknewsfeed.rest.model.response.WallGetResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tstv on 01.09.2017.
 */

public interface WallApi {

    @GET(ApiMethods.WALL_GET)
    Observable<WallGetResponse> get(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_BY_ID)
    Observable<GetWallByIdResponse> getById(@QueryMap Map<String, String> map);

    @GET(ApiMethods.WALL_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);

}
