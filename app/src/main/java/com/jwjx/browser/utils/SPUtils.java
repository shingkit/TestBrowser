package com.jwjx.browser.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jwjx.browser.BaseApplication;

/**
 * Created by sj on 2015/8/16 0016.
 */
public class SPUtils {
    private static SPUtils instance=new SPUtils();
    public static SPUtils getInstance(){
        return instance;
    }

    private static SharedPreferences sp=BaseApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);


    public void setIP(String str){
        sp.edit().putString("ip",str).commit();
    }
    public String getIp(){
        return sp.getString("ip","");

    }public void setPath(String str){
        sp.edit().putString("path",str).commit();
    }
    public String getPath(){
        return sp.getString("path","");
    }

    public void setPage(String str){
        sp.edit().putString("page",str).commit();
    }
    public String getPage(){
        return sp.getString("page","");
    }
}
