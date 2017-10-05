package com.tstv.vknewsfeed.di.module;

import android.support.v4.view.ViewCompat;

import com.tstv.vknewsfeed.rest.RestClient;
import com.tstv.vknewsfeed.rest.api.AccountApi;
import com.tstv.vknewsfeed.rest.api.BoardApi;
import com.tstv.vknewsfeed.rest.api.GroupsApi;
import com.tstv.vknewsfeed.rest.api.UsersApi;
import com.tstv.vknewsfeed.rest.api.VideoApi;
import com.tstv.vknewsfeed.rest.api.WallApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tstv on 14.09.2017.
 */

@Module
public class RestModule {
    private RestClient mRestClient;


    public RestModule() {
        mRestClient = new RestClient();
    }


    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }

    @Provides
    @Singleton
    public WallApi provideWallApi() {
        return mRestClient.createService(WallApi.class);
    }

    @Provides
    @Singleton
    public UsersApi provideUsersApi() {
        return mRestClient.createService(UsersApi.class);
    }

    @Provides
    @Singleton
    public GroupsApi provideGroupsApi(){
        return mRestClient.createService(GroupsApi.class);
    }

    @Provides
    @ViewCompat.ScrollIndicators
    public BoardApi provideBoardApi(){
        return mRestClient.createService(BoardApi.class);
    }

    @Provides
    @Singleton
    public VideoApi provideVideoApi(){
        return mRestClient.createService(VideoApi.class);
    }

    @Provides
    @Singleton
    public AccountApi provideAccountApi(){return mRestClient.createService(AccountApi.class);}


}
