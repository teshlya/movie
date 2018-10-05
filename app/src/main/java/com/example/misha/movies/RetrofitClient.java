package com.example.misha.movies;

import com.example.misha.movies.api.APIService;

import java.io.IOException;
import java.util.List;

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

    public List<MovieData> getMovies(String apiKey,
                                     String query,
                                     String language,
                                     int page,
                                     boolean include_adult){
        List<MovieData> list = null;
        try {
            list = getRetrofitClient().getMovies(apiKey, query, language, page, include_adult)
                    .execute()
                    .body()
                    .getMovies();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
