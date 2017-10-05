package com.tstv.vknewsfeed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tstv.vknewsfeed.R;

/**
 * Created by tstv on 14.09.2017.
 */

public class MyPostsFragment extends NewsFeedFragment {

    public MyPostsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setEnableIdFiltering(true);
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_my_posts;
    }


}