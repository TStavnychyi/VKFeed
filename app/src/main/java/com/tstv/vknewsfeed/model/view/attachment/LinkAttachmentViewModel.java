package com.tstv.vknewsfeed.model.view.attachment;

import android.view.View;

import com.tstv.vknewsfeed.model.attachment.Link;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.ui.holder.attachment.LinkAttachmentViewHolder;

/**
 * Created by tstv on 20.09.2017.
 */

public class LinkAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public LinkAttachmentViewModel(Link link) {

        if (link.getTitle() == null || link.getTitle().equals("")) {
            if (link.getName() != null) {
                mTitle = link.getName();
            } else {
                mTitle = "Link";
            }
        } else {
            mTitle = link.getTitle();
        }

        mUrl = link.getUrl();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentLink;
    }

    @Override
    public LinkAttachmentViewHolder onCreateViewHolder(View view) {
        return new LinkAttachmentViewHolder(view);
    }



    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }
}
