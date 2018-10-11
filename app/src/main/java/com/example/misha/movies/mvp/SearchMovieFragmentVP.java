package com.example.misha.movies.mvp;


import java.util.List;

import com.example.misha.movies.data.MovieData;

public interface SearchMovieFragmentVP {
    interface View{
        String getSearchQuery();
        void showMovies(List<MovieData> movies);
        void addMovies(List<MovieData> movies);
        void hideKeyboard();
        void showToast(int resId);
        void showProgress();
        void hideProgress();
        void showProgressList();
        void hideProgressList();
    }

    interface Presenter{
        void attachView(SearchMovieFragmentVP.View view);
        void detachView();
        void search();
        void loadMore();
    }
}
