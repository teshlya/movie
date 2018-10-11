package com.example.misha.movies.model;

import com.example.misha.movies.data.ListMoviesData;

public interface MovieModelCallback {

    public interface PresenterCallback {
        void onFound(ListMoviesData list);
    }

    public interface RetrofitCallback {
        void onFound(ListMoviesData list);
    }
}
