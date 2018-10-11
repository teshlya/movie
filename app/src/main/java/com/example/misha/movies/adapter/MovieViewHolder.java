package com.example.misha.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misha.movies.Constants;
import com.example.misha.movies.R;
import com.example.misha.movies.data.MovieData;
import com.example.misha.movies.fragment.MovieDetailFragment;
import com.example.misha.movies.mvp.FragmentNavigation;
import com.example.misha.movies.utils.LoadImage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView titleTextView;

    @BindView(R.id.thumbnail)
    ImageView image;

    private Context context;
    private FragmentNavigation.Presenter navigationPresenter;
    private MovieData movieData;

    public MovieViewHolder(@NonNull View itemView, FragmentNavigation.Presenter navigationPresenter) {
        super(itemView);
        context = itemView.getContext();
        this.navigationPresenter = navigationPresenter;
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.click)
    void detailMovie() {
        navigationPresenter.openDetailFragment(MovieDetailFragment.newInstance(movieData));
    }

    public void bind(MovieData movieData) {
        this.movieData = movieData;
        titleTextView.setText(movieData.getTitle());
        if (movieData.getThumbnail() != null)
            LoadImage.getInstance().load(Constants.BASE_URL_IMAGE + context.getResources().getString(R.string.image_size) + movieData.getThumbnail(), image);
    }
}

