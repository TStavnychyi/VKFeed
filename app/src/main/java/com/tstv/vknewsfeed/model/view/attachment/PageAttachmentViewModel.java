package com.tstv.vknewsfeed.model.view.attachment;

import android.view.View;

import com.tstv.vknewsfeed.model.attachment.Page;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.ui.holder.attachment.PageAttachmentHolder;

/**
 * Created by tstv on 20.09.2017.
 */

public class PageAttachmentViewModel extends BaseViewModel {

    private String mTitle;
    private String mUrl;

    public PageAttachmentViewModel(Page page) {
        mUrl = page.getUrl();
        mTitle = page.getTitle();
    }

    public String getTitle() {
        return mTitle;
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentPage;
    }

    @Override
    public PageAttachmentHolder onCreateViewHolder(View view) {
        return new PageAttachmentHolder(view);
    }



    public String getmUrl() {
        return mUrl;
    }
}
