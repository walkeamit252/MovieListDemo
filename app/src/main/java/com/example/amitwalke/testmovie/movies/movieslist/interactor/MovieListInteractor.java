package com.example.amitwalke.testmovie.movies.movieslist.interactor;

import com.example.amitwalke.testmovie.model.GenreListResponse;
import com.example.amitwalke.testmovie.model.GenreResponse;
import com.example.amitwalke.testmovie.model.Movie;
import com.example.amitwalke.testmovie.model.MoviesResponse;
import com.example.amitwalke.testmovie.network.ApiClient;
import com.example.amitwalke.testmovie.network.ApiInterface;
import com.example.amitwalke.testmovie.movies.movieslist.MoviesListContracotr;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amitwalke.testmovie.utils.AppConstants.API_KEY;

public class MovieListInteractor implements MoviesListContracotr.MovieListInteracotr {
    MoviesListContracotr.MovieListPresenter presenter;
    ApiInterface apiService;
    private HashMap<Integer, List<Movie>> movieListHashMap;
    int count = 0;


    public MovieListInteractor(MoviesListContracotr.MovieListPresenter presenter){
        this.presenter=presenter;
        apiService = ApiClient.getClient().create(ApiInterface.class);
        movieListHashMap = new HashMap<>();
    }


    @Override
    public void fetchMoveList() {
        Call<GenreListResponse> call = apiService.getGenerList(API_KEY);
        call.enqueue(new Callback<GenreListResponse>() {
            @Override
            public void onResponse(Call<GenreListResponse> call, Response<GenreListResponse> response) {
                if (response.isSuccessful()) {
                    int statusCode = response.code();
                    List<GenreResponse> genList = response.body().getGenres();
                    if (genList != null && genList.size() > 0) {
                        callToFetchMovieList(genList);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenreListResponse> call, Throwable t) {
                presenter.onMoviewListError(t.getMessage());
            }
        });
    }


    private void callToFetchMovieList(final List<GenreResponse> genList) {

        for (final GenreResponse genreResponse : genList) {
            Call<MoviesResponse> call = apiService.getMovieDetails(genreResponse.getId(),API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    if (response.isSuccessful()) {
                        int statusCode = response.code();
                        List<Movie> movieList = response.body().getResults();
                        if (movieList != null && movieList.size() > 0) {
                            // add movies list to hashmap for display
                            movieListHashMap.put(genreResponse.getId(),movieList);
                        }
                        count++;
                        if (count == genList.size())
                        {
                           presenter.onMovieListDataSuccess(genList,movieListHashMap);
                        }
                    }
                }
                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    presenter.onMoviewListError(t.getMessage());
                }
            });
        }

    }
}
