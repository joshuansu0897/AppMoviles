package com.example.vocales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vocales.Utils.Vocal;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aprender extends AppCompatActivity {

    private ArrayList<Vocal> vocales;
    private TextToSpeech textToSpeech;
    private int position = 0;
    private Vocal vocal = null;

    @BindView(R.id.text_aprender)
    TextView textAprender;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;

    @OnClick(R.id.siguiente)
    void next() {
        if (position >= vocales.size() - 3) {
            return;
        }

        position += 3;
        setData();
    }

    @OnClick(R.id.regresar)
    void back() {
        if (position <= 0) {
            return;
        }

        position -= 3;
        setData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender);
        ButterKnife.bind(this);

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
        vocales.add(new Vocal("imaginacion", "imaginación", "i"));
        vocales.add(new Vocal("isla", "isla", "i"));

        //o
        vocales.add(new Vocal("ogro", "ogro", "o"));
        vocales.add(new Vocal("oso", "oso", "o"));
        vocales.add(new Vocal("oveja", "oveja", "o"));

        //u
        vocales.add(new Vocal("ukelele", "ukelele", "u"));
        vocales.add(new Vocal("uno", "uno", "u"));
        vocales.add(new Vocal("uva", "uva", "u"));

        textToSpeech = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.getDefault());
            }
        });

        setData();
    }

    private void setData() {
        vocal = vocales.get(position);
        int id = getResources().getIdentifier(vocal.getImage(), "raw", getPackageName());
        img1.setImageResource(id);

        vocal = vocales.get(position + 1);
        id = getResources().getIdentifier(vocal.getImage(), "raw", getPackageName());
        img2.setImageResource(id);

        vocal = vocales.get(position + 2);
        id = getResources().getIdentifier(vocal.getImage(), "raw", getPackageName());
        img3.setImageResource(id);

        switch (vocal.getVocal()) {
            case "a":
                textAprender.setText("a A");
                break;
            case "e":
                textAprender.setText("e E");
                break;
            case "i":
                textAprender.setText("i I");
                break;
            case "o":
                textAprender.setText("o O");
                break;
            case "u":
                textAprender.setText("u U");
                break;
        }
    }

    private void habla(String palabras) {
        textToSpeech.speak(palabras, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void sonido(View view) {
        switch (view.getId()) {
            case R.id.img1:
                vocal = vocales.get(position);
                habla(vocal.getSound());
                break;
            case R.id.img2:
                vocal = vocales.get(position + 1);
                habla(vocal.getSound());
                break;
            case R.id.img3:
                vocal = vocales.get(position + 2);
                habla(vocal.getSound());
                break;
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
