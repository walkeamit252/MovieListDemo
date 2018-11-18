package com.example.amitwalke.testmovie.movies.moviesdetail.interactor;
import com.example.amitwalke.testmovie.movies.moviesdetail.MovieDetailContractor;
import com.example.amitwalke.testmovie.network.ApiInterface;
import com.example.amitwalke.testmovie.model.MovieDetailsResponse;
import com.example.amitwalke.testmovie.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.amitwalke.testmovie.utils.AppConstants.API_KEY;

public class MovieDetailInteractor implements MovieDetailContractor.MovieDetailInteracotr {

    MovieDetailContractor.MovieDetailPresenter presenter;
    ApiInterface apiService;
    public MovieDetailInteractor(MovieDetailContractor.MovieDetailPresenter presenter){
     this.presenter=presenter;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void fetchMoveDetail(int id) {
        Call<MovieDetailsResponse> call = apiService.getSelectedMovieDetails(id, API_KEY);
        call.enqueue(new Callback<MovieDetailsResponse>() {
            @Override
            public void onResponse(Call<MovieDetailsResponse> call, Response<MovieDetailsResponse> response) {
                if (response.isSuccessful()) {
                    MovieDetailsResponse movieDetailsResponse = response.body();
                    if (movieDetailsResponse != null) {
                        presenter.showMovieDetailSuccess(movieDetailsResponse);
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieDetailsResponse> call, Throwable t) {
             presenter.onMovieDetailError(t.getMessage());
            }
        });


    }
}
