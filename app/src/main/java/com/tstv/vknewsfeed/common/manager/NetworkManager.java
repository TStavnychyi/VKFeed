package com.tstv.vknewsfeed.common.manager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.tstv.vknewsfeed.MyApplication;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.realm.internal.Util;

public class NetworkManager {

    @Inject
    Context mContext;

    private static final String TAG = "NetworkManager";

    public NetworkManager() {
        MyApplication.getApplicationComponent().inject(this);
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return ((networkInfo != null && networkInfo.isConnected()) || Util.isEmulator());
    }

    public Callable<Boolean> isVkReachableCallable() {
        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    if (!isOnline()) {
                        return false;
                    }

                    URL url = new URL("https://api.vk.com");
                    HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                    urlc.setConnectTimeout(2000);
                    urlc.connect();

                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

    public Observable<Boolean> getNetworkObservable() {
        return Observable.fromCallable(isVkReachableCallable());
    }
}






