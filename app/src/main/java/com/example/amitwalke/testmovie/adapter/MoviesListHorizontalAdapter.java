package com.example.amitwalke.testmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.model.Movie;
import com.example.amitwalke.testmovie.movies.moviesdetail.view.MovieDetailsActivity;
import com.example.amitwalke.testmovie.utils.AppConstants;
import com.example.amitwalke.testmovie.utils.CommonUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;


public class MoviesListHorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    private Context context;
    private int position;
    private int mRowIndex = -1;
    boolean size = false;



    MoviesListHorizontalAdapter( ) {

    }

    public void setData(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    void setRowIndex(int index) {
        mRowIndex = index;
    }



    private class ItemViewHolder extends RecyclerView.ViewHolder {

        private final LinearLayout llSupplierDetails;
        private CircularImageView imgSupplierImage;
        private TextView tvMovieName;

        ItemViewHolder(View itemView) {
            super(itemView);
            llSupplierDetails = itemView.findViewById(R.id.ll_supplier);
            tvMovieName = itemView.findViewById(R.id.tvMovieName);
            imgSupplierImage = itemView.findViewById(R.id.ivSupplierImage);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.home_recycler_horizontal, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, final int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        holder.tvMovieName.setText(movieList.get(position).getTitle());
        CommonUtils.getInstance().downloadMyImageUsingUrl(context,movieList.get(position).getPosterPath(), holder.imgSupplierImage);

        holder.llSupplierDetails.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(AppConstants.BundleConstants.MOVIE_NAME,movieList.get(position).getTitle());
                bundle.putInt(AppConstants.BundleConstants.MOVIE_ID,movieList.get(position).getId());
                Intent intent_movie_details = new Intent(context, MovieDetailsActivity.class);
                intent_movie_details.putExtras(bundle);
                context.startActivity(intent_movie_details);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (movieList == null)
        {
            return 0;
        }
        if (movieList.size()>0)
        {
            return movieList.size();
        }
        else
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}