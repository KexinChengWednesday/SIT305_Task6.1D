package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Button submitBtn = findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(v -> startActivity(new Intent(this, ResultActivity.class)));
    }
}