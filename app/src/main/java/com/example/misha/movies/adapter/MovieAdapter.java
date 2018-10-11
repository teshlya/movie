package com.example.misha.movies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.misha.movies.R;
import com.example.misha.movies.mvp.FragmentNavigation;

import java.util.ArrayList;
import java.util.List;

import com.example.misha.movies.data.MovieData;

import static com.example.misha.movies.Constants.VIEW_TYPE_ITEM;
import static com.example.misha.movies.Constants.VIEW_TYPE_LOADING;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentNavigation.Presenter navigationPresenter;
    private ArrayList<MovieData> list = new ArrayList<>();

    public MovieAdapter(FragmentNavigation.Presenter navigationPresenter, RecyclerView recyclerView) {
        this.navigationPresenter = navigationPresenter;
    }

    public void setData(List<MovieData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder = null;
        if (viewType == VIEW_TYPE_ITEM) {
            holder = createMovieViewHolder(viewGroup);
        } else if (viewType == VIEW_TYPE_LOADING)
            holder = createLoadingViewHolder(viewGroup);
        return holder;
    }

    private MovieViewHolder createMovieViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view, navigationPresenter);
    }

    private LoadingViewHolder createLoadingViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup
                .getContext()).inflate(R.layout.item_loading, viewGroup, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            ((MovieViewHolder) holder).bind(list.get(position));
        } else if (holder instanceof LoadingViewHolder)
            ((LoadingViewHolder) holder).bind();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void showProgress() {
        list.add(null);
        notifyItemInserted(list.size());
    }

    public void hideProgress() {
        list.remove(list.size() - 1);
        notifyItemRemoved(list.size());
    }

    public void addData(List<MovieData> movies) {
        int listSize = list.size();
        int addCount = movies.size();
        list.addAll(movies);
        notifyItemRangeChanged(listSize, addCount);
    }
}
