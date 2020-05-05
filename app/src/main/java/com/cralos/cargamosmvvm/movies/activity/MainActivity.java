package com.cralos.cargamosmvvm.movies.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.view.FragmentMainMovies;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragmentMainMovies();
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    private void showFragmentMainMovies() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack(FragmentMainMovies.TAG);
        transaction.add(R.id.containerFragments, new FragmentMainMovies(), FragmentMainMovies.TAG).commit();
    }
}
