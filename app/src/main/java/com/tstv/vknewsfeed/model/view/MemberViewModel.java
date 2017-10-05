package com.tstv.vknewsfeed.model.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.model.Member;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tstv on 16.09.2017.
 */

public class MemberViewModel extends BaseViewModel{

    private int id;

    private int groupId;

    private String photo;

    private String mFullName;

    public MemberViewModel(Member member) {
        this.id = member.getId();
        this.groupId = member.getGroupId();

        this.photo = member.getPhoto();
        this.mFullName = member.getFullName();
    }



    @Override
    public LayoutTypes getType() {
        return LayoutTypes.Member;
    }

    @Override
    protected BaseViewHolder onCreateViewHolder(View view) {
        return new MemberViewHolder(view);
    }

    static class MemberViewHolder extends BaseViewHolder<MemberViewModel> {

        @BindView(R.id.civ_profile_image)
        public CircleImageView civProfilePhoto;

        @BindView(R.id.tv_profile_name)
        public TextView civProfileName;


        public MemberViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }

        @Override
        public void bindViewHolder(MemberViewModel memberViewModel) {
            Context context = itemView.getContext();

            Glide.with(context)
                    .load(memberViewModel.getPhoto())
                    .into(civProfilePhoto);
            civProfileName.setText(memberViewModel.getmFullName());

        }

        @Override
        public void unbindViewHolder() {
            civProfileName.setText(null);
            civProfilePhoto.setImageBitmap(null);

        }
    }

    public int getId() {
        return id;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getPhoto() {
        return photo;
    }

    public String getmFullName() {
        return mFullName;
    }
}
