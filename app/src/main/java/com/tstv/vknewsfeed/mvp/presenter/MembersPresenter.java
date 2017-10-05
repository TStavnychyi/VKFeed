package com.tstv.vknewsfeed.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.consts.ApiConstants;
import com.tstv.vknewsfeed.model.Member;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.model.view.MemberViewModel;
import com.tstv.vknewsfeed.mvp.view.BaseFeedView;
import com.tstv.vknewsfeed.rest.api.GroupsApi;
import com.tstv.vknewsfeed.rest.model.request.GroupsGetMembersRequestModel;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by tstv on 16.09.2017.
 */

@InjectViewState
public class MembersPresenter extends BaseFeedPresenter<BaseFeedView>{

    @Inject
    GroupsApi mGroupsApi;

    public MembersPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mGroupsApi.getMembers(new GroupsGetMembersRequestModel(
                ApiConstants.MY_GROUP_ID, count, offset).toMap())
                .flatMap(baseItemResponseFull -> {
                    return Observable.fromIterable(baseItemResponseFull.response.getItems());
                })
                .doOnNext(member -> saveToDb(member))
                .map(member -> new MemberViewModel(member));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .map(member -> new MemberViewModel(member));
    }

    public Callable<List<Member>>  getListFromRealmCallable(){
        return () ->{
            String[] sortFields = {Member.ID};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<Member> results = realm.where(Member.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }
}
