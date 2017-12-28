package com.example.android.popularmovieyuba;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovieyuba.utiles.Movie;
import com.example.android.popularmovieyuba.utiles.NetworkUtils;
import com.example.android.popularmovieyuba.utiles.TmdbJsonUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView mTmdbTextView;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private static final int NUM_LIST_ITEMS = 100;
    String[] movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView =(RecyclerView) findViewById(R.id.rv_movies);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        Log.e("hello",TmdbJsonUtils.buildPosterUrl());
        mRecyclerView.setLayoutManager(layoutManager);


        mMovieAdapter = new MovieAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mMovieAdapter);
        loadTmdbData();

    }

    public void loadTmdbData(){

        mRecyclerView.setVisibility(View.VISIBLE);
        new TMDBAsyncTask().execute("popularity.desc");
    }



    public class TMDBAsyncTask extends AsyncTask<String, Void, ArrayList<Movie>>{


        @Override
        protected  ArrayList<Movie>  doInBackground(String... strings) {

            String popular = strings[0];
            URL TmdbUrl = NetworkUtils.buildUrl(popular);
            Log.e("this, ", TmdbUrl + " ");
            try{

                String JsonTdbRespense = NetworkUtils.getResponseFromHttpUrl(TmdbUrl);
                 ArrayList<Movie> mtdbResult = TmdbJsonUtils.getDataForPopularity(MainActivity.this, JsonTdbRespense);

                return mtdbResult;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(ArrayList<Movie> s) {


          if(s != null){
              mRecyclerView.setVisibility(View.VISIBLE);
          }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.popularity_and_rating_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemSelected = item.getItemId();
        if (itemSelected == R.id.most_popular_menu){
            Toast.makeText(this,"most popular movies", Toast.LENGTH_SHORT).show();
            new TMDBAsyncTask().execute("popularity.desc");
        }else if(itemSelected == R.id.most_rated_menu){
            Toast.makeText(this, "most rated movies", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
