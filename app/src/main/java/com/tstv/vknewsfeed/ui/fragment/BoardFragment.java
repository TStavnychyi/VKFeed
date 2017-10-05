package com.tstv.vknewsfeed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.mvp.presenter.BaseFeedPresenter;
import com.tstv.vknewsfeed.mvp.presenter.BoardPresenter;

import butterknife.ButterKnife;

/**
 * Created by tstv on 17.09.2017.
 */

public class BoardFragment extends BaseFeedFragment {
    @InjectPresenter
    BoardPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_topics;
    }
}