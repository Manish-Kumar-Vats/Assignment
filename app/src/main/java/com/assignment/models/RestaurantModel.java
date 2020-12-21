package com.assignment.models;

public class RestaurantModel {
    String mName, mAddress;

    public RestaurantModel(String mName, String mAddress){
        this.mName = mName;
        this.mAddress = mAddress;
    }
    public void setmAddress(String address) {
        this.mAddress = address;
    }

    public void setmName(String name) {
        this.mName = name;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmName() {
        return mName;
    }

    @Override
    public String toString() {
        return "RestaurantModel{" +
                "mName='" + mName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                '}';
    }
}
