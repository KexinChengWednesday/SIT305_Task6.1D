package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class InterestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        Button nextBtn = findViewById(R.id.btnNext);
        nextBtn.setOnClickListener(v -> startActivity(new Intent(this, DashboardActivity.class)));
    }
}
