package com.tstv.vknewsfeed.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.model.view.NewsItemFooterViewModel;
import com.tstv.vknewsfeed.mvp.presenter.BaseFeedPresenter;
import com.tstv.vknewsfeed.mvp.presenter.OpenedPostPresenter;
import com.tstv.vknewsfeed.mvp.view.OpenedPostView;
import com.tstv.vknewsfeed.ui.holder.NewsItemFooterHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tstv on 20.09.2017.
 */

public class OpenedPostFragment extends BaseFeedFragment implements OpenedPostView {


    @BindView(R.id.rv_footer)
    View mFooter;

    @InjectPresenter
    OpenedPostPresenter mPresenter;

    int id;

    public static OpenedPostFragment newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt("id", id);
        OpenedPostFragment fragment = new OpenedPostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getApplicationComponent().inject(this);

        if (getArguments() != null) {
            this.id = getArguments().getInt("id");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_opened_wall_item;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_opened_post;
    }


    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        mPresenter.setId(id);
        return mPresenter;
    }

    @Override
    public void setFooter(NewsItemFooterViewModel viewModel) {
        mFooter.setVisibility(View.VISIBLE);
        new NewsItemFooterHolder(mFooter).bindViewHolder(viewModel);
    }

}

