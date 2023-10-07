package com.example.myimdbbook.views;

import static com.example.myimdbbook.database.DbHelper.TABLE_NAME;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myimdbbook.R;
import com.example.myimdbbook.adapters.MovieAdapter;
import com.example.myimdbbook.database.DbHelper;
import com.example.myimdbbook.databinding.FragmentMainBinding;
import com.example.myimdbbook.model.Movie;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    MovieAdapter adapter;
    ArrayList<Movie> movieArrayList;
    SQLiteDatabase database;
    DbHelper dbHelper;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieArrayList = new ArrayList<>();
        dbHelper = new DbHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView'i başlatma
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new MovieAdapter(movieArrayList, getContext());
        recyclerView.setAdapter(adapter);

        // Verileri al
        getData();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void getData() {
        movieArrayList.clear();
        database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);

        int idx = cursor.getColumnIndex("id");
        int moviex = cursor.getColumnIndex("movieName");
        int datex = cursor.getColumnIndex("finishDate");
        int scorex = cursor.getColumnIndex("score");

        while (cursor.moveToNext()) {
            int id = cursor.getInt(idx);
            String movie = cursor.getString(moviex);
            String date = cursor.getString(datex);
            String score = cursor.getString(scorex);

            Movie movie1 = new Movie(id, movie, date, score);
            movieArrayList.add(movie1);
        }
        adapter.notifyDataSetChanged();
        cursor.close();
    }
}
