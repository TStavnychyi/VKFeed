package com.tstv.vknewsfeed.ui.holder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.common.utils.Utils;
import com.tstv.vknewsfeed.common.utils.VkListHelper;
import com.tstv.vknewsfeed.model.Place;
import com.tstv.vknewsfeed.model.WallItem;
import com.tstv.vknewsfeed.model.countable.Likes;
import com.tstv.vknewsfeed.model.view.NewsItemFooterViewModel;
import com.tstv.vknewsfeed.model.view.counter.CommentCounterViewModel;
import com.tstv.vknewsfeed.model.view.counter.LikeCounterViewModel;
import com.tstv.vknewsfeed.model.view.counter.RepostCounterViewModel;
import com.tstv.vknewsfeed.rest.api.LikeEventOnSubscribe;
import com.tstv.vknewsfeed.rest.api.WallApi;
import com.tstv.vknewsfeed.rest.model.request.WallGetByIdRequestModel;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.fragment.CommentsFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;


/**
 * Created by tstv on 11.09.2017.
 */

public class NewsItemFooterHolder extends BaseViewHolder<NewsItemFooterViewModel>{

    public static final String POST = "post";

    @BindView(R.id.tv_date)
    public TextView tvDate;

    @BindView(R.id.tv_likes_count)
    public TextView tvLikesCount;

    @BindView(R.id.tv_likes_icon)
    public TextView tvLikesIcon;

    @BindView(R.id.tv_comments_icon)
    public TextView tvCommentsIcon;

    @BindView(R.id.tv_comments_count)
    public TextView tvCommentsCount;

    @BindView(R.id.tv_reposts_icon)
    public TextView tvRepostIcon;

    @BindView(R.id.tv_reposts_count)
    public TextView tvRepostsCount;

    @BindView(R.id.rl_comments)
    public View rlComments;

    @Inject
    MyFragmentManager mFragmentManager;

    @Inject
    Typeface mGoogleFontTypeface;

    @BindView(R.id.rl_likes)
    public View rlLikes;

    @Inject
    WallApi mWallApi;

    private Resources mResources;
    private Context mContext;

    public NewsItemFooterHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
        MyApplication.getApplicationComponent().inject(this);

        mContext = itemView.getContext();
        mResources = mContext.getResources();

        tvLikesIcon.setTypeface(mGoogleFontTypeface);
        tvCommentsIcon.setTypeface(mGoogleFontTypeface);
        tvRepostIcon.setTypeface(mGoogleFontTypeface);

    }


    @Override
    public void bindViewHolder(NewsItemFooterViewModel item) {
        tvDate.setText(Utils.parseDate(item.getDateLong(), mContext));

        bindLikes(item.getLikes());
        bindComments(item.getComments());
        bindReposts(item.getReposts());

        rlComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentManager.addFragment((BaseActivity) view.getContext(),
                        CommentsFragment.newInstance(new Place(String.valueOf(item.getOwnerId()), String.valueOf(item.getId()))),
                        R.id.main_wrapper);
            }
        });

        rlLikes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like(item);
            }
        });

    }

    private void bindLikes(LikeCounterViewModel likes) {
        tvLikesCount.setText(String.valueOf(likes.getCount()));
        tvLikesCount.setTextColor(mResources.getColor(likes.getTextColor()));
        tvLikesIcon.setTextColor(mResources.getColor(likes.getIconColor()));

    }
    private void bindComments(CommentCounterViewModel comments) {
        tvCommentsCount.setText(String.valueOf(comments.getCount()));
        tvCommentsCount.setTextColor(mResources.getColor(comments.getTextColor()));
        tvCommentsIcon.setTextColor(mResources.getColor(comments.getIconColor()));

    }
    private void bindReposts(RepostCounterViewModel reposts) {
        tvRepostsCount.setText(String.valueOf(reposts.getCount()));
        tvRepostsCount.setTextColor(mResources.getColor(reposts.getTextColor()));
        tvRepostIcon.setTextColor(mResources.getColor(reposts.getIconColor()));

    }


    @Override
    public void unbindViewHolder() {

        tvDate.setText(null);
        tvLikesCount.setText(null);
        tvCommentsCount.setText(null);
        tvRepostsCount.setText(null);

    }

    public void saveToDb(RealmObject item) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(item));
    }

    public Observable<LikeCounterViewModel> likeObservable(int ownerId, int postId, Likes likes) {
        return Observable.create(new LikeEventOnSubscribe(POST, ownerId, postId, likes))

                .observeOn(Schedulers.io())
                .flatMap(count -> {

                    return mWallApi.getById(new WallGetByIdRequestModel(ownerId, postId).toMap());
                })
                .flatMap(full -> Observable.fromIterable(VkListHelper.getWallList(full.response)))
                .doOnNext(this::saveToDb)
                .map(wallItem -> new LikeCounterViewModel(wallItem.getLikes()));
    }

    public void like(NewsItemFooterViewModel item) {
        WallItem wallItem = getWallItemFromRealm(item.getId());
        likeObservable(wallItem.getOwnerId(), wallItem.getId(), wallItem.getLikes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(likes -> {
                    item.setLikes(likes);
                    bindLikes(likes);
                }, error -> {
                    error.printStackTrace();
                });
    }

    public WallItem getWallItemFromRealm(int postId) {
        Realm realm = Realm.getDefaultInstance();
        WallItem wallItem = realm.where(WallItem.class)
                .equalTo("id", postId)
                .findFirst();

        return realm.copyFromRealm(wallItem);
    }
}
