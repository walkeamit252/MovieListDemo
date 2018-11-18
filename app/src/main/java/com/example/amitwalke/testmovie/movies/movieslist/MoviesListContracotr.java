package com.example.amitwalke.testmovie.movies.movieslist;

import com.example.amitwalke.testmovie.model.GenreResponse;
import com.example.amitwalke.testmovie.model.Movie;

import java.util.HashMap;
import java.util.List;

public class MoviesListContracotr {

    public interface MovieListView{

        void showProgressDialog();
        void hideProgressDialog();
        void showToastMessage(String message);
        void showMoviewListSuccess(List<GenreResponse> genList, HashMap<Integer, List<Movie>> movieListHashMap);
        void showMoveListError(String message);

    }

    public interface MovieListPresenter{
        void getMovieList();
        void onMovieListDataSuccess(List<GenreResponse> genList,HashMap<Integer, List<Movie>> movieListHashMap);
        void onMoviewListError(String message);
    }

    public interface MovieListInteracotr{
        void fetchMoveList();
    }

}
