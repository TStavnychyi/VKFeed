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

public class InfoLinksViewModel extends BaseViewModel{

    @Override
    public LayoutTypes getType() {
        return LayoutTypes.InfoLinks;
    }

    @Override
    public InfoLinkViewHolder onCreateViewHolder(View view) {
        return new InfoLinkViewHolder(view);
    }

    static class InfoLinkViewHolder extends BaseViewHolder<InfoLinksViewModel> {

        @BindView(R.id.rv_links)
        RelativeLayout rvLinks;

        public InfoLinkViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void bindViewHolder(InfoLinksViewModel infoLinksViewModel) {

        }

        @Override
        public void unbindViewHolder() {

        }
    }
}
