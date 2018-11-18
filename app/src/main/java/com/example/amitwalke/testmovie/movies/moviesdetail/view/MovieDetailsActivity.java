package com.example.amitwalke.testmovie.movies.moviesdetail.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitwalke.testmovie.R;
import com.example.amitwalke.testmovie.movies.moviesdetail.MovieDetailContractor;
import com.example.amitwalke.testmovie.movies.moviesdetail.presenter.MovieDetailPresenter;
import com.example.amitwalke.testmovie.model.MovieDetailsResponse;
import com.example.amitwalke.testmovie.utils.CommonUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

import static com.example.amitwalke.testmovie.utils.AppConstants.Description;
import static com.example.amitwalke.testmovie.utils.AppConstants.Language;
import static com.example.amitwalke.testmovie.utils.AppConstants.Popularity;
import static com.example.amitwalke.testmovie.utils.AppConstants.ReleaseDate;
import static com.example.amitwalke.testmovie.utils.AppConstants.Revenue;
import static com.example.amitwalke.testmovie.utils.AppConstants.BundleConstants.MOVIE_ID;
import static com.example.amitwalke.testmovie.utils.AppConstants.BundleConstants.MOVIE_NAME;

public class MovieDetailsActivity extends AppCompatActivity implements View.OnClickListener ,MovieDetailContractor.MovieDetailView {

    ProgressDialog progressBar ;

    Toolbar toolbar;
    TextView txtTitle;
    Bundle bundle;
    TextView txtDescription,txtLanguage,txtPopularity,txtReleasedate,txtRevenue;
    CircularImageView ivBackImage;
    ImageView ivPosterImage,ivBack;
    int movieId;
    MovieDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        presenter=new MovieDetailPresenter(this,this);
        initView();
    }

    private void initView() {
        ivBackImage = findViewById(R.id.ivBackImage);
        ivBack = findViewById(R.id.ivBack);
        ivPosterImage = findViewById(R.id.ivPosterImage);
        txtDescription = findViewById(R.id.tvDescription);
        txtLanguage = findViewById(R.id.tvLanguage);
        txtPopularity = findViewById(R.id.tvPopularity);
        txtReleasedate = findViewById(R.id.tvReleasedate);
        txtRevenue = findViewById(R.id.tvRevenue);
        txtTitle = findViewById(R.id.tvTitle);
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please Wait ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        toolbar = findViewById(R.id.toolbar);
        bundle =  getIntent().getExtras();
        if (bundle != null)
        {
            txtTitle.setText(bundle.getString(MOVIE_NAME));
            movieId=bundle.getInt(MOVIE_ID);
        }
        else {
            return;
        }
        ivBack.setOnClickListener(this);
        presenter.getMovieDetail();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ivBack:
                finish();
                break;
        }
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
    public void showMoviewDetailSuccess(MovieDetailsResponse movieDetailsResponse) {
        if (movieDetailsResponse != null) {
            txtDescription.setText(Description+" - "+movieDetailsResponse.getOverview());
            if (movieDetailsResponse.getSpoken_languagesList() != null) {
                if (movieDetailsResponse.getSpoken_languagesList().get(0) != null) {
                    txtLanguage.setText(Language + " - " + movieDetailsResponse.getSpoken_languagesList().get(0).getName());
                }
            }
            txtPopularity.setText(Popularity+" - "+movieDetailsResponse.getPopularity());
            txtReleasedate.setText(ReleaseDate+" - "+movieDetailsResponse.getRelease_date());
            txtRevenue.setText(Revenue+" - "+movieDetailsResponse.getRevenue());
            CommonUtils.getInstance().downloadMyImageUsingUrl(this,movieDetailsResponse.getBackdrop_path(), ivBackImage);
            CommonUtils.getInstance().downloadMyImageUsingUrl(this,movieDetailsResponse.getPoster_path(), ivPosterImage);

        }
    }

    @Override
    public void showMovieDetailError(String message) {
       showToastMessage(message);
    }

    @Override
    public int getMovieId() {
        return movieId;
    }
}