package com.example.amitwalke.testmovie.movies.moviesdetail;

import com.example.amitwalke.testmovie.model.MovieDetailsResponse;

public interface MovieDetailContractor {

     interface MovieDetailView{

        void showProgressDialog();
        void hideProgressDialog();
        void showToastMessage(String message);

        void showMoviewDetailSuccess(MovieDetailsResponse movieDetailsResponse);

        void showMovieDetailError(String message);

        int getMovieId();

    }

     interface MovieDetailPresenter{
        void getMovieDetail();
        void showMovieDetailSuccess(MovieDetailsResponse movieDetailsResponse);
        void onMovieDetailError(String message);
    }

     interface MovieDetailInteracotr{
        void fetchMoveDetail(int id);
    }


}
