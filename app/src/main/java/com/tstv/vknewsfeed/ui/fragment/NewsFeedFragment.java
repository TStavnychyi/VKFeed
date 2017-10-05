package com.tstv.vknewsfeed.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.mvp.presenter.BaseFeedPresenter;
import com.tstv.vknewsfeed.mvp.presenter.NewsFeedPresenter;
import com.tstv.vknewsfeed.rest.api.WallApi;
import com.tstv.vknewsfeed.ui.activity.CreatePostActivity;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFeedFragment extends BaseFeedFragment {

    @Inject
    WallApi mWallApi;

    @InjectPresenter
    NewsFeedPresenter mPresenter;


    public NewsFeedFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public boolean needFab() {
        return true;
    }




    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_news;
    }

    @Override
    protected BaseFeedPresenter onCreateFeedPresenter() {
        return mPresenter;
    }
}

 /* mWallApi.get(new WallGetRequestModel(-86529522).toMap()).flatMap(new Function<WallGetResponse, ObservableSource<WallItem>>() {
        @Override
        public ObservableSource<WallItem> apply(@NonNull WallGetResponse wallGetResponse) throws Exception {
            return Observable.fromIterable(VkListHelper.getWallList(wallGetResponse.response));
        }
    }).flatMap(new Function<WallItem, ObservableSource<BaseViewModel>>() {
        @Override
        public ObservableSource<BaseViewModel> apply(@NonNull WallItem wallItem) throws Exception {
            List<BaseViewModel> baseItems = new ArrayList<>();
            baseItems.add(new NewsItemHeaderViewModel(wallItem));
            baseItems.add(new NewsFeedItemBodyViewModel(wallItem));
            baseItems.add(new NewsItemFooterViewModel(wallItem));

            return Observable.fromIterable(baseItems);
        }
    })
            .toList()
    .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<BaseViewModel>>() {
        @Override
        public void accept(List<BaseViewModel> baseViewModels) throws Exception {
            mBaseAdapter.setItems(baseViewModels);
        }
    });
*/
