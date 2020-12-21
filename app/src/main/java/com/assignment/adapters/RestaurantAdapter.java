package com.assignment.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.assignment.R;
import com.assignment.models.RestaurantModel;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<RestaurantModel> {
    public RestaurantAdapter(Context context, List<RestaurantModel> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_restaurant, parent, false);
        }

        RestaurantModel currentRestaurant =  getItem(position);

        TextView restaurantName = convertView.findViewById(R.id.name);

        TextView restaurantAddress = convertView.findViewById(R.id.address);

        restaurantName.setText(currentRestaurant.getmName());

        restaurantAddress.setText(currentRestaurant.getmAddress());

        return convertView;
    }

}

