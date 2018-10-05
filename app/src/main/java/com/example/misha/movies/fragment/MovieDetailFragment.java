package com.example.misha.movies.fragment;


import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.movies.Constants;
import com.example.misha.movies.R;

import data.MovieData;

public class MovieDetailFragment extends BaseFragment {

    MovieData movieData;

    public static BaseFragment newInstance(MovieData movieData) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.MOVIE_DATA, movieData);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        movieData = (MovieData) getArguments().getSerializable(Constants.MOVIE_DATA);
    }

}
