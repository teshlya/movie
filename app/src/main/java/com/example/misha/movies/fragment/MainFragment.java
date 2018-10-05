package com.example.misha.movies.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.misha.movies.R;
import com.example.misha.movies.adapter.MovieAdapter;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.mvp.MainFragmentVP;
import com.example.misha.movies.presenters.MainFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import data.MovieData;


public class MainFragment extends BaseFragment implements MainFragmentVP.View {

    @BindView(R.id.search_edit)
    EditText editTextSearch;

    @BindView(R.id.list_movie)
    RecyclerView listMovie;

    private Unbinder unbinder;
    private Context context;
    private ProgressDialog progressDialog;
    private MainFragmentPresenter presenter;
    private MovieAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        context = container.getContext();
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new MovieAdapter(this.navigationPresenter);
        listMovie.setLayoutManager(layoutManager);
        listMovie.setAdapter(adapter);

        MovieModel movieModel = new MovieModel();
        presenter = new MainFragmentPresenter(movieModel);
        presenter.attachView(this);
    }

    @OnClick(R.id.search_button)
    void searchMovie() {
        presenter.search();
    }

    @Override
    public String getSearchQuery() {
        return editTextSearch.getText().toString();
    }

    @Override
    public void showMovies(List<MovieData> movies) {
        adapter.setData(movies);
    }
    @Override
    public void showToast(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressDialog = ProgressDialog.show(context, "", getString(R.string.please_wait));
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }
}
