package com.tstv.vknewsfeed.model.view;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.common.utils.UiHelper;
import com.tstv.vknewsfeed.model.CommentItem;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.fragment.OpenedCommentFragment;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tstv on 27.09.2017.
 */

public class CommentBodyViewModel extends BaseViewModel {

    private int mId;
    private String mText;
    private String mAttachmentsString;

    public CommentBodyViewModel(CommentItem commentItem) {
        this.mId = commentItem.getId();
        this.mText = commentItem.getDisplayText();
        this.mAttachmentsString = commentItem.getDisplayAttachmentsString();
    }

    @Override
    public boolean isItemDecorator() {
        return true;
    }

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.CommentBody;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new CommentBodyViewHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public String getAttachmentsString() {
        return mAttachmentsString;
    }

    public static class CommentBodyViewHolder extends BaseViewHolder<CommentBodyViewModel> {

        @Inject
        Typeface mGoogleFont;

        @Inject
        MyFragmentManager mFragmentManager;

        @BindView(R.id.tv_text)
        public TextView tvText;

        @BindView(R.id.tv_attachments)
        public TextView tvAttachments;

        public CommentBodyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            MyApplication.getApplicationComponent().inject(this);
            tvAttachments.setTypeface(mGoogleFont);
        }

        @Override
        public void bindViewHolder(CommentBodyViewModel commentBodyViewModel) {

            UiHelper.getInstance().setUpTextViewWithMessage(tvText, commentBodyViewModel.getText(), "");
            UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, commentBodyViewModel.getAttachmentsString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                            OpenedCommentFragment.newInstance(commentBodyViewModel.getId()), R.id.main_wrapper);
                }
            });
        }

        @Override
        public void unbindViewHolder() {
            tvText.setText(null);
            tvAttachments.setText(null);
        }
    }
}