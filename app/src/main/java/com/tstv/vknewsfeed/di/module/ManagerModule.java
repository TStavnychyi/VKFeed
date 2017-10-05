package com.tstv.vknewsfeed.di.module;

import com.tstv.vknewsfeed.common.manager.MyFragmentManager;
import com.tstv.vknewsfeed.common.manager.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tstv on 14.09.2017.
 */

@Module
public class ManagerModule {
    @Provides
    @Singleton
    MyFragmentManager provideMyFragmentManager() {
        return new MyFragmentManager();
    }

    @Provides
    @Singleton
    NetworkManager provideNetworkManager() {
        return new NetworkManager();
    }
}
