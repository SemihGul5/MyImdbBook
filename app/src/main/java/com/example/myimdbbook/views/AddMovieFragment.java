package com.example.myimdbbook.views;

import static com.example.myimdbbook.database.DbHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import com.example.myimdbbook.model.Movie;
import com.google.android.material.snackbar.Snackbar;

public class AddMovieFragment extends Fragment {
    private FragmentAddMovieBinding binding;
    DbHelper dbHelper;
    SQLiteDatabase database;
    String info="";
    int id=0;

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
        dbHelper=new DbHelper(getContext());

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

        info=AddMovieFragmentArgs.fromBundle(getArguments()).getNoro();

        if (info.equals("new")){
            binding.button.setText("Kaydet");
            binding.dateText.setText("");
            binding.scoreText.setText("");
            binding.movieNameText.setText("");
        }
        else{
            binding.button.setText("Sil");
            binding.movieNameText.setEnabled(false);
            binding.scoreText.setEnabled(false);
            binding.dateText.setEnabled(false);
            id=AddMovieFragmentArgs.fromBundle(getArguments()).getMovieID();

            getData(id);
        }



    }

    private void getData(int id) {
        database=dbHelper.getReadableDatabase();
        Cursor cursor=database.rawQuery("select * from "+TABLE_NAME+" where id="+id+"",null);

        if (cursor.moveToNext()) {
            String movie = cursor.getString(1);
            String date = cursor.getString(2);
            String score = cursor.getString(3);

            binding.movieNameText.setText(movie);
            binding.dateText.setText(date);
            binding.scoreText.setText(score);
        }
        cursor.close();

    }


    private void saveButtonClicked(View view) {
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.button.getText().equals("Kaydet")){
                    if (binding.movieNameText.getText().toString().isEmpty()||
                            binding.dateText.getText().toString().isEmpty()||
                            binding.scoreText.getText().toString().isEmpty()){
                        Snackbar.make(v,"Tüm alanları doldurun",Snackbar.LENGTH_SHORT).show();
                    }
                    else{
                        int score=Integer.parseInt(binding.scoreText.getText().toString());
                        if (score>=0&&score<=10){
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
                                    goToMainFragment(v);

                                    textClear();

                                }
                                else {
                                    Toast.makeText(getContext(), "Kayıt Başarısız", Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        else{
                            Snackbar.make(v,"Puan 0-10 arası olmalıdır",Snackbar.LENGTH_SHORT).show();

                        }
                    }
                }
                else{
                    //eski veri gelcek, silme işlemi yapılacak
                    try {
                        database=dbHelper.getReadableDatabase();
                        long result=database.delete(TABLE_NAME,"id="+id+"",null);

                        if (result!=-1){
                            Toast.makeText(getContext(), "Kayıt Silme işlemi başarılı", Toast.LENGTH_SHORT).show();
                            goToMainFragment(v);
                            textClear();
                        }
                        else{
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

    private void textClear() {
        binding.scoreText.setText("");
        binding.movieNameText.setText("");
        binding.dateText.setText("");
    }

    private void goToMainFragment(View v) {
        NavDirections navDirections= AddMovieFragmentDirections.actionAddMovieFragmentToMainFragment();
        Navigation.findNavController(v).navigate(navDirections);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding =null;

    }
}