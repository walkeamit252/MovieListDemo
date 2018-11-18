package com.example.amitwalke.testmovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsResponse {
    @SerializedName("budget")
    private Integer budget;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("id")
    private Integer id;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String poster_path;

    @SerializedName("spoken_languages")
    private List<Language> spoken_languagesList = new ArrayList<Language>();


    @SerializedName("status")
    private String status;

    @SerializedName("title")
    private String title;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("vote_count")
    private Integer vote_count;


    @SerializedName("release_date")
    private String release_date;

    @SerializedName("revenue")
    private double revenue;


    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public List<Language> getSpoken_languagesList() {
        return spoken_languagesList;
    }

    public void setSpoken_languagesList(List<Language> spoken_languagesList) {
        this.spoken_languagesList = spoken_languagesList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}