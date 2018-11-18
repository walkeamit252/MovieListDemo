package com.example.amitwalke.testmovie.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class GenreListResponse
{

    @Expose
    List<GenreResponse> genres;


    public List<GenreResponse> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponse> genres) {
        this.genres = genres;
    }
}