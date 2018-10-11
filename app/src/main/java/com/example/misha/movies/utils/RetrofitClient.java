package com.example.misha.movies.utils;

import com.example.misha.movies.Constants;
import com.example.misha.movies.api.APIService;

import com.example.misha.movies.data.ListMoviesData;
import com.example.misha.movies.data.QueryParameters;
import com.example.misha.movies.model.MovieModelCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitClient getInstance() {
        return new RetrofitClient();
    }

    public void getMovies(QueryParameters parameters,
                          final MovieModelCallback.RetrofitCallback callback) {
        getCall(parameters)
                .enqueue(new Callback<ListMoviesData>() {
                    @Override
                    public void onResponse(Call<ListMoviesData> call, Response<ListMoviesData> response) {
                        ListMoviesData list = response.body();
                        callback.onFound(list);
                    }

                    @Override
                    public void onFailure(Call<ListMoviesData> call, Throwable t) {

                    }
                });
    }

    private Call<ListMoviesData> getCall(QueryParameters parameters) {
        return getRetrofitClient()
                .getMovies(parameters.getApiKeyValue(),
                        parameters.getQueryString(),
                        parameters.getLanguageValue(),
                        parameters.getPage(),
                        parameters.isIncludeAdult());
    }

    private APIService getRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(APIService.class);
    }
}
