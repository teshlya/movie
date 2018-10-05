package com.example.misha.movies.presenters;


import com.example.misha.movies.fragment.BaseFragment;
import com.example.misha.movies.fragment.MainFragment;
import com.example.misha.movies.mvp.FragmentNavigation;
import com.example.misha.movies.mvp.MainActivityVP;

import data.MovieData;

public class MainActivityPresenter implements MainActivityVP.Presenter, FragmentNavigation.Presenter {

    private MainActivityVP.View view;

    public MainActivityPresenter(MainActivityVP.View view) {
        this.view = view;
    }

    @Override
    public void openDetailFragment(MovieData movieData, BaseFragment fragment) {
        view.addDetailFragment(movieData, fragment);
    }

    @Override
    public void getMainFragment() {
            view.setMainFragment(new MainFragment());
    }
}
