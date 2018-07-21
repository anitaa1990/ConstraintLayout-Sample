package com.an.constraintlayout.sample.utils;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.Pair;
import android.view.Display;
import android.view.WindowManager;

import com.an.constraintlayout.sample.R;
import com.an.constraintlayout.sample.model.APIResponse;
import com.an.constraintlayout.sample.model.Cast;
import com.an.constraintlayout.sample.model.Crew;
import com.an.constraintlayout.sample.model.Movie;
import com.an.constraintlayout.sample.model.MovieResponse;
import com.an.constraintlayout.sample.model.Video;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseUtils {



    public static List<String> getGenres(List<Object> genres) {
        List<String> genreNames = new ArrayList<>(genres.size());
        for(Object obj : genres) {
            if(obj instanceof String)
                genreNames.add(Objects.toString(obj, null));
            else genreNames.add(String.valueOf(((Map)obj).get("name")));
        }
        return genreNames;
    }


    public static Movie getMovieDetails(Context context) {
        String jsonString = getJSONStringFromRaw(context, R.raw.sample_detail);
        Type listType = new TypeToken<Movie>() {}.getType();
        Movie movie = new Gson().fromJson(jsonString, listType);
        return movie;
    }


    public static List<Video> getMovieVideos(Context context) {
        String jsonString = getJSONStringFromRaw(context, R.raw.sample_videos);
        Type listType = new TypeToken<List<Video>>() {}.getType();
        List<Video> videos = new Gson().fromJson(jsonString, listType);
        return videos;
    }


    public static Pair<List<Cast>, List<Crew>> getMovieCast(Context context) {
        String jsonString = getJSONStringFromRaw(context, R.raw.sample_cast);
        Type listType = new TypeToken<APIResponse>() {}.getType();
        APIResponse apiResponse = new Gson().fromJson(jsonString, listType);
        return new Pair<>(apiResponse.getCast(), apiResponse.getCrew());
    }


    public static List<Movie> getMovieList(Context context) {
        String jsonString = getJSONStringFromRaw(context, R.raw.sample_similar_movies);
        Type listType = new TypeToken<MovieResponse>() {}.getType();
        MovieResponse apiResponseMessage = new Gson().fromJson(jsonString, listType);
        return apiResponseMessage.getResults();
    }


    private static String getJSONStringFromRaw(Context context, int rawId) {
        InputStream content = context.getResources().openRawResource(rawId);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
        String respString = "";
        try {
            String s = "";
            while ((s = buffer.readLine()) != null) {
                respString += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respString;
    }


    public static int getScreenWidth(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        int width1;
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            width1 = size.x;
        } else {
            width1 = display.getWidth();
        }

        return width1;
    }

    public static int getScreenHeight(Context mContext) {
        WindowManager wm = (WindowManager) mContext.getSystemService("window");
        Display display = wm.getDefaultDisplay();
        int height1;
        if (Build.VERSION.SDK_INT > 12) {
            Point size = new Point();
            display.getSize(size);
            height1 = size.y;
        } else {
            height1 = display.getHeight();
        }

        return height1;
    }


    public static int getAspectWidth(Context context,
                                     int width) {
        int screenWidth = getScreenWidth(context);

        Double aspectWidth = (double) ((width * 100) / screenWidth);
        Double newWidth = Math.ceil((aspectWidth * screenWidth) / 100);

        return newWidth.intValue();
    }


    public static int getAspectHeight(Context context,
                                      int height) {
        int screenHeight = getScreenHeight(context);

        Double aspecHeight = (double) ((height * 100) / screenHeight);
        Double newHeight = Math.ceil((aspecHeight * screenHeight) / 100);

        return newHeight.intValue();
    }


    public static String getFormattedDate(String dateString) {
        Date date = getDate(dateString);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int day = cal.get(Calendar.DATE);
        switch (day % 10) {
            case 1:
                return new SimpleDateFormat("MMMM d'st', yyyy").format(date);
            case 2:
                return new SimpleDateFormat("MMMM d'nd', yyyy").format(date);
            case 3:
                return new SimpleDateFormat("MMMM d'rd', yyyy").format(date);
            default:
                return new SimpleDateFormat("MMMM d'th', yyyy").format(date);
        }
    }


    private static Date getDate(String aDate) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date stringDate = simpledateformat.parse(aDate, pos);
        return stringDate;

    }
}
