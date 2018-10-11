package com.example.misha.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.misha.movies.fragment.BaseFragment;
import com.example.misha.movies.mvp.MainActivityVP;
import com.example.misha.movies.presenters.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityVP.View {

    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainActivityPresenter(this);
        presenter.getSearchMovieFragment();
    }

    @Override
    public void addSearchMovieFragment(BaseFragment fragment) {
        fragment.atachPresenter(presenter);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void addDetailFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
