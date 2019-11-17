package com.example.vocales;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.vocales.Utils.Vocal;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    private MediaPlayer audio;
    private ArrayList<Vocal> vocales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        vocales = new ArrayList<>();

        //a
        vocales.add(new Vocal("abeja", "abeja", "a"));
        vocales.add(new Vocal("anillo", "anillo", "a"));
        vocales.add(new Vocal("arcoiris", "arcoiris", "a"));

        //e
        vocales.add(new Vocal("elefante", "elefante", "e"));
        vocales.add(new Vocal("erizo", "erizo", "e"));
        vocales.add(new Vocal("estrella", "estrella", "e"));

        //i
        vocales.add(new Vocal("iguana", "iguana", "i"));
        vocales.add(new Vocal("imaginacion", "imaginacion", "i"));
        vocales.add(new Vocal("isla", "isla", "i"));

        //o
        vocales.add(new Vocal("ogro", "ogro", "o"));
        vocales.add(new Vocal("oso", "oso", "o"));
        vocales.add(new Vocal("oveja", "oveja", "o"));

        //u
        vocales.add(new Vocal("universo", "universo", "u"));
        vocales.add(new Vocal("uno", "uno", "u"));
        vocales.add(new Vocal("uva", "uva", "u"));
    }

    void sonido(View view) {
        String vocal = view.getTag().toString();
        int id = getResources().getIdentifier("FILENAME_WITHOUT_EXTENSION", "raw", getPackageName());
        audio = MediaPlayer.create(this, id);
        audio.start();
    }
}
