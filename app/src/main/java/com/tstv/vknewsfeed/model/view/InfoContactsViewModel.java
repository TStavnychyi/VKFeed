package com.tstv.vknewsfeed.model.view;

import android.view.View;
import android.widget.RelativeLayout;

import com.tstv.vknewsfeed.R;
import com.tstv.vknewsfeed.ui.holder.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tstv on 17.09.2017.
 */

public class InfoContactsViewModel extends BaseViewModel{
    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoContacts;
    }

    @Override
    public InfoContactsViewHolder onCreateViewHolder(View view) {
        return new InfoContactsViewHolder(view);
    }

    static class InfoContactsViewHolder extends BaseViewHolder<InfoContactsViewModel> {

        @BindView(R.id.rv_contacts)
        RelativeLayout rvContacts;

        public InfoContactsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoContactsViewModel infoContactsViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
