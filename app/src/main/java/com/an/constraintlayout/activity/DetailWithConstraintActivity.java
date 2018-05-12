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
import com.an.constraintlayout.adapter.CreditListWithConstraintAdapter;
import com.an.constraintlayout.adapter.SimilarMoviesListWithConstraintAdapter;
import com.an.constraintlayout.adapter.VideoListWithConstraintAdapter;
import com.an.constraintlayout.databinding.DetailWithConstraintActivityBinding;
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

public class DetailWithConstraintActivity extends BaseActivity implements View.OnClickListener {

    private DetailWithConstraintActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_with_constraint);

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
        VideoListWithConstraintAdapter adapter = new VideoListWithConstraintAdapter(getApplicationContext(),
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
        CreditListWithConstraintAdapter creditAdapter = new CreditListWithConstraintAdapter(getApplicationContext(), CREDIT_CAST, creditPair.first, null);
        binding.includedLayout.castList.setAdapter(creditAdapter);

        CreditListWithConstraintAdapter crewAdapter = new CreditListWithConstraintAdapter(getApplicationContext(), CREDIT_CREW, null, creditPair.second);
        binding.includedLayout.crewList.setAdapter(crewAdapter);
    }


    private void loadSimilarMovies() {
        binding.includedSimilarLayout.moviesList.setNestedScrollingEnabled(false);
        LinearLayoutManager similarMoviesLayout = new LinearLayoutManager(getApplicationContext());
        similarMoviesLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.includedSimilarLayout.moviesList.setLayoutManager(similarMoviesLayout);
        binding.includedSimilarLayout.moviesList.smoothScrollToPosition(1);

        SimilarMoviesListWithConstraintAdapter movieAdapter = new SimilarMoviesListWithConstraintAdapter(getApplicationContext(),
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
