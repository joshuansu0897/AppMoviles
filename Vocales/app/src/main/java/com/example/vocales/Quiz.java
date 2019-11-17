package com.example.vocales;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vocales.Utils.Vocal;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Quiz extends AppCompatActivity {

    private ArrayList<Vocal> vocales;
    private ArrayList<String> msgCorrecto;
    private ArrayList<String> msgMal;
    private TextToSpeech textToSpeech;
    private Vocal vocal = null;
    private int malas = 0;
    private int buenas = 0;

    @BindView(R.id.imgQuiz)
    ImageView imgQuiz;
    @BindView(R.id.bien_count)
    TextView bien_count;
    @BindView(R.id.mal_count)
    TextView mal_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        vocales = new ArrayList<>();
        msgMal = new ArrayList<>();
        msgCorrecto = new ArrayList<>();

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
        vocales.add(new Vocal("ukelele", "ukelele", "u"));
        vocales.add(new Vocal("uno", "uno", "u"));
        vocales.add(new Vocal("uva", "uva", "u"));

        msgCorrecto.add("Muy bien! :D");
        msgCorrecto.add("Correcto!");
        msgCorrecto.add("Perfecto :D");

        msgMal.add("Casi :(");
        msgMal.add("No ;-;");
        msgMal.add("Sigue practicando! :D");

        textToSpeech = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });

        setRandomData();
    }

    private void setRandomData() {
        int rnd = randomArray(vocales);
        vocal = vocales.get(rnd);
        int id = getResources().getIdentifier(vocal.getImage(), "raw", getPackageName());
        imgQuiz.setImageResource(id);
    }

    private int randomArray(ArrayList arr) {
        return new Random().nextInt(arr.size());
    }

    private void habla(String palabras) {
        textToSpeech.speak(palabras, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void response(View view) {
        switch (view.getId()) {
            case R.id.a:
                compare(view, "a");
                break;
            case R.id.e:
                compare(view, "e");
                break;
            case R.id.i:
                compare(view, "i");
                break;
            case R.id.o:
                compare(view, "o");
                break;
            case R.id.u:
                compare(view, "u");
                break;
        }
    }

    private void compare(View view, String letter) {
        if (vocal.getVocal().equals(letter)) {
            int rnd = randomArray(msgCorrecto);

            Snackbar mSnackBar = Snackbar.make(view, msgCorrecto.get(rnd), Snackbar.LENGTH_LONG);
            mSnackBar.getView().setBackgroundColor(Color.rgb(75, 175, 75));
            mSnackBar.show();
            buenas++;
            bien_count.setText("correctas:" + buenas);
        } else {
            int rnd = randomArray(msgMal);

            Snackbar mSnackBar = Snackbar.make(view, msgMal.get(rnd), Snackbar.LENGTH_LONG);
            mSnackBar.getView().setBackgroundColor(Color.rgb(220, 75, 75));
            mSnackBar.show();
            malas++;
            mal_count.setText("malas:" + malas);
        }
        setRandomData();
    }

    public void sonido(View view) {
        habla(vocal.getSound());
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
