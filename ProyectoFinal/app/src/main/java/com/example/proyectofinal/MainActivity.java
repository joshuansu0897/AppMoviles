package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.proyectofinal.DB.DatabaseClient;
import com.example.proyectofinal.DB.User;
import com.example.proyectofinal.views.Menu;
import com.example.proyectofinal.views.SignUp;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin)
    void signin(View view) {
        String usern = username.getText().toString().trim();
        String paswd = password.getText().toString().trim();

        if (usern.equals("")) {
            Snackbar.make(view, "El Username es necesario", Snackbar.LENGTH_LONG).show();
            return;
        }

        class GetTasks extends AsyncTask<Void, Void, User> {

            @Override
            protected User doInBackground(Void... voids) {
                User user = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getByUsername(usern);
                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if (user == null) {
                    Snackbar.make(view, "No se encontro al usuario con el username:" + usern, Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (!paswd.equals(user.getPassword())) {
                    Snackbar.make(view, "Error en el password", Snackbar.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(view.getContext(), Menu.class);
                intent.putExtra(Menu.EXTRA_TODO_KEY, user);
                view.getContext().startActivity(intent);
                finish();
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @OnClick(R.id.signup)
    void signup() {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
