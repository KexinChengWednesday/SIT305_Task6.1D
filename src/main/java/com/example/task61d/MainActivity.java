package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.etUsername);
        passwordEditText = findViewById(R.id.etPassword);
        Button loginBtn = findViewById(R.id.btnLogin);
        TextView registerLink = findViewById(R.id.tvRegister);

        loginBtn.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        registerLink.setOnClickListener(v -> startActivity(new Intent(this, RegisterActivity.class)));
    }
}
