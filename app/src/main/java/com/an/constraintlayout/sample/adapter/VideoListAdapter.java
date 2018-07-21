package com.an.constraintlayout.sample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.an.constraintlayout.sample.R;
import com.an.constraintlayout.sample.databinding.VideoItemBinding;
import com.an.constraintlayout.sample.model.Video;
import com.an.constraintlayout.sample.utils.BaseUtils;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.CustomViewHolder> {

    private String YOUTUBE_API_KEY = "AIzaSyCZY8Vnw_6GcJcESL-NilTZDMSvg9ViLt8";

    private Context context;
    private List<Video> videos;

    public VideoListAdapter(Context context, List<Video> videos) {
        this.videos = videos;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        VideoItemBinding itemBinding = VideoItemBinding.inflate(layoutInflater, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(itemBinding);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, final int position) {
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.binding.vidFrame.setVisibility(View.VISIBLE);
                holder.binding.btnPlay.setImageResource(R.drawable.ic_play);
            }
        };

        holder.binding.youtubeThumbnail.initialize(YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                final Video item = getItem(position);
                youTubeThumbnailLoader.setVideo(item.getKey());
                youTubeThumbnailView.setImageBitmap(null);
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public Video getItem(int position) {
        return videos.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        private VideoItemBinding binding;

        public CustomViewHolder(VideoItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            ViewGroup.LayoutParams lp = binding.youtubeThumbnail.getLayoutParams();
            lp.height = BaseUtils.getAspectHeight(context, lp.height);
            lp.width = BaseUtils.getAspectWidth(context, lp.width);
            binding.youtubeThumbnail.setLayoutParams(lp);

        }
    }
}
