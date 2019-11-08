package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.DB.User;
import com.example.login.utils.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Detalle extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "User";
    private User user;

    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.password)
    TextView password;
    @BindView(R.id.logo)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);

        user = getUser();
        displayDetail();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void displayDetail() {
        username.setEnabled(false);
        password.setEnabled(false);

        username.setText(user.getUsername());
        password.setText(user.getPassword());

        imageView.setImageBitmap(ImageUtils.convert(user.getPhoto()));
    }

    private User getUser() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (User) extras.getSerializable(EXTRA_TODO_KEY);
        }
        return new User();
    }
}
