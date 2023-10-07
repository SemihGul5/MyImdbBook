package com.example.myimdbbook.views;

import static com.example.myimdbbook.database.DbHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myimdbbook.R;
import com.example.myimdbbook.database.DbHelper;
import com.example.myimdbbook.databinding.FragmentAddMovieBinding;
import com.example.myimdbbook.databinding.FragmentMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class AddMovieFragment extends Fragment {
    private FragmentAddMovieBinding binding;
    DbHelper dbHelper;
    SQLiteDatabase database;

    public AddMovieFragment() {
        // Required empty public constructor
    }
    public static AddMovieFragment newInstance(String param1, String param2) {
        AddMovieFragment fragment = new AddMovieFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddMovieBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveButtonClicked(view);
        dbHelper=new DbHelper(getContext());
    }

    private void saveButtonClicked(View view) {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.movieNameText.getText().toString().isEmpty()||
                        binding.dateText.getText().toString().isEmpty()||
                        binding.scoreText.getText().toString().isEmpty()){
                    Snackbar.make(v,"Tüm alanları doldurun",Snackbar.LENGTH_SHORT).show();
                }
                else{

                    try {
                        ContentValues contentValues=new ContentValues();
                        contentValues.put("movieName",binding.movieNameText.getText().toString());
                        contentValues.put("finishDate",binding.dateText.getText().toString());
                        contentValues.put("score",binding.scoreText.getText().toString());

                        database=dbHelper.getWritableDatabase();
                        long result=database.insert(TABLE_NAME,null,contentValues);
                        if (result!=-1){
                            Toast.makeText(getContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                            // ana fragmenta git
                            NavDirections navDirections= AddMovieFragmentDirections.actionAddMovieFragmentToMainFragment();
                            Navigation.findNavController(v).navigate(navDirections);
                            binding.scoreText.setText("");
                            binding.movieNameText.setText("");
                            binding.dateText.setText("");

                        }
                        else {
                            Toast.makeText(getContext(), "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding =null;

    }
}