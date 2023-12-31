package com.example.myimdbbook.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.myimdbbook.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.addMovie){
            //NavDirections navDirections= MainFragmentDirections.actionMainFragmentToAddMovieFragment("new");
            MainFragmentDirections.ActionMainFragmentToAddMovieFragment action=MainFragmentDirections.actionMainFragmentToAddMovieFragment();
            action.setNoro("new");
            Navigation.findNavController(this,R.id.fragmentContainerView).navigate(action);
        }
        return super.onOptionsItemSelected(item);
    }

}