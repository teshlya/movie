package com.example.misha.movies.mvp;


public interface DetailFragmentVP {
    interface View{
        void goBack();
    }

    interface Presenter{
        void attachView(DetailFragmentVP.View view);
        void detachView();
        void goBack();
    }
}
