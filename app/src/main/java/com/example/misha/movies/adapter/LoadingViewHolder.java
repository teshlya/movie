package com.example.misha.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.misha.movies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public LoadingViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind() {
        progressBar.setIndeterminate(true);
    }
}
