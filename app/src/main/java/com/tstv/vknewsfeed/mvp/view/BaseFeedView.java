package com.tstv.vknewsfeed.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.tstv.vknewsfeed.model.view.BaseViewModel;

import java.util.List;
/**
 * Created by tstv on 12.09.2017.
 */
public interface BaseFeedView extends MvpView {

    void showRefreshing();

    void hideRefreshing();

    void showListProgress();

    void hideListProgress();

    void showError(String message);

    void setItems(List<BaseViewModel> items);

    void addItems(List<BaseViewModel> items);
}
