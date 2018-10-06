package com.example.misha.movies.presenters;


import android.text.TextUtils;

import com.example.misha.movies.R;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.mvp.DetailFragmentVP;
import com.example.misha.movies.mvp.MainFragmentVP;

import java.util.List;

import data.MovieData;

public class DetailFragmentPresenter implements DetailFragmentVP.Presenter {

    private DetailFragmentVP.View view;

    public DetailFragmentPresenter(){
    }

    public void attachView(DetailFragmentVP.View view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

}
