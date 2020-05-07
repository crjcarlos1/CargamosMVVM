package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.FragmentMovieDetailBinding;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces.OnClickDetail;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.viewmodel.MovieDetailViewModelImpl;

public class FragmentMovieDetail extends Fragment implements OnClickDetail {
    public static final String TAG = FragmentMovieDetail.class.getSimpleName();

    private Bundle bundle;

    /**
     * binding
     */
    private FragmentMovieDetailBinding binding;

    /**
     * viewModel
     */
    private MovieDetailViewModelImpl viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_detail, container, false);
        initFragmentMovieDetail();
        setupMovie();
        setupIsFavoriteMovie();
        setupMessage();
        return binding.getRoot();
    }

    @Override
    public void onClickSaveMovie() {
        viewModel.saveMovie(binding.getMovie());
    }

    @Override
    public void onClickDeleteMovie() {
        viewModel.deleteMovie(binding.getMovie());
    }

    private void initFragmentMovieDetail() {
        binding.setOnClick(this);
        viewModel = ViewModelProviders.of(this).get(MovieDetailViewModelImpl.class);
        viewModel.initMovieDetailViewModel(getContext());
        viewModel.getMovieFromCore(bundle);
    }

    private void setupMovie() {
        viewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.setMovie(movie);
            }
        });
    }

    private void setupIsFavoriteMovie() {
        viewModel.getIsFavoriteMovie().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isFavoriteMovie) {
                binding.setIsFavoriteMovie(isFavoriteMovie);
            }
        });
    }

    private void setupMessage() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
