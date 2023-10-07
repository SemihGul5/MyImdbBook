package com.example.myimdbbook.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.contentcapture.ContentCaptureCondition;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myimdbbook.databinding.RecycerListBinding;
import com.example.myimdbbook.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    ArrayList<Movie> movieArrayList;
    Context context;

    public MovieAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycerListBinding binding=RecycerListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new MovieHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.binding.recyclerMovieNameText.setText(movieArrayList.get(position).getMovieName());
        holder.binding.recyclerScoreText.setText(movieArrayList.get(position).getScore());

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{

        RecycerListBinding binding;
        public MovieHolder(RecycerListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
