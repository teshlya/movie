package com.example.misha.movies.api;


import com.example.misha.movies.Constants;

import com.example.misha.movies.data.ListMoviesData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET(Constants.SEARCH)
    Call<ListMoviesData> getMovies(@Query(Constants.API_KEY) String apiKey,
                                   @Query(Constants.QUERY) String query,
                                   @Query(Constants.LANGUAGE) String language,
                                   @Query(Constants.PAGE) int page,
                                   @Query(Constants.INCLUDE_ADULT) Boolean include_adult);
}
