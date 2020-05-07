package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.FavoriteMovieItemBinding;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.OnClickFavoriteMovie;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies;
    private OnClickFavoriteMovie onClickFavoriteMovie;

    public FavoriteMoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FavoriteMovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.favorite_movie_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.binding.setMovie(movies.get(position));
        holder.binding.constraintMainMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickFavoriteMovie != null)
                    onClickFavoriteMovie.onClickFavoriteMovie(movies.get(position));
            }
        });
    }

    public void updateList(List<Movie> movies) {
        this.movies = new ArrayList<>();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public void setOnClickFavoriteMovieListener(OnClickFavoriteMovie onClickFavoriteMovieListener) {
        this.onClickFavoriteMovie = onClickFavoriteMovieListener;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private FavoriteMovieItemBinding binding;

        public ViewHolder(@NonNull FavoriteMovieItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
