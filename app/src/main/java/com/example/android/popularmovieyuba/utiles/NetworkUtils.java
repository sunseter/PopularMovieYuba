package com.example.android.popularmovieyuba.utiles;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by yubaarrami on 10/29/17.
 */

public class NetworkUtils {

    static final String TMDB_BASE_URL = "http://api.themoviedb.org/3/movie/popular?api_key=d232b74289036df12887574744463974";

    public static String sort = "sort_by";
    public static URL buildUrl(String popularity){

        Uri TMDBUri = Uri.parse(TMDB_BASE_URL).buildUpon()
                .appendQueryParameter(sort, popularity)
                .build();

        URL url = null;
        try{
            url = new URL(TMDBUri.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
