package com.example.misha.movies.mvp;

import com.example.misha.movies.fragment.BaseFragment;

public interface FragmentNavigation {
    interface View {
        void atachPresenter(Presenter presenter);

    }

    interface Presenter {
        void openDetailFragment(BaseFragment fragment);
    }
}
