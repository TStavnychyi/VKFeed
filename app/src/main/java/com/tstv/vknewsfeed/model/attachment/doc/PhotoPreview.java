package com.tstv.vknewsfeed.model.attachment.doc;

/**
 * Created by tstv on 20.09.2017.
 */

import io.realm.RealmList;
import io.realm.RealmObject;
public class PhotoPreview extends RealmObject {
    RealmList<Size> sizes;

    public RealmList<Size> getSizes() {
        return sizes;
    }

    public void setSizes(RealmList<Size> sizes) {
        this.sizes = sizes;
    }
}