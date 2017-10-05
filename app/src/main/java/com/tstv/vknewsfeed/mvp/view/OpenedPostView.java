package com.tstv.vknewsfeed.mvp.view;

import com.tstv.vknewsfeed.model.view.NewsItemFooterViewModel;

/**
 * Created by tstv on 20.09.2017.
 */

public interface OpenedPostView extends BaseFeedView {
    void setFooter(NewsItemFooterViewModel viewModel);
}