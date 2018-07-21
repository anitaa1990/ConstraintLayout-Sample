package com.an.constraintlayout.sample.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import com.an.constraintlayout.sample.databinding.SimilarMoviesItemBinding;
import com.an.constraintlayout.sample.model.Movie;
import com.an.constraintlayout.sample.utils.BaseUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMoviesListAdapter extends RecyclerView.Adapter<SimilarMoviesListAdapter.CustomViewHolder> {

    private Context context;
    private List<Movie> movies;
    public SimilarMoviesListAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SimilarMoviesItemBinding itemBinding = SimilarMoviesItemBinding.inflate(layoutInflater, parent, false);
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
        private SimilarMoviesItemBinding binding;

        public CustomViewHolder(SimilarMoviesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            ViewGroup.LayoutParams lp = binding.itemImg.getLayoutParams();
            lp.height = BaseUtils.getAspectHeight(context, lp.height);
            lp.width = BaseUtils.getAspectWidth(context, lp.width);
            binding.itemImg.setLayoutParams(lp);
        }
    }
}
