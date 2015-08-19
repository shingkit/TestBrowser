package com.jwjx.browser;

import android.app.Application;
import android.content.Context;

/**
 * Created by sj on 2015/8/16 0016.
 */
public class BaseApplication extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static Context getContext() {
        return context;
    }
}
