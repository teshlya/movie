package com.example.misha.movies.presenters;

import android.text.TextUtils;

import com.example.misha.movies.R;
import com.example.misha.movies.data.MovieData;
import com.example.misha.movies.data.QueryParameters;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.model.MovieModelCallback;
import com.example.misha.movies.mvp.SearchMovieFragmentVP;
import com.example.misha.movies.data.ListMoviesData;

import java.util.ArrayList;

public class SearchMovieFragmentPresenter implements SearchMovieFragmentVP.Presenter, MovieModelCallback.PresenterCallback {

    private SearchMovieFragmentVP.View view;
    private final MovieModel model;
    private String queryString;
    private int queryPage;
    private int totalPages;

    public SearchMovieFragmentPresenter(MovieModel model) {
        this.model = model;
    }

    @Override
    public void attachView(SearchMovieFragmentVP.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void search() {
        if (!checkQueryEmpty()) {
            view.hideKeyboard();
            startSearch();
        }
    }

    private boolean checkQueryEmpty() {
        initArguments();
        if (TextUtils.isEmpty(queryString)) {
            view.showToast(R.string.empty_values);
            return true;
        }
        return false;
    }

    private void initArguments() {
        queryString = view.getSearchQuery();
        this.queryPage = 1;
    }

    private void startSearch() {
        showProgress();
        model.searchMovies(new QueryParameters(queryString, queryPage), this);
    }

    @Override
    public void loadMore() {
        if (checkExistPage())
            startLoadMore();
    }

    private boolean checkExistPage() {
        if (queryPage == totalPages) {
            return false;
        }
        return true;
    }

    private void startLoadMore() {
        view.showProgressList();
        queryPage++;
        model.searchMovies(new QueryParameters(queryString, queryPage), this);
    }

    @Override
    public void onFound(ListMoviesData list) {
        hideProgress();
        if (list.getMovies() == null) {
            view.showToast(R.string.error_load_data);
        } else {
            totalPages = list.getTotalPages();
            showMovies(list.getMovies());
        }
    }

    private void showMovies(ArrayList<MovieData> list) {
        if (queryPage == 1)
            view.showMovies(list);
        else
            view.addMovies(list);
    }

    private void showProgress() {
        if (queryPage == 1)
            view.showProgress();
        else
            view.showProgressList();
    }

    private void hideProgress() {
        if (queryPage == 1)
            view.hideProgress();
        else
            view.hideProgressList();
    }
}
