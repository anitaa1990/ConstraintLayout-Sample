package com.an.constraintlayout.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Pair;
import android.view.View;

import com.an.constraintlayout.R;
import com.an.constraintlayout.adapter.CreditListAdapter;
import com.an.constraintlayout.adapter.SimilarMoviesListAdapter;
import com.an.constraintlayout.adapter.VideoListAdapter;
import com.an.constraintlayout.databinding.DetailWithoutConstraintActivityBinding;
import com.an.constraintlayout.model.Cast;
import com.an.constraintlayout.model.Crew;
import com.an.constraintlayout.model.Movie;
import com.an.constraintlayout.utils.BaseUtils;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import static com.an.constraintlayout.AppConstants.CREDIT_CAST;
import static com.an.constraintlayout.AppConstants.CREDIT_CREW;
import static com.an.constraintlayout.AppConstants.MOVIE_STATUS_RELEASED;

public class DetailWithoutConstraintActivity extends BaseActivity implements View.OnClickListener {


    private DetailWithoutConstraintActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_without_constraint);

        binding.recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.smoothScrollToPosition(1);

        binding.shineButton.setOnClickListener(this);
        binding.expandButton.setPaintFlags(binding.expandButton.getPaintFlags() |  Paint.UNDERLINE_TEXT_FLAG);
        binding.expandButton.setOnClickListener(this);

        loadVideos();
        loadMovieDetail();
        loadCrewDetails();
        loadSimilarMovies();
    }


    private void loadMovieDetail() {
        Movie movie = BaseUtils.getMovieDetails(BaseUtils.getJSONStringFromRaw(getApplicationContext(), R.raw.sample_detail));
        binding.setMovie(movie);
        Picasso.get().load(movie.getPosterPath()).into(binding.image);

        String status = movie.getStatus();
        binding.movieStatus.setItems(Arrays.asList(new String[]{ status }));

        String runTxt = status.equalsIgnoreCase(MOVIE_STATUS_RELEASED) ?
                String.format("%s mins", String.valueOf(movie.getRuntime())) :
                BaseUtils.getFormattedDate(movie.getReleaseDate());
        binding.txtRuntime.setText(runTxt);

        binding.collectionItemPicker.setUseRandomColor(true);
        binding.collectionItemPicker.setItems(movie.getGenreNames());
    }


    private void loadVideos() {
        VideoListAdapter adapter = new VideoListAdapter(getApplicationContext(),
                BaseUtils.getMovieVideos(BaseUtils.getJSONStringFromRaw(getApplicationContext(), R.raw.sample_videos)));
        binding.recyclerView.setAdapter(adapter);
    }


    private void loadCrewDetails() {
        binding.includedLayout.castList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.includedLayout.castList.setLayoutManager(linearLayoutManager1);
        binding.includedLayout.castList.smoothScrollToPosition(1);

        binding.includedLayout.crewList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.includedLayout.crewList.setLayoutManager(linearLayoutManager2);
        binding.includedLayout.crewList.smoothScrollToPosition(1);

        Pair<List<Cast>, List<Crew>> creditPair = BaseUtils.getMovieCast(BaseUtils.getJSONStringFromRaw(getApplicationContext(), R.raw.sample_cast));
        CreditListAdapter creditAdapter = new CreditListAdapter(getApplicationContext(), CREDIT_CAST, creditPair.first, null);
        binding.includedLayout.castList.setAdapter(creditAdapter);

        CreditListAdapter crewAdapter = new CreditListAdapter(this, CREDIT_CREW, null, creditPair.second);
        binding.includedLayout.crewList.setAdapter(crewAdapter);
    }


    private void loadSimilarMovies() {
        binding.includedSimilarLayout.moviesList.setNestedScrollingEnabled(false);
        LinearLayoutManager similarMoviesLayout = new LinearLayoutManager(getApplicationContext());
        similarMoviesLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.includedSimilarLayout.moviesList.setLayoutManager(similarMoviesLayout);
        binding.includedSimilarLayout.moviesList.smoothScrollToPosition(1);

        SimilarMoviesListAdapter movieAdapter = new SimilarMoviesListAdapter(getApplicationContext(),
                BaseUtils.getMovieList(BaseUtils.getJSONStringFromRaw(getApplicationContext(), R.raw.sample_similar_movies)));
        binding.includedSimilarLayout.moviesList.setAdapter(movieAdapter);
    }


    @Override
    public void onClick(View view) {
        if(view == binding.shineButton) {
            if(binding.shineButton.isChecked()) {
                binding.favView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                binding.favView.setBackgroundResource(R.drawable.ic_fav);
            }

        } else if(view == binding.expandButton) {
            if (binding.includedLayout.expandableLayout.isExpanded()) {
                binding.expandButton.setText(getString(R.string.read_more));
                binding.includedLayout.expandableLayout.collapse();
            } else {
                binding.expandButton.setText(getString(R.string.read_less));
                binding.includedLayout.expandableLayout.expand();
            }
        }
    }
}
