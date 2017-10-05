package com.tstv.vknewsfeed.model.view;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.utils.UiHelper;
import com.tstv.vknewsfeed.common.utils.Utils;
import com.tstv.vknewsfeed.model.WallItem;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tstv on 20.09.2017.
 */
public class OpenedPostRepostHeaderViewModel extends BaseViewModel {

    private int mId;

    private String mProfileName;
    private String mProfilePhoto;

    private String mText;

    private long mDate;

    public OpenedPostRepostHeaderViewModel(WallItem forwardedPost) {
        this.mId = forwardedPost.getId();

        this.mProfileName = forwardedPost.getSenderName();
        this.mProfilePhoto = forwardedPost.getSenderPhoto();

        this.mText = forwardedPost.getText();

        this.mDate = forwardedPost.getDate();
    }


    @Override
    public LayoutTypes getType() {
        return LayoutTypes.OpenedPostRepostHeader;
    }

    @Override
    public OpenedPostRepostHolder onCreateViewHolder(View view) {
        return new OpenedPostRepostHolder(view);
    }

    public int getId() {
        return mId;
    }

    public String getProfileName() {
        return mProfileName;
    }

    public String getProfilePhoto() {
        return mProfilePhoto;
    }

    public String getText() {
        return mText;
    }

    public long getDate() {
        return mDate;
    }

    class OpenedPostRepostHolder extends BaseViewHolder<OpenedPostRepostHeaderViewModel> {

        @BindView(R.id.civ_repost_profile_image)
        public CircleImageView repostImage;

        @BindView(R.id.tv_repost_profile_name)
        public TextView repostName;

        @BindView(R.id.tv_repost_text)
        public TextView repostText;

        @BindView(R.id.tv_repost_date)
        public TextView repostDate;


        public OpenedPostRepostHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(OpenedPostRepostHeaderViewModel openedPostRepostHeaderViewModel) {
            Glide.with(itemView.getContext()).load(openedPostRepostHeaderViewModel.getProfilePhoto()).into(repostImage);
            repostName.setText(openedPostRepostHeaderViewModel.getProfileName());

            UiHelper.getInstance().setUpTextViewWithVisibility(repostText, openedPostRepostHeaderViewModel.getText());

            repostDate.setText(Utils.parseDate(openedPostRepostHeaderViewModel.getDate(), itemView.getContext()));
        }

        @Override
        public void unbindViewHolder() {
            repostImage.setImageBitmap(null);
            repostName.setText(null);

            repostText.setText(null);
            repostDate.setText(null);
        }

    }
}
