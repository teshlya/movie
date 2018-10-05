package com.example.misha.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misha.movies.Constants;
import com.example.misha.movies.LoadImage;
import com.example.misha.movies.R;
import com.example.misha.movies.fragment.MovieDetailFragment;
import com.example.misha.movies.mvp.FragmentNavigation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.MovieData;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private FragmentNavigation.Presenter navigationPresenter;
    private ArrayList<MovieData> list = new ArrayList<>();
    private Context context;

    public MovieAdapter(FragmentNavigation.Presenter navigationPresenter) {
        this.navigationPresenter = navigationPresenter;
    }

    public void setData(List<MovieData> list) {
        this.list.clear();
        this.list.addAll(list);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.thumbnail)
        ImageView image;

        MovieData movieData;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
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
}
