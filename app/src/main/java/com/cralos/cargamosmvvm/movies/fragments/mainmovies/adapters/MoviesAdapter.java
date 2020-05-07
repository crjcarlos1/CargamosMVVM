package com.cralos.cargamosmvvm.movies.fragments.mainmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.MovieItemBinding;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.OnClickMainMovieListener;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private Context context;
    private List<Movie> movies;
    private OnClickMainMovieListener onClickMainMovieListener;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.binding.setMovie(movies.get(position));
        holder.binding.constraintMainMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickMainMovieListener != null) {
                    onClickMainMovieListener.onClickMainMovie(movies.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnClickMainMovieListener(OnClickMainMovieListener onClickMainMovieListener) {
        this.onClickMainMovieListener = onClickMainMovieListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private MovieItemBinding binding;

        public ViewHolder(@NonNull MovieItemBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

}
