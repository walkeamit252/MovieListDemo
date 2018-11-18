package com.example.amitwalke.testmovie.network;

import com.example.amitwalke.testmovie.model.GenreListResponse;
import com.example.amitwalke.testmovie.model.MoviesResponse;
import com.example.amitwalke.testmovie.model.MovieDetailsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
 
    @GET("genre/{id}/movies")
    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreListResponse> getGenerList(@Query("api_key") String apiKey);


    @GET("movie/{id}")
    Call<MovieDetailsResponse> getSelectedMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}