package com.example.misha.movies.presenters;


import android.text.TextUtils;

import com.example.misha.movies.R;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.mvp.MainFragmentVP;

import java.util.List;

import data.MovieData;

public class MainFragmentPresenter implements MainFragmentVP.Presenter {

    private MainFragmentVP.View view;
    private final MovieModel model;

    public MainFragmentPresenter(MovieModel model) {
        this.model = model;
    }

    public void attachView(MainFragmentVP.View view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    @Override
    public void search() {
        String query = view.getSearchQuery();
        if (TextUtils.isEmpty(query)) {
            view.showToast(R.string.empty_values);
            return;
        }
        view.showProgress();
        model.searchMovies(query, new MovieModel.SearchMoviesCallback() {
            @Override
            public void onSearch(List<MovieData> movies) {
                view.hideProgress();
                if (movies == null) {
                    view.showToast(R.string.error_load_data);
                    return;
                }
                view.showMovies(movies);
            }
        });

    }
}
