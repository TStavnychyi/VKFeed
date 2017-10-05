package com.tstv.vknewsfeed.di.component;

import com.tstv.vknewsfeed.common.manager.NetworkManager;
import com.tstv.vknewsfeed.di.module.ApplicationModule;
import com.tstv.vknewsfeed.di.module.ManagerModule;
import com.tstv.vknewsfeed.di.module.RestModule;
import com.tstv.vknewsfeed.model.view.CommentBodyViewModel;
import com.tstv.vknewsfeed.model.view.CommentFooterViewModel;
import com.tstv.vknewsfeed.model.view.TopicViewModel;
import com.tstv.vknewsfeed.mvp.presenter.BoardPresenter;
import com.tstv.vknewsfeed.mvp.presenter.CommentsPresenter;
import com.tstv.vknewsfeed.mvp.presenter.InfoPresenter;
import com.tstv.vknewsfeed.mvp.presenter.MainPresenter;
import com.tstv.vknewsfeed.mvp.presenter.MembersPresenter;
import com.tstv.vknewsfeed.mvp.presenter.NewsFeedPresenter;
import com.tstv.vknewsfeed.mvp.presenter.OpenedCommentPresenter;
import com.tstv.vknewsfeed.mvp.presenter.OpenedPostPresenter;
import com.tstv.vknewsfeed.mvp.presenter.TopicCommentsPresenter;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.activity.MainActivity;
import com.tstv.vknewsfeed.ui.fragment.CommentsFragment;
import com.tstv.vknewsfeed.ui.fragment.NewsFeedFragment;
import com.tstv.vknewsfeed.ui.fragment.OpenedCommentFragment;
import com.tstv.vknewsfeed.ui.fragment.OpenedPostFragment;
import com.tstv.vknewsfeed.ui.fragment.TopicCommentsFragment;
import com.tstv.vknewsfeed.ui.holder.NewsItemBodyHolder;
import com.tstv.vknewsfeed.ui.holder.NewsItemFooterHolder;
import com.tstv.vknewsfeed.ui.holder.attachment.ImageAttachmentHolder;
import com.tstv.vknewsfeed.ui.holder.attachment.VideoAttachmentHolder;

import javax.inject.Singleton;

import dagger.Component;



@Singleton
@Component(
        modules = {ApplicationModule.class, ManagerModule.class, RestModule.class})
public interface ApplicationComponent {

    //activities
    void inject(BaseActivity activity);
    void inject(MainActivity activity);

    //fragments
    void inject(NewsFeedFragment fragment);
    void inject(CommentsFragment fragment);
    void inject(OpenedPostFragment fragment);
    void inject(OpenedCommentFragment fragment);
    void inject(OpenedCommentPresenter presenter);
    void inject(TopicCommentsFragment fragment);

    //holders
    void inject(NewsItemBodyHolder holder);
    void inject(NewsItemFooterHolder holder);
    void inject(ImageAttachmentHolder holder);
    void inject(VideoAttachmentHolder holder);
    void inject(CommentBodyViewModel.CommentBodyViewHolder holder);
    void inject(CommentFooterViewModel.CommentFooterHolder holder);
    void inject(TopicViewModel.TopicViewHolder holder);

    //presenters
    void inject(NewsFeedPresenter presenter);
    void inject(MainPresenter presenter);
    void inject(MembersPresenter presenter);
    void inject(BoardPresenter presenter);
    void inject(InfoPresenter presenter);
    void inject(OpenedPostPresenter presenter);
    void inject(CommentsPresenter presenter);
    void inject(TopicCommentsPresenter presenter);


    //managers
    void inject(NetworkManager manager);


}