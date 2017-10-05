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
public class SendCommentEventOnSubscribe implements ObservableOnSubscribe<VKResponse> {

    private int mOwnerId;
    private int mPostId;
    private String mMessage;
    private VKAttachments attachments;

    public SendCommentEventOnSubscribe(int ownerId, int postId, String message, VKAttachments attachments) {
        this.mOwnerId = ownerId;
        this.mPostId = postId;
        this.mMessage = message;
        this.attachments = attachments;
    }
    //преобразовывает данные из VKRequestListener в RX цепочку данных
    @Override
    public void subscribe(@NonNull ObservableEmitter<VKResponse> e) throws Exception {


        VKParameters parameters = new VKParameters();
        parameters.put(VKApiConst.OWNER_ID, mOwnerId);
        parameters.put(VKApiConst.POST_ID, mPostId);
        parameters.put(VKApiConst.ATTACHMENTS, attachments);
        parameters.put(VKApiConst.MESSAGE, mMessage);
        VKRequest request = VKApi.wall().addComment(parameters);
        request.attempts = 10;

        //отправляет запрос и создает слушатель для получения ответа
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                e.onNext(response); //передает ответ сервера в RX цепочку
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