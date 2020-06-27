package com.findmore.myshop.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.findmore.myshop.models.HomeData;
import com.findmore.myshop.models.UserProfile;
import com.findmore.myshop.utils.LogUtils;

import java.io.InputStream;

/**
 * Created by Shivayogi Hiremath on 06,June,2020
 *
 */
public class ReadJsonFile {

    public static HomeData getAssetJsonHomeData(Context context) {
        String json = null;
        HomeData jsonresp=null;
        try {
            InputStream is = context.getAssets().open("HomeData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            jsonresp= gson.fromJson(json, HomeData.class);

            Gson g = new Gson();
            HomeData p = g.fromJson(json, HomeData.class);
            LogUtils.e("data:"+json);
            LogUtils.e("parsed_data:"+jsonresp.toString());

            return p;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static UserProfile getProfileData(Context context) {
        String json = null;
        UserProfile jsonresp=null;
        try {
            InputStream is = context.getAssets().open("UserProfile.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Gson gson = new Gson();
            jsonresp= gson.fromJson(json, UserProfile.class);

            Gson g = new Gson();
            UserProfile p = g.fromJson(json, UserProfile.class);
            LogUtils.e("UserProfile_data:"+jsonresp.toString());

            return p;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
