package com.tstv.vknewsfeed.ui.holder.attachment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tstv.vknewsfeed.MyApplication;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.model.view.attachment.ImageAttachmentViewModel;
import com.tstv.vknewsfeed.ui.activity.BaseActivity;
import com.tstv.vknewsfeed.ui.fragment.ImageFragment;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tstv on 19.09.2017.
 */

public class ImageAttachmentHolder extends BaseViewHolder<ImageAttachmentViewModel> {


    @BindView(R.id.iv_attachment_image)
    public ImageView image;

    @Inject
    MyFragmentManager mFragmentManager;


    public ImageAttachmentHolder(View itemView) {
        super(itemView);

        MyApplication.getApplicationComponent().inject(this);

        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindViewHolder(ImageAttachmentViewModel imageAttachmentViewModel) {

        if (imageAttachmentViewModel.needClick) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager.addFragment((BaseActivity) itemView.getContext(),
                            ImageFragment.newInstance(imageAttachmentViewModel.getPhotoUrl()), R.id.main_wrapper);
                }
            });
        }

        Glide.with(itemView.getContext()).load(imageAttachmentViewModel.getPhotoUrl()).into(image);


    }

    @Override
    public void unbindViewHolder() {

        itemView.setOnClickListener(null);
        image.setImageBitmap(null);
    }

}
