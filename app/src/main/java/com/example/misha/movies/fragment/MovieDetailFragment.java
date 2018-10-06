package com.example.misha.movies.fragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misha.movies.Constants;
import com.example.misha.movies.utils.LoadImage;
import com.example.misha.movies.R;
import com.example.misha.movies.mvp.DetailFragmentVP;
import com.example.misha.movies.presenters.DetailFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import data.MovieData;

public class MovieDetailFragment extends BaseFragment implements DetailFragmentVP.View {

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.description)
    TextView descriptionTextView;

    @BindView(R.id.thumbnail)
    ImageView image;

    private Unbinder unbinder;
    private DetailFragmentPresenter presenter;
    private Context context;


    public static BaseFragment newInstance(MovieData movieData) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.MOVIE_DATA, movieData);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        MovieData movieData = (MovieData) getArguments().getSerializable(Constants.MOVIE_DATA);
        presenter = new DetailFragmentPresenter();
        presenter.attachView(this);

        titleTextView.setText(movieData.getTitle());
        descriptionTextView.setText(movieData.getDescription());
        if (movieData.getThumbnail() != null)
            LoadImage.getInstance().load(Constants.BASE_URL_IMAGE + Constants.ORIGINAL_SIZE + movieData.getThumbnail(), image);

    }

    @OnClick(R.id.back)
    void goBack() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }
}
