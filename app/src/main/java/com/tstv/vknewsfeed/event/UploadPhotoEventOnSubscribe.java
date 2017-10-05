package com.tstv.vknewsfeed.event;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKPhotoArray;
import com.vk.sdk.api.photo.VKImageParameters;
import com.vk.sdk.api.photo.VKUploadImage;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
/**
 * Created by tstv on 27.09.2017.
 */


public class UploadPhotoEventOnSubscribe implements ObservableOnSubscribe<VKApiPhoto> {


    private String mPhoto;

    public UploadPhotoEventOnSubscribe(String photo) {
        this.mPhoto = photo;
    }


    @Override
    public void subscribe(@NonNull ObservableEmitter<VKApiPhoto> e) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(mPhoto, options);

        VKApi.uploadWallPhotoRequest(new VKUploadImage(bitmap,
                        VKImageParameters.pngImage()),
                Integer.parseInt(VKAccessToken.currentToken().userId), 0).executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKApiPhoto photo = ((VKPhotoArray) response.parsedModel).get(0);
                e.onNext(photo);
                e.onComplete();
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);
                e.onError(error.httpError);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });
    }
}