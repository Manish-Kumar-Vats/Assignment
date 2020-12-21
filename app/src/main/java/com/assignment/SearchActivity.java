package com.assignment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.assignment.adapters.RestaurantAdapter;
import com.assignment.databinding.ActivitySearchBinding;
import com.assignment.models.RestaurantModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RestaurantModel>> {

    private static final String API_URL = "https://developers.zomato.com/api/v2.1/search?q=";

    private static final int LOADER_ID = 1;
    ActivitySearchBinding activitySearchBinding;
    List<RestaurantModel> restaurantModelList = new ArrayList<RestaurantModel>();

    TextView emptyListTextView;
    ListView listView;
    private RestaurantAdapter restaurantAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);


        restaurantAdapter = new RestaurantAdapter(this, new ArrayList<RestaurantModel>());

        activitySearchBinding.listView.setAdapter(restaurantAdapter);
        activitySearchBinding.listView.setEmptyView(emptyListTextView);

        activitySearchBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                RestaurantModel restaurant = restaurantAdapter.getItem(position);

                Toast.makeText(getApplicationContext(), "ccc", Toast.LENGTH_LONG).show();

            }
        });


        Context context = this;
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {

            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            emptyListTextView.setText("No internet connection.");
        }

    }


    @NonNull
    @Override
    public Loader<List<RestaurantModel>> onCreateLoader(int id, @Nullable Bundle args) {

        Uri baseUri = Uri.parse(API_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        String temp = String.valueOf(activitySearchBinding.searchBar.getQuery());
        uriBuilder.appendPath("cake");

        Log.i("llll", uriBuilder.toString());
        return new RestaurantLoader(this, uriBuilder.toString());
//        return new NewsLoader(this, API_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<RestaurantModel>> loader, List<RestaurantModel> data) {
        emptyListTextView.setText("No news articles found.");
        restaurantAdapter.clear();

        if (data != null && !data.isEmpty()) {
            restaurantAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<RestaurantModel>> loader) {
        restaurantAdapter.clear();
    }


}
