package com.example.amitwalke.testmovie.movies.movieslist.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.adapter.MoviesListAccordingToTypeVerticalAdapter;
import com.example.amitwalke.testmovie.model.GenreResponse;
import com.example.amitwalke.testmovie.model.Movie;
import com.example.amitwalke.testmovie.movies.movieslist.MoviesListContracotr;
import com.example.amitwalke.testmovie.movies.movieslist.presenter.MovieListPresenter;

import java.util.HashMap;
import java.util.List;

public class MovieListingActivity extends AppCompatActivity implements MoviesListContracotr.MovieListView {
    private MoviesListAccordingToTypeVerticalAdapter verticalAdapter;
    private RecyclerView rvHomeMain;

    Toolbar toolbar;

    MovieListPresenter presenter;

    ProgressDialog progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        presenter=new MovieListPresenter(this,this);
        initView();

    }

    private void initView() {
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please Wait ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        rvHomeMain = findViewById(R.id.rvHomeMain);
        rvHomeMain.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvHomeMain.setLayoutManager(llm);
        rvHomeMain.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter.getMovieList();
    }


    @Override
    public void showProgressDialog() {
        if (progressBar != null)
        {
            progressBar.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        if (progressBar != null) {
            progressBar.dismiss();
        }
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMoviewListSuccess(List<GenreResponse> genList, HashMap<Integer, List<Movie>> movieListHashMap) {
        if (rvHomeMain == null) {
            return;
        }
        verticalAdapter = new MoviesListAccordingToTypeVerticalAdapter(this,genList,movieListHashMap);
        rvHomeMain.setAdapter(verticalAdapter);
    }

    @Override
    public void showMoveListError(String message) {
       showToastMessage(message);
    }



    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:

                   finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:

                    break;
            }
        }
    };

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit App?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }


}