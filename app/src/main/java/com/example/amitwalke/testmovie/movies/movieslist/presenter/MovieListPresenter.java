package com.example.amitwalke.testmovie.movies.movieslist.presenter;

import android.content.Context;

import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.model.GenreResponse;
import com.example.amitwalke.testmovie.model.Movie;
import com.example.amitwalke.testmovie.movies.movieslist.interactor.MovieListInteractor;
import com.example.amitwalke.testmovie.utils.NetworkUtils;
import com.example.amitwalke.testmovie.movies.movieslist.MoviesListContracotr;

import java.util.HashMap;
import java.util.List;

public class MovieListPresenter implements MoviesListContracotr.MovieListPresenter {
    MoviesListContracotr.MovieListView view;
    MovieListInteractor interactor;
    Context mContext;

    public MovieListPresenter(MoviesListContracotr.MovieListView view,Context mContext){
        this.view=view;
        interactor=new MovieListInteractor(this);
        this.mContext=mContext;
    }

    @Override
    public void getMovieList() {
        if (!(NetworkUtils.isNetworkAvailable(mContext)))
        {
            view.showToastMessage(mContext.getString(R.string.network_not_available));
            return;
        }
        view.showProgressDialog();
        interactor.fetchMoveList();
    }

    @Override
    public void onMovieListDataSuccess(List<GenreResponse> genList, HashMap<Integer, List<Movie>> movieListHashMap) {
       view.hideProgressDialog();
       view.showMoviewListSuccess(genList,movieListHashMap);
    }

    @Override
    public void onMoviewListError(String message) {
        view.showMoveListError(message);
    }


}
