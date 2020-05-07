package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
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

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.FragmentFavoritesMoviesBinding;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.adapters.FavoriteMoviesAdapter;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.OnClickFavoriteMovie;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.viewmodel.FavoriteMoviesViewModelImpl;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.view.FragmentMovieDetail;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavoriteMovies extends Fragment implements OnClickFavoriteMovie {
    public static final String TAG = FragmentFavoriteMovies.class.getSimpleName();

    /**
     * BINDING
     */
    private FragmentFavoritesMoviesBinding binding;

    /**
     * viewmodel
     */
    private FavoriteMoviesViewModelImpl viewModel;

    /**
     * recyclerView
     */
    private FavoriteMoviesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites_movies, container, false);
        initFragmentFavoriteMovies();
        return binding.getRoot();
    }

    @Override
    public void onClickFavoriteMovie(Movie movie) {
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

    private void initFragmentFavoriteMovies() {
        viewModel = ViewModelProviders.of(this).get(FavoriteMoviesViewModelImpl.class);
        viewModel.initFavoriteMoviesViewModel(getContext());
        viewModel.getMoviesFromCore();
        setupSearchView();
        setUpRecycler();
        setUpMessage();
    }

    private void setupSearchView(){
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                List<Movie> newList = new ArrayList<>();
                for (Movie movie : viewModel.getMovies().getValue()) {
                    if (movie.getOriginalTitle().toLowerCase().contains(userInput)) {
                        newList.add(movie);
                    }
                }
                adapter.updateList(newList);
                return true;
            }
        });
    }

    private void setUpRecycler() {
        adapter = new FavoriteMoviesAdapter(getContext(), viewModel.getMovies().getValue());
        adapter.setOnClickFavoriteMovieListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        binding.recyclerFavoriteMovies.setHasFixedSize(false);
        binding.recyclerFavoriteMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerFavoriteMovies.setAdapter(adapter);
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.notifyDataSetChanged();
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
        viewModel.getMovies().removeObservers(this);
        viewModel.getMovies().removeObservers(this);
    }

}
