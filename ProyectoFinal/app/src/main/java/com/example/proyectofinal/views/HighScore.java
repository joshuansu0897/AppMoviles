package com.example.proyectofinal.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.proyectofinal.DB.DatabaseClient;
import com.example.proyectofinal.DB.User;
import com.example.proyectofinal.R;
import com.example.proyectofinal.views.Adapter.ToDoListAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HighScore extends AppCompatActivity {
    private ToDoListAdapter adapter;

    @BindView(R.id.todo_list)
    RecyclerView toDoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        ButterKnife.bind(this);

        adapter = new ToDoListAdapter();

        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        class GetTasks extends AsyncTask<Void, Void, ArrayList<User>> {

            @Override
            protected ArrayList<User> doInBackground(Void... voids) {
                List<User> users = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll();
                return new ArrayList<>(users);
            }

            @Override
            protected void onPostExecute(ArrayList<User> users) {
                super.onPostExecute(users);
                adapter.updateToDos(users);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
