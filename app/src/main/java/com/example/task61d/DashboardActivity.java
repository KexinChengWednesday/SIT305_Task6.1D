package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        String username = getIntent().getStringExtra("username");
        TextView greeting = findViewById(R.id.tvGreeting);
        greeting.setText("Hello, " + username);

        LinearLayout taskCard = findViewById(R.id.taskCard);
        taskCard.setOnClickListener(v -> startActivity(new Intent(this, TaskDetailActivity.class)));
    }
}