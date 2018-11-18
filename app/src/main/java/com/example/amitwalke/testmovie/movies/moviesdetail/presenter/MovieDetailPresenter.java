package com.example.amitwalke.testmovie.movies.moviesdetail.presenter;

import android.content.Context;

import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.movies.moviesdetail.MovieDetailContractor;
import com.example.amitwalke.testmovie.model.MovieDetailsResponse;
import com.example.amitwalke.testmovie.movies.moviesdetail.interactor.MovieDetailInteractor;
import com.example.amitwalke.testmovie.utils.NetworkUtils;

public class MovieDetailPresenter implements MovieDetailContractor.MovieDetailPresenter {

    MovieDetailContractor.MovieDetailView view;
    MovieDetailInteractor interactor;
    Context mContext;
    public MovieDetailPresenter(MovieDetailContractor.MovieDetailView view,Context mContext){
        this.view=view;
        interactor=new MovieDetailInteractor(this);
        this.mContext=mContext;
    }


    @Override
    public void getMovieDetail() {
        if (!(NetworkUtils.isNetworkAvailable(mContext)))
        {
            view.showToastMessage(mContext.getString(R.string.network_not_available));
            return;
        }
        view.showProgressDialog();
        interactor.fetchMoveDetail(view.getMovieId());
    }

    @Override
    public void showMovieDetailSuccess(MovieDetailsResponse movieDetailsResponse) {
        view.hideProgressDialog();
        view.showMoviewDetailSuccess(movieDetailsResponse);
    }

    @Override
    public void onMovieDetailError(String message) {
        view.hideProgressDialog();
        view.showMovieDetailError(message);
    }
}
