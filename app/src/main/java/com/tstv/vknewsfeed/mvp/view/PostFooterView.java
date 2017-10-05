package com.tstv.vknewsfeed.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.tstv.vknewsfeed.model.WallItem;
import com.tstv.vknewsfeed.model.view.counter.LikeCounterViewModel;

/**
 * Created by tstv on 27.09.2017.
 */

public interface PostFooterView extends MvpView {
    void like(LikeCounterViewModel likes);

    void openComments(WallItem wallItem);
}
