package com.example.proyectofinal.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectofinal.DB.User;
import com.example.proyectofinal.MainActivity;
import com.example.proyectofinal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "User";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        user = getUser();
    }

    @OnClick(R.id.aprender)
    void aprender(View view) {
        Intent intent = new Intent(view.getContext(), Aprender.class);
        startActivity(intent);
    }

    @OnClick(R.id.high_score)
    void highScore(View view) {
        Intent intent = new Intent(view.getContext(), HighScore.class);
        startActivity(intent);
    }

    @OnClick(R.id.cerrar_sesion)
    void cerrar_sesion(View view) {
        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.quiz)
    void quiz(View view) {
        Intent intent = new Intent(view.getContext(), Quiz.class);
        startActivity(intent);
        intent.putExtra(Quiz.EXTRA_TODO_KEY, user);
        view.getContext().startActivity(intent);
        finish();
    }

    private User getUser() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (User) extras.getSerializable(EXTRA_TODO_KEY);
        }
        return new User();
    }
}
