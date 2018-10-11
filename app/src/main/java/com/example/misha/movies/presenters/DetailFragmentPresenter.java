package com.example.misha.movies.presenters;

import com.example.misha.movies.mvp.DetailFragmentVP;

public class DetailFragmentPresenter implements DetailFragmentVP.Presenter {

    private DetailFragmentVP.View view;

    @Override
    public void attachView(DetailFragmentVP.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void goBack() {
        view.goBack();
    }
}
