package com.tstv.vknewsfeed.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.tstv.vknewsfeed.model.Profile;
import com.tstv.vknewsfeed.ui.fragment.BaseFragment;


/**
 * Created by tstv on 30.08.2017.
 */

public interface MainView extends MvpView {
    void startSignIn();
    void signedIn();
    void showCurrentUser(Profile profile);
    void showFragmentFromDrawer(BaseFragment baseFragment);
}
