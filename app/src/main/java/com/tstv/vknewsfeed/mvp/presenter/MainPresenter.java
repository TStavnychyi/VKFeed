package com.tstv.vknewsfeed.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.tstv.vknewsfeed.CurrentUser;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.common.manager.NetworkManager;
import com.tstv.vknewsfeed.model.Profile;
import com.tstv.vknewsfeed.mvp.view.MainView;
import com.tstv.vknewsfeed.rest.api.UsersApi;
import com.tstv.vknewsfeed.rest.model.request.UsersGetRequestModel;
import com.tstv.vknewsfeed.ui.fragment.BaseFragment;
import com.tstv.vknewsfeed.ui.fragment.BoardFragment;
import com.tstv.vknewsfeed.ui.fragment.InfoFragment;
import com.tstv.vknewsfeed.ui.fragment.MembersFragment;
import com.tstv.vknewsfeed.ui.fragment.MyPostsFragment;
import com.tstv.vknewsfeed.ui.fragment.NewsFeedFragment;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by tstv on 30.08.2017.
 */

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    MyFragmentManager myFragmentManager;

    @Inject
    UsersApi mUserApi;

    @Inject
    NetworkManager mNetworkManager;

    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
            getViewState().startSignIn();
        } else {
            getCurrentUser();
            getViewState().signedIn();
        }
    }

    public MainPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public Observable<Profile> getProfileFromNetwork() {
        return mUserApi.get(new UsersGetRequestModel(CurrentUser.getId()).toMap())
                .flatMap(listFull -> Observable.fromIterable(listFull.response))
                .doOnNext(this::saveToDb);
    }

    private Observable<Profile> getProfileFromDb() {
        return Observable.fromCallable(getListFromRealmCallable());
    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Callable<Profile> getListFromRealmCallable() {
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Profile realmResults = realm.where(Profile.class)
                    .equalTo("id", Integer.parseInt(CurrentUser.getId()))
                    .findFirst();
            return realm.copyFromRealm(realmResults);
        };
    }

    private void getCurrentUser() {
        mNetworkManager.getNetworkObservable()
                .flatMap(aBoolean ->  {
                    if (!CurrentUser.isAuthorized()) {
                        getViewState().startSignIn();
                    }
                    return aBoolean
                            ? getProfileFromNetwork()
                            : getProfileFromDb();
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(profile -> {
                    getViewState().showCurrentUser(profile);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public void drawerItemClick(int id){
        BaseFragment fragment = null;

        switch (id) {
            case 1:
                fragment = new NewsFeedFragment();
                break;
            case 2:
                fragment = new MyPostsFragment();
                break;
            case 4:
                fragment = new MembersFragment();
                break;
            case 5:
                fragment = new BoardFragment();
                break;
            case 6:
                fragment= new InfoFragment();
                break;

        }

        if (fragment != null && !myFragmentManager.isAlreadyContains(fragment)) {
            getViewState().showFragmentFromDrawer(fragment);
        }
    }

}
