package com.tstv.vknewsfeed.event;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKAttachments;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by tstv on 27.09.2017.
 */

public class SendCreatedPostEventOnSubscribe implements ObservableOnSubscribe<VKResponse> {
    private int mOwnerId;
    private String mMessage;
    private VKAttachments mAttachments;

    public SendCreatedPostEventOnSubscribe(int ownerId, String message, VKAttachments attachments) {
        this.mOwnerId = ownerId;
        this.mMessage = message;
        this.mAttachments = attachments;
    }


    @Override
    public void subscribe(@NonNull ObservableEmitter<VKResponse> e) throws Exception {


        VKParameters parameters = new VKParameters();
        parameters.put(VKApiConst.OWNER_ID, mOwnerId);
        parameters.put(VKApiConst.MESSAGE, mMessage);
        parameters.put(VKApiConst.ATTACHMENTS, mAttachments);
        VKRequest request = VKApi.wall().post(parameters);
        request.attempts = 10;

        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                e.onNext(response);
                e.onComplete();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                e.onError(error.httpError);
            }
        });
    }
}