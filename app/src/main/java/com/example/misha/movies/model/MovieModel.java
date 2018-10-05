package com.example.misha.movies.model;

import android.os.AsyncTask;

import com.example.misha.movies.Constants;
import com.example.misha.movies.RetrofitClient;

import java.util.List;

import data.MovieData;

public class MovieModel {


    public void searchMovies(String query, SearchMoviesCallback callback) {
        SearchMoviesTask searchMoviesTask = new SearchMoviesTask(callback);
        searchMoviesTask.execute(query);
    }

    public interface SearchMoviesCallback {
        void onSearch(List<MovieData> movies);
    }

    class SearchMoviesTask extends AsyncTask<String, Void, List<MovieData>> {

        private final SearchMoviesCallback callback;

        SearchMoviesTask(SearchMoviesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected List<MovieData> doInBackground(String... params) {
            String query = params[0];
            List<MovieData> list = RetrofitClient.getInstance().getMovies(
                    Constants.API_KEY_VALUE,
                    query,
                    Constants.LANGUAGE_VALUE,
                    1,
                    false
            );
            return list;
        }

        @Override
        protected void onPostExecute(List<MovieData> list) {
            super.onPostExecute(list);
            if (callback != null) {
                callback.onSearch(list);
            }
        }
    }
}
