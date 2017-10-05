package com.tstv.vknewsfeed.model;

/**
 * Created by tstv on 14.09.2017.
 */
import com.vk.sdk.api.model.Identifiable;

public interface Owner extends Identifiable{

    String getFullName();
    String getPhoto();
}
