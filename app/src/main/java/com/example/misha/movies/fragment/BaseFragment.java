package com.example.misha.movies.fragment;

import android.support.v4.app.Fragment;

import com.example.misha.movies.mvp.FragmentNavigation;


public abstract class BaseFragment extends Fragment implements FragmentNavigation.View {

    protected FragmentNavigation.Presenter navigationPresenter;

    @Override
    public void atachPresenter(FragmentNavigation.Presenter presenter) {
        navigationPresenter = presenter;
    }

}