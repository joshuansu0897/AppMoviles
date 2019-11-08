package com.example.sonidosr;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.chango)
    void chango() {
        mpChango.start();
    }

    @OnClick(R.id.feo)
    void feo() {
        mpFeo.start();
    }

    @OnClick(R.id.matanga)
    void matanga() {
        mpMatanga.start();
    }

    @OnClick(R.id.mensaje)
    void mensaje() {
        mpMensaje.start();
    }

    MediaPlayer mpMensaje;
    MediaPlayer mpFeo;
    MediaPlayer mpMatanga;
    MediaPlayer mpChango;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mpMensaje = MediaPlayer.create(this, R.raw.mensaje);
        mpFeo = MediaPlayer.create(this, R.raw.feo);
        mpMatanga = MediaPlayer.create(this, R.raw.matanga);
        mpChango = MediaPlayer.create(this, R.raw.chango);

        ButterKnife.bind(this);
    }
}
