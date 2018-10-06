package com.example.misha.movies.utils;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class LoadImage {
    public static LoadImage getInstance() {
        return new LoadImage();
    }
//82530
    public void load(String url, ImageView view) {
        Picasso.with(view.getContext())
                .load(url)
                .into(view, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }
}
