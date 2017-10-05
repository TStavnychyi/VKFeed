package com.tstv.vknewsfeed.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
/**
 * Created by tstv on 01.09.2017.
 */
public abstract class BaseFragment extends MvpAppCompatFragment {

    @LayoutRes
    protected abstract int getMainContentLayout();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(getMainContentLayout(), container, false);
    }

    public String createToolbarTitle(Context context) {
        return context.getString(onCreateToolbarTitle());
    }

    @StringRes
    public abstract int onCreateToolbarTitle();

    public boolean needFab() {
        return false;
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}
