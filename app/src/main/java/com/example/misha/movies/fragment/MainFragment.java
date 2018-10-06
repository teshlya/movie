package com.example.misha.movies.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.misha.movies.R;
import com.example.misha.movies.adapter.MovieAdapter;
import com.example.misha.movies.adapter.OnLoadMoreListener;
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
    RecyclerView recyclerView;

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
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(this.navigationPresenter, recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        MovieModel movieModel = new MovieModel();
        presenter = new MainFragmentPresenter(movieModel);
        presenter.attachView(this);

        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.search_button)
    void searchMovie() {
        search();
    }

    private void search() {
        presenter.search();
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public String getSearchQuery() {
        return editTextSearch.getText().toString();
    }

    @Override
    public void showMovies(List<MovieData> movies) {
        adapter.setData(movies);
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void addMovies(List<MovieData> movies) {
        adapter.addData(movies);
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

    @Override
    public void showProgressList() {
        adapter.showProgress();
    }

    @Override
    public void hideProgressList() {
        adapter.hideProgress();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }
}
