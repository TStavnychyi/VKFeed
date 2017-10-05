package com.tstv.vknewsfeed.rest.api;

import com.tstv.vknewsfeed.model.CommentItem;
import com.tstv.vknewsfeed.model.Topic;
import com.tstv.vknewsfeed.rest.model.response.BaseItemResponse;
import com.tstv.vknewsfeed.rest.model.response.Full;
import com.tstv.vknewsfeed.rest.model.response.ItemWithSendersResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by tstv on 17.09.2017.
 */

public interface BoardApi {
    @GET(ApiMethods.BOARD_GET_TOPICS)
    Observable<Full<BaseItemResponse<Topic>>> getTopics(@QueryMap Map<String, String> map);

    @GET(ApiMethods.BOARD_GET_COMMENTS)
    Observable<Full<ItemWithSendersResponse<CommentItem>>> getComments(@QueryMap Map<String, String> map);
}
