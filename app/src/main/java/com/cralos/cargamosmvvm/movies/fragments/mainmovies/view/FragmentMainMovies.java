package com.cralos.cargamosmvvm.movies.fragments.mainmovies.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.FragmentMainMoviesBinding;
import com.cralos.cargamosmvvm.loader.LoaderMovies;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.view.FragmentFavoriteMovies;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.adapters.MoviesAdapter;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.OnClickMainMovie;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.OnClickMainMovieListener;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.viewmodel.MainMoviesViewModelImpl;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.view.FragmentMovieDetail;

import java.util.List;

public class FragmentMainMovies extends Fragment implements OnClickMainMovieListener, OnClickMainMovie {
    public static final String TAG = FragmentMainMovies.class.getSimpleName();
    private boolean readyToCharge = false;

    /**
     * binding
     */
    private FragmentMainMoviesBinding binding;

    /**
     * loader
     */
    private LoaderMovies loaderMovies;

    /**
     * viewModel
     */
    private MainMoviesViewModelImpl viewModel;

    /**
     * recyclerView
     */
    private MoviesAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_movies, container, false);
        binding.setOnClick(this);
        initFragmentMainMovies();
        setupMoviesRecycler();
        setUpLoader();
        setUpMessage();
        return binding.getRoot();
    }

    @Override
    public void onClickMainMovie(Movie movie) {
        removeObservers();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MOVIE", movie);
        FragmentMovieDetail fragmentMovieDetail = new FragmentMovieDetail();
        fragmentMovieDetail.setArguments(bundle);
        transaction.addToBackStack(FragmentMovieDetail.TAG);
        transaction.replace(R.id.containerFragments, fragmentMovieDetail, FragmentMovieDetail.TAG).commit();
    }

    @Override
    public void showFavoriteMovies() {
        removeObservers();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentFavoriteMovies.TAG);
        transaction.replace(R.id.containerFragments, new FragmentFavoriteMovies(), FragmentFavoriteMovies.TAG).commit();
    }

    private void initFragmentMainMovies() {
        viewModel = ViewModelProviders.of(this).get(MainMoviesViewModelImpl.class);
        viewModel.initMainMoviesViewModel(getContext());
        viewModel.getMoviesFromApi();
    }

    private void setupMoviesRecycler() {
        readyToCharge = true;
        adapter = new MoviesAdapter(getContext(), viewModel.getMovies().getValue());
        adapter.setOnClickMainMovieListener(this);
        gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.recyclerMovies.setHasFixedSize(false);
        binding.recyclerMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerMovies.setAdapter(adapter);

        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.notifyDataSetChanged();
                readyToCharge = true;
            }
        });

        binding.recyclerMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = gridLayoutManager.getChildCount();
                int totalItemCount = gridLayoutManager.getItemCount();
                int pastVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();
                if (readyToCharge) {
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        readyToCharge = false;
                        viewModel.getMoviesFromApi();
                    }
                }
            }
        });

    }

    private void setUpLoader() {
        viewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isUpdating) {
                if (isUpdating) showLoader();
                else hideLoader();
            }
        });
    }

    private void setUpMessage() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeObservers() {
        viewModel.getIsUpdating().removeObservers(this);
        viewModel.getMessage().removeObservers(this);
        viewModel.getMovies().removeObservers(this);
    }

    private void showLoader() {
        loaderMovies = new LoaderMovies();
        loaderMovies.show(getActivity().getSupportFragmentManager(), LoaderMovies.TAG);
    }

    private void hideLoader() {
        loaderMovies.dismiss();
    }

}
