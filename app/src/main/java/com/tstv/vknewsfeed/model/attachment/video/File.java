package com.tstv.vknewsfeed.model.attachment.video;

import io.realm.RealmObject;

/**
 * Created by tstv on 20.09.2017.
 */


public class File extends RealmObject {
    public String external;

    public String getExternal() {
        return external;
    }

    public void setExternal(String external) {
        this.external = external;
    }
}