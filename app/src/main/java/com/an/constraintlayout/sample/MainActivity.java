package com.an.constraintlayout.sample;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.an.constraintlayout.sample.adapter.CastCrewListAdapter;
import com.an.constraintlayout.sample.adapter.SimilarMoviesListAdapter;
import com.an.constraintlayout.sample.adapter.VideoListAdapter;
import com.an.constraintlayout.sample.databinding.MainActivityBinding;
import com.an.constraintlayout.sample.model.Cast;
import com.an.constraintlayout.sample.model.Crew;
import com.an.constraintlayout.sample.model.Movie;
import com.an.constraintlayout.sample.utils.BaseUtils;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private MainActivityBinding mainActivityBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.expandButton.setPaintFlags(mainActivityBinding.expandButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        mainActivityBinding.expandButton.setOnClickListener(this);
        mainActivityBinding.shineButton.setOnClickListener(this);

        loadVideos();
        loadMovieDetails();
        loadCrewDetails();
        loadSimilarMovies();
    }


    private void loadVideos() {
        mainActivityBinding.listVideos.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mainActivityBinding.listVideos.setLayoutManager(linearLayoutManager);
        mainActivityBinding.listVideos.smoothScrollToPosition(1);

        VideoListAdapter videoListAdapter = new VideoListAdapter(getApplicationContext(),
                BaseUtils.getMovieVideos(getApplicationContext()));
        mainActivityBinding.listVideos.setAdapter(videoListAdapter);
    }


    private void loadMovieDetails() {
        Movie movie = BaseUtils.getMovieDetails(getApplicationContext());
        mainActivityBinding.setMovie(movie);

        Picasso.get().load(movie.getPosterPath()).into(mainActivityBinding.imageBanner);

        String status = movie.getStatus();
        mainActivityBinding.movieStatus.setItems(Arrays.asList(new String[]{status}));

        String runTxt = status.equalsIgnoreCase("Released") ?
                String.format("%s mins", String.valueOf(movie.getRuntime())) :
                BaseUtils.getFormattedDate(movie.getReleaseDate());
        mainActivityBinding.txtRuntime.setText(runTxt);

        mainActivityBinding.collectionItemPicker.setUseRandomColor(true);
        mainActivityBinding.collectionItemPicker.setItems(movie.getGenreNames());
    }


    private void loadCrewDetails() {
        mainActivityBinding.includedLayout.castList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mainActivityBinding.includedLayout.castList.setLayoutManager(linearLayoutManager1);
        mainActivityBinding.includedLayout.castList.smoothScrollToPosition(1);

        mainActivityBinding.includedLayout.crewList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mainActivityBinding.includedLayout.crewList.setLayoutManager(linearLayoutManager2);
        mainActivityBinding.includedLayout.crewList.smoothScrollToPosition(1);

        Pair<List<Cast>, List<Crew>> creditPair = BaseUtils.getMovieCast(getApplicationContext());
        CastCrewListAdapter creditAdapter = new CastCrewListAdapter(getApplicationContext(), "cast", creditPair.first, null);
        mainActivityBinding.includedLayout.castList.setAdapter(creditAdapter);

        CastCrewListAdapter crewAdapter = new CastCrewListAdapter(getApplicationContext(), "crew", null, creditPair.second);
        mainActivityBinding.includedLayout.crewList.setAdapter(crewAdapter);
    }


    private void loadSimilarMovies() {
        mainActivityBinding.includedSimilarLayout.moviesList.setNestedScrollingEnabled(false);
        LinearLayoutManager similarMoviesLayout = new LinearLayoutManager(getApplicationContext());
        similarMoviesLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mainActivityBinding.includedSimilarLayout.moviesList.setLayoutManager(similarMoviesLayout);
        mainActivityBinding.includedSimilarLayout.moviesList.smoothScrollToPosition(1);

        SimilarMoviesListAdapter movieAdapter = new SimilarMoviesListAdapter(getApplicationContext(),
                BaseUtils.getMovieList(getApplicationContext()));
        mainActivityBinding.includedSimilarLayout.moviesList.setAdapter(movieAdapter);
    }


    @Override
    public void onClick(View view) {
        if (view == mainActivityBinding.expandButton) {
            if (mainActivityBinding.includedLayout.expandableLayout.isExpanded()) {
                mainActivityBinding.expandButton.setText(getString(R.string.read_more));
                mainActivityBinding.includedLayout.expandableLayout.collapse();

            } else {
                mainActivityBinding.expandButton.setText(getString(R.string.read_less));
                mainActivityBinding.includedLayout.expandableLayout.expand();
            }

        } else if (view == mainActivityBinding.shineButton) {
            if (mainActivityBinding.shineButton.isChecked()) {
                mainActivityBinding.favView.setBackgroundColor(Color.TRANSPARENT);
            } else {
                mainActivityBinding.favView.setBackgroundResource(R.drawable.ic_fav);
            }

        }
    }
}
