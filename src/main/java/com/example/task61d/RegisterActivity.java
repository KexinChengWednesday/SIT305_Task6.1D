package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button createBtn = findViewById(R.id.btnCreateAccount);
        createBtn.setOnClickListener(v -> startActivity(new Intent(this, InterestActivity.class)));
    }
}
