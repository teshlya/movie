package com.example.misha.movies.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misha.movies.Constants;
import com.example.misha.movies.R;
import com.example.misha.movies.mvp.DetailFragmentVP;
import com.example.misha.movies.presenters.DetailFragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.example.misha.movies.data.MovieData;
import com.squareup.picasso.Picasso;

public class MovieDetailFragment extends BaseFragment implements DetailFragmentVP.View {

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.description)
    TextView descriptionTextView;

    @BindView(R.id.thumbnail)
    ImageView image;

    private Unbinder unbinder;
    private DetailFragmentPresenter presenter;

    public static BaseFragment newInstance(MovieData movieData) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(putArgumentsToBundle(movieData));
        return fragment;
    }

    private static Bundle putArgumentsToBundle(MovieData movieData) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.MOVIE_DATA, movieData);
        return bundle;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        initPresenter();
        initView();
    }

    private void initPresenter() {
        presenter = new DetailFragmentPresenter();
        presenter.attachView(this);
    }

    private void initView() {
        MovieData movieData = getArgumentsdFromBundle();
        titleTextView.setText(movieData.getTitle());
        descriptionTextView.setText(movieData.getDescription());
        if (movieData.getThumbnail() != null) {
            Picasso.with(getContext())
                    .load(getUrl(movieData.getThumbnail()))
                    .into(image);
        }
    }

    private MovieData getArgumentsdFromBundle() {
        return (MovieData) getArguments().getSerializable(Constants.MOVIE_DATA);
    }

    private String getUrl(String thumbnail) {
        return Constants.BASE_URL_IMAGE
                + Constants.ORIGINAL_SIZE
                + thumbnail;
    }

    @OnClick(R.id.back)
    void back() {
        presenter.goBack();
    }

    @Override
    public void goBack() {
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unbinder.unbind();
    }
}
