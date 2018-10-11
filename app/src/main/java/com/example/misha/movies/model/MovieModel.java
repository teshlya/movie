package com.example.misha.movies.model;

import com.example.misha.movies.Constants;
import com.example.misha.movies.data.QueryParameters;
import com.example.misha.movies.utils.RetrofitClient;

import com.example.misha.movies.data.ListMoviesData;

public class MovieModel implements MovieModelCallback.RetrofitCallback{

    MovieModelCallback.PresenterCallback callback;

    public void searchMovies(QueryParameters query, MovieModelCallback.PresenterCallback callback) {
        this.callback = callback;
        QueryParameters parameters = getParameters(query);
        RetrofitClient.getInstance().getMovies(parameters, this);
    }

    private QueryParameters getParameters(QueryParameters query) {
        return new QueryParameters(query,
                Constants.API_KEY_VALUE,
                Constants.LANGUAGE_VALUE,
                false);
    }

    @Override
    public void onFound(ListMoviesData list) {
        callback.onFound(list);
    }
}
