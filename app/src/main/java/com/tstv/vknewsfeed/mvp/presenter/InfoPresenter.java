package com.tstv.vknewsfeed.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.consts.ApiConstants;
import com.tstv.vknewsfeed.model.Group;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.model.view.InfoContactsViewModel;
import com.tstv.vknewsfeed.model.view.InfoLinksViewModel;
import com.tstv.vknewsfeed.model.view.InfoStatusViewModel;
import com.tstv.vknewsfeed.mvp.view.BaseFeedView;
import com.tstv.vknewsfeed.rest.api.GroupsApi;
import com.tstv.vknewsfeed.rest.model.request.GroupsgetByIdRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by tstv on 17.09.2017.
 */

@InjectViewState
public class InfoPresenter extends BaseFeedPresenter<BaseFeedView> {

    @Inject
    GroupsApi mGroupsApi;

    public InfoPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupsApi.getById(new GroupsgetByIdRequestModel(ApiConstants.MY_GROUP_ID).toMap())
        .flatMap(listFull -> Observable.fromIterable(listFull.response))
        .doOnNext(this::saveToDb)
        .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(group -> Observable.fromIterable(parsePojoModel(group)));
    }

    private List<BaseViewModel> parsePojoModel(Group group){
        List<BaseViewModel> items = new ArrayList<>();
        items.add(new InfoStatusViewModel(group));
        items.add(new InfoContactsViewModel());
        items.add(new InfoLinksViewModel());

        return items;
    }

    public Callable<Group> getListFromRealmCallable(){
        return () -> {
            Realm realm = Realm.getDefaultInstance();
            Group result = realm.where(Group.class)
                    .equalTo("id", Math.abs(ApiConstants.MY_GROUP_ID))
                    .findFirst();

            return realm.copyFromRealm(result);
        };
    }
}
