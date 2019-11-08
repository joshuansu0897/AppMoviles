package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.DB.DatabaseClient;
import com.example.login.DB.User;
import com.example.login.utils.ImageUtils;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;

    private User user;

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.passwordC)
    TextView passwordC;

    @BindView(R.id.logo)
    ImageView imageView;

    @OnClick(R.id.foto)
    void submit() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @OnClick(R.id.create)
    void create(View view) {
        user.setUsername(username.getText().toString().trim());
        if (user.getUsername().equals("")) {
            Snackbar.make(view, "El Username es necesario", Snackbar.LENGTH_LONG).show();
            return;
        }

        System.out.println("user.getUsername()");
        System.out.println(user.getUsername());

        if (user.getPhoto() == null || user.getPhoto().equals("")) {
            Snackbar.make(view, "Foto necesaria", Snackbar.LENGTH_LONG).show();
            return;
        }

        System.out.println("user.getPhoto()");
        System.out.println(user.getPhoto());

        String pwd = password.getText().toString().trim();
        String pwdC = passwordC.getText().toString().trim();

        if (pwd.equals("") || !pwd.equals(pwdC)) {
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).show();
            return;
        }
        user.setPassword(pwd);

        System.out.println("user.getPassword()");
        System.out.println(user.getPassword());


        class SaveUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(user);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveUser st = new SaveUser();
        st.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        user = new User();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);

            user.setPhoto(ImageUtils.convert(photo));
        }
    }
}
