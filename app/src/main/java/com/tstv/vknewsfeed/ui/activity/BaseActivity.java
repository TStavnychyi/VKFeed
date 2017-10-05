package com.tstv.vknewsfeed.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.ui.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseActivity extends MvpAppCompatActivity {

    @BindView(R.id.progress)
    protected ProgressBar mProgressBar;

    @Inject
    MyFragmentManager myFragmentManager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    public FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        ButterKnife.bind(this);

        MyApplication.getApplicationComponent().inject(this);

        setSupportActionBar(toolbar);

        FrameLayout parent = (FrameLayout) findViewById(R.id.main_wrapper);
        getLayoutInflater().inflate(getMainContentLayout(), parent);
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @LayoutRes
    protected abstract int getMainContentLayout();


    public void fragmentOnScreen(BaseFragment baseFragment) {
        setToolbarTitle(baseFragment.createToolbarTitle(this));
        setupFabVisibility(baseFragment.needFab());
    }


    private void setToolbarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }


    public void setContent(BaseFragment fragment) {
        myFragmentManager.setFragment(this, fragment, R.id.main_wrapper);
    }

    public void addContent(BaseFragment fragment) {
        myFragmentManager.addFragment(this, fragment, R.id.main_wrapper);
    }

    public boolean removeCurrentFragment() {
        return myFragmentManager.removeCurrentFragment(this);
    }

    public boolean removeFragment(BaseFragment fragment) {
        return myFragmentManager.removeFragment(this, fragment);
    }

    public void setupFabVisibility(boolean needFab) {
        if (mFab == null) return;

        if (needFab) {
            mFab.show();
        } else {
            mFab.hide();
        }
    }

    @Override
    public void onBackPressed() {
        removeCurrentFragment();
    }
}
