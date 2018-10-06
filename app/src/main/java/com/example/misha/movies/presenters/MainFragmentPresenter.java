package com.example.misha.movies.presenters;


import android.text.TextUtils;

import com.example.misha.movies.R;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.mvp.MainFragmentVP;


import data.ListMoviesData;

public class MainFragmentPresenter implements MainFragmentVP.Presenter {

    private MainFragmentVP.View view;
    private final MovieModel model;
    private String queryString;
    private int queryPage;
    private int totalPages;

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
        String queryString = view.getSearchQuery();
        if (TextUtils.isEmpty(queryString)) {
            view.showToast(R.string.empty_values);
            return;
        }
        this.queryString = queryString;
        this.queryPage = 1;
        view.showProgress();
        model.searchMovies(new MovieModel.Query(queryString, queryPage), new MovieModel.SearchMoviesCallback() {
            @Override
            public void onSearch(ListMoviesData list) {
                view.hideProgress();
                if (list.getMovies() == null) {
                    view.showToast(R.string.error_load_data);
                    return;
                }
                totalPages = list.getTotalPages();
                view.showMovies(list.getMovies());
            }
        });

    }

    @Override
    public void loadMore() {
       if (queryPage == totalPages) {
            return;
        }
        view.showProgressList();
        queryPage++;
        model.searchMovies(new MovieModel.Query(queryString, queryPage), new MovieModel.SearchMoviesCallback() {
            @Override
            public void onSearch(ListMoviesData newMovies) {
                if (newMovies == null) {
                    view.showToast(R.string.error_load_data);
                    return;
                }
                view.hideProgressList();
                view.addMovies(newMovies.getMovies());
            }
        });

    }
}
