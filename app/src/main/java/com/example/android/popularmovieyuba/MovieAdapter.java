package com.example.android.popularmovieyuba;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovieyuba.utiles.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.movieAdapterViewHolder> {

    public List<Movie> mMovieData=new ArrayList<>();
    private Context context;

    public MovieAdapter (){

    }

    public  class movieAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView mPosterImage;
        public movieAdapterViewHolder(View itemView) {
            super(itemView);

            mPosterImage =  itemView.findViewById(R.id.too_late);



        }
    }

    @Override
    public movieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movies_list_items;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new movieAdapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(movieAdapterViewHolder holder, int position) {

         if(mMovieData != null) {
             String imgUrl = "https://image.tmdb.org/t/p/w500" + mMovieData.get(position).getMoviePosterPath();
             Picasso.with(context).load(imgUrl)
                     .into(holder.mPosterImage);
             double ratings = mMovieData.get(position).getMovieVotes();
         }
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if (mMovieData == null) {
            return 0;
        } else {
            return mMovieData.size();
        }
    }


}
