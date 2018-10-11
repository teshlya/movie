package com.example.misha.movies.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
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
import com.example.misha.movies.adapter.LoadMoreListener;
import com.example.misha.movies.adapter.MovieAdapter;
import com.example.misha.movies.adapter.OnLoadMoreCallback;
import com.example.misha.movies.model.MovieModel;
import com.example.misha.movies.mvp.SearchMovieFragmentVP;
import com.example.misha.movies.presenters.SearchMovieFragmentPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.example.misha.movies.data.MovieData;

public class SearchMovieFragment extends BaseFragment implements SearchMovieFragmentVP.View {

    @BindView(R.id.search_edit)
    EditText editTextSearch;

    @BindView(R.id.list_movie)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private Context context;
    private ProgressDialog progressDialog;
    private SearchMovieFragmentPresenter presenter;
    private MovieAdapter adapter;
    private LoadMoreListener loadMoreListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = getContext();
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initPresenter();
        initRecyclerView();
        setSearchActionKeyboard();
    }

    private void initPresenter() {
        MovieModel movieModel = new MovieModel();
        presenter = new SearchMovieFragmentPresenter(movieModel);
        presenter.attachView(this);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new MovieAdapter(this.navigationPresenter, recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(initLoadMoreListener());
    }

    private LoadMoreListener initLoadMoreListener(){
        loadMoreListener = new LoadMoreListener(new OnLoadMoreCallback() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }
        });
        return loadMoreListener;
    }

    private void setSearchActionKeyboard() {
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
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public String getSearchQuery() {
        return editTextSearch.getText().toString();
    }

    @Override
    public void showMovies(List<MovieData> movies) {
        adapter.setData(movies);
        recyclerView.smoothScrollToPosition(0);
        loadMoreListener.setLoaded();
    }

    @Override
    public void addMovies(List<MovieData> movies) {
        adapter.addData(movies);
        loadMoreListener.setLoaded();
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