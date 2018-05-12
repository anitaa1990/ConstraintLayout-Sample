package com.an.constraintlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.constraintlayout.databinding.SimilarMoviesListWithConstraintItemBinding;
import com.an.constraintlayout.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMoviesListWithConstraintAdapter extends RecyclerView.Adapter<SimilarMoviesListWithConstraintAdapter.CustomViewHolder> {

    private Context context;
    private List<Movie> movies;
    public SimilarMoviesListWithConstraintAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SimilarMoviesListWithConstraintItemBinding itemBinding = SimilarMoviesListWithConstraintItemBinding.inflate(layoutInflater, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Movie movie = getItem(position);
        String imageUrl = movie.getPosterPath();
        Picasso.get().load(imageUrl).into(holder.binding.itemImg);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private SimilarMoviesListWithConstraintItemBinding binding;

        public CustomViewHolder(SimilarMoviesListWithConstraintItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}