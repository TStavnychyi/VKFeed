package com.tstv.vknewsfeed.model.view.attachment;

import android.view.View;

import com.tstv.vknewsfeed.model.attachment.Photo;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.ui.holder.attachment.ImageAttachmentHolder;

/**
 * Created by tstv on 19.09.2017.
 */

public class ImageAttachmentViewModel extends BaseViewModel {

    private String mPhotoUrl;
    public boolean needClick = true;

    public ImageAttachmentViewModel(String url) {
        mPhotoUrl = url;

        needClick = false;
    }

    public ImageAttachmentViewModel(Photo photo) {
        mPhotoUrl = photo.getPhoto604();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentImage;
    }

    @Override
    public ImageAttachmentHolder onCreateViewHolder(View view) {
        return new ImageAttachmentHolder(view);
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }
}