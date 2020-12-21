package com.assignment;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.assignment.models.RestaurantModel;

import java.util.List;

public class RestaurantLoader  extends AsyncTaskLoader<List<RestaurantModel>> {

    private static final String LOG_TAG = RestaurantLoader.class.getName();

    private String mUrl;


    public RestaurantLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    @Override
    public List<RestaurantModel> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        List<RestaurantModel> restaurantModelList = QueryUtils.fetchArticleData(mUrl);

        return restaurantModelList;

    }
}