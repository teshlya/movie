package com.example.misha.movies.mvp;


import com.example.misha.movies.fragment.BaseFragment;

import data.MovieData;

public interface MainActivityVP {
    interface View{
        void setMainFragment(BaseFragment fragment);
        void addDetailFragment(BaseFragment fragment);
    }

    interface Presenter{

        void getMainFragment();

    }
}
