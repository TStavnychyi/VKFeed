package com.tstv.vknewsfeed.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.common.utils.VkListHelper;
import com.tstv.vknewsfeed.model.CommentItem;
import com.tstv.vknewsfeed.model.Place;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.model.view.CommentBodyViewModel;
import com.tstv.vknewsfeed.model.view.CommentFooterViewModel;
import com.tstv.vknewsfeed.model.view.CommentHeaderViewModel;
import com.tstv.vknewsfeed.mvp.view.BaseFeedView;
import com.tstv.vknewsfeed.rest.api.BoardApi;
import com.tstv.vknewsfeed.rest.model.request.BoardGetCommentsRequestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by tstv on 27.09.2017.
 */

@InjectViewState
public class TopicCommentsPresenter extends BaseFeedPresenter<BaseFeedView> {
    private Place mPlace;

    @Inject
    BoardApi mBoardApi;


    public TopicCommentsPresenter() {
        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public Observable<BaseViewModel> onCreateLoadDataObservable(int count, int offset) {
        return mBoardApi.getComments(new BoardGetCommentsRequestModel(
                Integer.parseInt(mPlace.getOwnerId()), Integer.parseInt(mPlace.getPostId()), offset).toMap())
                .flatMap(full -> Observable.fromIterable(VkListHelper.getCommentsList(full.response, true)))
                .doOnNext(commentItem -> commentItem.setPlace(mPlace))
                .doOnNext(this::saveToDb)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }

    @Override
    public Observable<BaseViewModel> onCreateRestoreDataObservable() {
        return Observable.fromCallable(getListFromRealmCallable())
                .flatMap(Observable::fromIterable)
                .filter(commentItem -> commentItem.getPlace().equals(this.mPlace) && commentItem.isFromTopic)
                .flatMap(commentItem -> Observable.fromIterable(parsePojoModel(commentItem)));
    }


    private List<BaseViewModel> parsePojoModel(CommentItem commentItem) {
        List<BaseViewModel> baseItems = new ArrayList<>();
        baseItems.add(new CommentHeaderViewModel(commentItem));
        baseItems.add(new CommentBodyViewModel(commentItem));
        baseItems.add(new CommentFooterViewModel(commentItem));
        return baseItems;
    }


    public Callable<List<CommentItem>> getListFromRealmCallable() {
        return () -> {
            String[] sortFields = {"id"};
            Sort[] sortOrder = {Sort.ASCENDING};

            Realm realm = Realm.getDefaultInstance();
            RealmResults<CommentItem> results = realm.where(CommentItem.class)
                    .findAllSorted(sortFields, sortOrder);
            return realm.copyFromRealm(results);
        };
    }


    public void setPlace(Place place) {
        this.mPlace = place;
    }
}