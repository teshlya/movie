package com.example.misha.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.misha.movies.Constants;
import com.example.misha.movies.utils.LoadImage;
import com.example.misha.movies.R;
import com.example.misha.movies.fragment.MovieDetailFragment;
import com.example.misha.movies.mvp.FragmentNavigation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import data.MovieData;

import static com.example.misha.movies.Constants.VIEW_TYPE_LOADING;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentNavigation.Presenter navigationPresenter;
    private ArrayList<MovieData> list = new ArrayList<>();
    private Context context;
    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public MovieAdapter(FragmentNavigation.Presenter navigationPresenter, RecyclerView recyclerView) {
        this.navigationPresenter = navigationPresenter;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public void setData(List<MovieData> list) {
        isLoading = false;
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? VIEW_TYPE_LOADING : Constants.VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        context = viewGroup.getContext();
        if (viewType == Constants.VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.movie_item, viewGroup, false);
            return new MovieViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, viewGroup, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MovieViewHolder) {
            ((MovieViewHolder) holder).bind(list.get(position));
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setLoaded() {
        isLoading = false;
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
        list.addAll(movies);
        notifyDataSetChanged();
        setLoaded();
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

    public class LoadingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
