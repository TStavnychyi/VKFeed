package com.tstv.vknewsfeed.model.view.attachment;

import android.view.View;

import com.tstv.vknewsfeed.common.utils.Utils;
import com.tstv.vknewsfeed.model.attachment.doc.Doc;
import com.tstv.vknewsfeed.model.attachment.doc.Size;
import com.tstv.vknewsfeed.model.view.BaseViewModel;
import com.tstv.vknewsfeed.ui.holder.attachment.DocImageAttachmentHolder;

import java.util.List;

/**
 * Created by tstv on 20.09.2017.
 */

public class DocImageAttachmentViewModel extends BaseViewModel {


    private String mTitle;
    private String mSize;
    private String mExt;

    private String mImage;

    private String mUrl;


    public DocImageAttachmentViewModel(Doc doc) {
        if (doc.getTitle().equals("")) {
            mTitle = "Document";
        } else {
            mTitle = Utils.removeExtFromText(doc.getTitle());
        }

        mUrl = doc.getUrl();

        mSize = Utils.formatSize(doc.getSize());

        mExt = "." + doc.getExt();

        List<Size> sizes = doc.getPreview().getPhoto().getSizes();
        mImage = sizes.get(sizes.size() - 1).getSrc();
    }




    @Override
    public LayoutTypes getType() {
        return LayoutTypes.AttachmentDocImage;
    }

    @Override
    public DocImageAttachmentHolder onCreateViewHolder(View view) {
        return new DocImageAttachmentHolder(view);
    }



    public String getmUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSize() {
        return mSize;
    }

    public String getExt() {
        return mExt;
    }

    public String getImage() {
        return mImage;
    }
}
