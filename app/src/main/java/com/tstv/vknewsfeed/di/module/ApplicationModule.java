package com.tstv.vknewsfeed.di.module;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 11.08.2017.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) mApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Provides
    @Singleton
    Typeface provideGoogleFontTypeface(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "MaterialIcons_Regular.ttf");
    }
}
