package com.example.misha.movies.utils;

import com.example.misha.movies.Constants;
import com.example.misha.movies.api.APIService;

import java.io.IOException;
import java.util.List;

import data.ListMoviesData;
import data.MovieData;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitClient getInstance() {
        return new RetrofitClient();
    }

    private APIService getRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIService.class);
    }

    public ListMoviesData getMovies(String apiKey,
                                    String query,
                                    String language,
                                    int page,
                                    boolean include_adult){
        ListMoviesData list = null;
        try {
            list = getRetrofitClient().getMovies(apiKey, query, language, page, include_adult)
                    .execute()
                    .body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
