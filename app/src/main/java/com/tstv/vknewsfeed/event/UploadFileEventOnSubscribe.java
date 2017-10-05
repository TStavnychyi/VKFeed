package com.tstv.vknewsfeed.event;

import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiDocument;
import com.vk.sdk.api.model.VKDocsArray;

import java.io.File;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by tstv on 27.09.2017.
 */

public class UploadFileEventOnSubscribe implements ObservableOnSubscribe<VKApiDocument> {


    private File mDoc;

    public UploadFileEventOnSubscribe(File doc) {
        this.mDoc = doc;
    }


    private static final String TAG = "UploadFileEventOnSubscr";

    @Override
    public void subscribe(@NonNull ObservableEmitter<VKApiDocument> e) throws Exception {
        VKApi.docs().uploadWallDocRequest(mDoc).executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKApiDocument doc = ((VKDocsArray) response.parsedModel).get(0);
                e.onNext(doc);
                e.onComplete();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.d(TAG, "onError: " + error.apiError.errorMessage);
                e.onError(error.httpError);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });
    }
}