package com.example.sonidos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.webView)
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        myWebView.getSettings().setLoadsImagesAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Macario":
                        myWebView.loadUrl("https://www.google.com/search?q=Macario");
                        break;
                    case "Office Space":
                        myWebView.loadUrl("https://www.google.com/search?q=Office%20Space");
                        break;
                    case "Blood In, Blood Out":
                        myWebView.loadUrl("https://www.google.com/search?q=Blood%20In,%20Blood%20Out");
                        break;
                    case "Ahí está el detalle":
                        myWebView.loadUrl("https://www.google.com/search?q=Ah%C3%AD%20est%C3%A1%20el%20detalle");
                        break;
                    case "The Iron Giant":
                        myWebView.loadUrl("https://www.google.com/search?q=The%20Iron%20Giant");
                        break;
                    default:
                        myWebView.loadUrl("http://www.example.com");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
