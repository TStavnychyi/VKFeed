package com.tstv.vknewsfeed.ui.holder;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.common.utils.UiHelper;
import com.tstv.vknewsfeed.model.view.NewsItemBodyViewModel;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.fragment.OpenedPostFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by tstv on 02.09.2017.
 */
public class NewsItemBodyHolder extends BaseViewHolder<NewsItemBodyViewModel> {

    @BindView(R.id.tv_text)
    public TextView tvText;

    @BindView(R.id.tv_attachments)
    public TextView tvAttachments;

    @Inject
    protected Typeface mFontGoogle;

    @Inject
    MyFragmentManager myFragmentManager;


    public NewsItemBodyHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        MyApplication.getApplicationComponent().inject(this);

        if (tvAttachments != null) {
            tvAttachments.setTypeface(mFontGoogle);
        }
    }

    @Override
    public void bindViewHolder(NewsItemBodyViewModel item) {
        tvText.setText(item.getText());
        tvAttachments.setText(item.getAttachmentString());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myFragmentManager.addFragment((BaseActivity) view.getContext(),
                        OpenedPostFragment.newInstance(item.getId()),
                        R.id.main_wrapper);

            }
        });
        UiHelper.getInstance().setUpTextViewWithVisibility(tvText, item.getText());
        UiHelper.getInstance().setUpTextViewWithVisibility(tvAttachments, item.getAttachmentString());
    }

    @Override
    public void unbindViewHolder() {
        tvText.setText(null);
        tvAttachments.setText(null);
        itemView.setOnClickListener(null);
    }
}
