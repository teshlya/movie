package com.example.misha.movies.mvp;


import com.example.misha.movies.fragment.BaseFragment;

public interface MainActivityVP {
    interface View {
        void addSearchMovieFragment(BaseFragment fragment);
        void addDetailFragment(BaseFragment fragment);
    }

    interface Presenter {
        void getSearchMovieFragment();
    }
}
