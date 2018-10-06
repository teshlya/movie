package com.example.misha.movies.mvp;


import com.example.misha.movies.adapter.MovieAdapter;

import java.util.List;

import data.MovieData;

public interface MainFragmentVP {
    interface View{
        String getSearchQuery();
        void showMovies(List<MovieData> movies);
        void addMovies(List<MovieData> movies);
        void showToast(int resId);
        void showProgress();
        void hideProgress();
        void showProgressList();
        void hideProgressList();
    }

    interface Presenter{
        void search();
        void loadMore();
    }
}
