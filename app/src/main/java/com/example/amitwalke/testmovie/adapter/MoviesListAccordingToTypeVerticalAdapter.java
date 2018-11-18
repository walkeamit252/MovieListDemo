package com.example.amitwalke.testmovie.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.model.GenreResponse;
import com.example.amitwalke.testmovie.model.Movie;

import java.util.HashMap;
import java.util.List;


public class MoviesListAccordingToTypeVerticalAdapter extends RecyclerView.Adapter<MoviesListAccordingToTypeVerticalAdapter.SimpleViewHolder> {

    private Context mContext;
    List<GenreResponse> genreResponseList;
    private HashMap<Integer, List<Movie>> movieListHashMap;
    static class SimpleViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnItemTouchListener {
        public TextView title;
        ImageView icons;
        RecyclerView horizontalList; //horizontal recyle view
        Context context;
        private MoviesListHorizontalAdapter horizontalAdapter;

        SimpleViewHolder(View view) {
            super(view);
            context = itemView.getContext();

            title = view.findViewById(R.id.tvTitle);
            //horizontal recycler view
            horizontalList = itemView.findViewById(R.id.horizontal_list);
            horizontalList.addOnItemTouchListener(this);
            horizontalList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            horizontalAdapter = new MoviesListHorizontalAdapter();
            horizontalList.setAdapter(horizontalAdapter);


        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public MoviesListAccordingToTypeVerticalAdapter(Context context, List<GenreResponse> genreResponseList, HashMap<Integer, List<Movie>> movieListHashMap) {
        this.mContext = context;
        this.genreResponseList = genreResponseList;
        this.movieListHashMap = movieListHashMap;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.home_recycler_vertical, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(genreResponseList.get(position).getName());
        holder.horizontalAdapter.setData(mContext,movieListHashMap.get(genreResponseList.get(position).getId()));
        holder.horizontalAdapter.setRowIndex(position);
    }

    @Override
    public int getItemCount() {
      return genreResponseList.size();
    }



}