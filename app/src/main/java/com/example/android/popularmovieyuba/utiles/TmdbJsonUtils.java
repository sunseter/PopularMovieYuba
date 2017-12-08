package com.example.android.popularmovieyuba.utiles;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by yubaarrami on 11/2/17.
 */

public class TmdbJsonUtils {


    static String baseUrl = "http://image.tmdb.org/t/p/";
    static String moviePoster;
    public static ArrayList<Movie> getDataForPopularity(Context context,  String TdbJsonStr) throws JSONException {

        JSONArray movieArray;


        ArrayList<Movie> movieList = new ArrayList<>();

        if (TextUtils.isEmpty(TdbJsonStr)) {
            return null;
        }


        JSONObject movieJASONObject = new JSONObject(TdbJsonStr);
        movieArray = movieJASONObject.getJSONArray("results");

        int moviesArraylength = movieArray.length();
        String stringmoviesArray = String.valueOf(moviesArraylength);

        Log.d("MoviesExtraction ", " movies method" + stringmoviesArray);
        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject eachMovie = movieArray.getJSONObject(i);
            int movieIdString = eachMovie.getInt("id");
            String movieUserRating = Double.toString(eachMovie.getDouble("vote_average"));
            String movieOriginalTitle = eachMovie.getString("original_title");
            String movieSynopsis = eachMovie.getString("overview");
            moviePoster = eachMovie.getString("poster_path");

            String movieVoteCount = Integer.toString(eachMovie.getInt("vote_count"));
            String movieReleaseDate = eachMovie.getString("release_date");


            Movie movie = new Movie(movieIdString, 20.0,movieOriginalTitle,movieSynopsis,10,movieVoteCount,movieVoteCount,movieReleaseDate);

            movieList.add(movie);
        }
        return movieList;
    }

    public static String buildPosterUrl() {

        Uri uri = Uri.parse(baseUrl).buildUpon()
                .appendPath(moviePoster)
                .appendPath(moviePoster)
                .build();

        URL url = null;
        try{
            url = new URL(uri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url.toString();
    }
}
