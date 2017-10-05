package com.tstv.vknewsfeed.model.view;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.utils.UiHelper;
import com.tstv.vknewsfeed.model.CommentItem;
import com.tstv.vknewsfeed.model.WallItem;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tstv on 20.09.2017.
 */


public class OpenedPostHeaderViewModel extends BaseViewModel {

    private int mId;

    private String mProfileName;
    private String mProfilePhoto;

    private String mText;

    public OpenedPostHeaderViewModel(CommentItem commentItem) {
        this.mId = commentItem.getId();

        this.mProfileName = commentItem.getSenderName();
        this.mProfilePhoto = commentItem.getSenderPhoto();

        this.mText = commentItem.getDisplayText();
    }


    public OpenedPostHeaderViewModel(WallItem wallItem) {
        this.mId = wallItem.getId();

        this.mProfileName = wallItem.getSenderName();
        this.mProfilePhoto = wallItem.getSenderPhoto();

        this.mText = wallItem.getText();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.OpenedPostHeader;
    }


    @Override
    public OpenedPostHeaderHolder onCreateViewHolder(View view) {
        return new OpenedPostHeaderHolder(view);
    }


    public int getId() {
        return mId;
    }

    private String getProfileName() {
        return mProfileName;
    }

    private String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getText() {
        return mText;
    }

    static class OpenedPostHeaderHolder extends BaseViewHolder<OpenedPostHeaderViewModel> {

        @BindView(R.id.civ_profile_image)
        public CircleImageView profilePhoto;

        @BindView(R.id.tv_profile_name)
        public TextView profileName;

        @BindView(R.id.tv_text)
        public TextView text;


        private OpenedPostHeaderHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(OpenedPostHeaderViewModel openedPostHeaderViewModel) {
            Glide.with(itemView.getContext()).load(openedPostHeaderViewModel.getProfilePhoto()).into(profilePhoto);
            profileName.setText(openedPostHeaderViewModel.getProfileName());

            UiHelper.getInstance().setUpTextViewWithVisibility(text, openedPostHeaderViewModel.getText());
        }

        @Override
        public void unbindViewHolder() {
            profilePhoto.setImageBitmap(null);
            profileName.setText(null);

            text.setText(null);
        }

    }
}