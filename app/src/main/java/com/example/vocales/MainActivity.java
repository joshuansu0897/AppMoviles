package com.example.vocales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.aprender)
    void aprender(View view) {
        Intent intent = new Intent(MainActivity.this, Aprender.class);
        startActivity(intent);
    }

    @OnClick(R.id.quiz)
    void quiz(View view) {
        Intent intent = new Intent(MainActivity.this, Quiz.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
