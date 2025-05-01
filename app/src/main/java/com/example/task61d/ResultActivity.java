package com.example.task61d;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.tvResults);
        resultText.setText("1. Correct!\n2. Great explanation.\n3. Needs improvement.");

        Button continueBtn = findViewById(R.id.btnContinue);
        continueBtn.setOnClickListener(v -> finish());
    }
}
