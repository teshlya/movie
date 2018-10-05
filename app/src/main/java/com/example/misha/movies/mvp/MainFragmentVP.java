package com.example.misha.movies.mvp;


import java.util.List;

import data.MovieData;

public interface MainFragmentVP {
    interface View{
        String getSearchQuery();
        void showMovies(List<MovieData> movies);
        void showToast(int resId);
        void showProgress();
        void hideProgress();
    }

    interface Presenter{
        void search();
    }
}
