package com.tstv.vknewsfeed.model.view;

import android.view.View;
import android.widget.TextView;

import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.model.Place;
import com.tstv.vknewsfeed.model.Topic;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.fragment.TopicCommentsFragment;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tstv on 17.09.2017.
 */
public class TopicViewModel extends  BaseViewModel{

    private int mid;
    private int mGroupid;
    private String mTitle;
    private String mCommentsCount;

    public TopicViewModel() {
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Topic;
    }

    public TopicViewModel(Topic topic) {
        this.mid = topic.getId();
        this.mGroupid = topic.getGroupId();
        this.mTitle = topic.getTitle();
        this.mCommentsCount = topic.getComments() + " сообщений";
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new TopicViewHolder(view);
    }

    public int getMid() {
        return mid;
    }

    public int getGroupid() {
        return mGroupid;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCommentsCount() {
        return mCommentsCount;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public void setGroupid(int mGroupid) {
        this.mGroupid = mGroupid;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setCommentsCount(String mCommentsCount) {
        this.mCommentsCount = mCommentsCount;
    }

    public static class TopicViewHolder extends BaseViewHolder<TopicViewModel> {

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.tv_title)
        public TextView tvTitle;

        @BindView(R.id.tv_comments_count)
        public TextView tvCommentsCount;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
        }

        @Override
        public void bindViewHolder(TopicViewModel topicViewModel) {
            tvTitle.setText(topicViewModel.getTitle());
            tvCommentsCount.setText(topicViewModel.getCommentsCount());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) view.getContext(),
                            TopicCommentsFragment.newInstance(new Place(String.valueOf(topicViewModel.getGroupid()), String.valueOf(topicViewModel.getMid()))),
                            R.id.main_wrapper);
                }
            });

        }

        @Override
        public void unbindViewHolder() {
            tvTitle.setText(null);
            tvCommentsCount.setText(null);

        }
    }
}
