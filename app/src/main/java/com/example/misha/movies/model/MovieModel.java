package com.example.misha.movies.model;

import android.os.AsyncTask;

import com.example.misha.movies.Constants;
import com.example.misha.movies.utils.RetrofitClient;

import java.util.List;

import data.ListMoviesData;
import data.MovieData;

public class MovieModel {

    public static class Query{
        String string;
        int page;

        public Query(String string, int page) {
            this.string = string;
            this.page = page;
        }

    }

    public void searchMovies(Query query, SearchMoviesCallback callback) {
        SearchMoviesTask searchMoviesTask = new SearchMoviesTask(callback);
        searchMoviesTask.execute(query);
    }

    public interface SearchMoviesCallback {
        void onSearch(ListMoviesData movies);
    }

    class SearchMoviesTask extends AsyncTask<Query, Void, ListMoviesData> {

        private final SearchMoviesCallback callback;

        SearchMoviesTask(SearchMoviesCallback callback) {
            this.callback = callback;
        }

        @Override
        protected ListMoviesData doInBackground(Query... params) {
            Query query = params[0];
            ListMoviesData list = RetrofitClient.getInstance().getMovies(
                    Constants.API_KEY_VALUE,
                    query.string,
                    Constants.LANGUAGE_VALUE,
                    query.page,
                    false
            );
            return list;
        }

        @Override
        protected void onPostExecute(ListMoviesData list) {
            super.onPostExecute(list);
            if (callback != null) {
                callback.onSearch(list);
            }
        }
    }
}
