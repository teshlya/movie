package com.example.misha.movies.presenters;


import com.example.misha.movies.fragment.BaseFragment;
import com.example.misha.movies.fragment.SearchMovieFragment;
import com.example.misha.movies.mvp.FragmentNavigation;
import com.example.misha.movies.mvp.MainActivityVP;

public class MainActivityPresenter implements MainActivityVP.Presenter, FragmentNavigation.Presenter {

    private MainActivityVP.View view;

    public MainActivityPresenter(MainActivityVP.View view) {
        this.view = view;
    }

    @Override
    public void openDetailFragment(BaseFragment fragment) {
        view.addDetailFragment(fragment);
    }

    @Override
    public void getSearchMovieFragment() {
        view.addSearchMovieFragment(new SearchMovieFragment());
    }
}
