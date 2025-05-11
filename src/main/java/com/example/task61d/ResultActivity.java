package com.example.task61d;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ScrollView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout container = findViewById(R.id.resultContainer);
        Button continueBtn = findViewById(R.id.btnContinue);

        int score = 0;

        try {
            String quizData = getIntent().getStringExtra("quiz_data");
            JSONArray quizArray = new JSONArray(quizData);
            JSONArray userAnswers = new JSONArray(getIntent().getStringExtra("user_answers"));

            for (int i = 0; i < quizArray.length(); i++) {
                JSONObject q = quizArray.getJSONObject(i);
                String question = q.getString("question");
                String correct = q.getString("correct_answer");
                JSONArray options = q.getJSONArray("options");
                String user = userAnswers.getString(i);

                TextView tv = new TextView(this);
                StringBuilder sb = new StringBuilder();
                sb.append("Q").append(i + 1).append(": ").append(question).append("\n");

                for (int j = 0; j < options.length(); j++) {
                    sb.append((char) ('A' + j)).append(". ").append(options.getString(j)).append("\n");
                }

                sb.append("✅ Correct: ").append(correct).append("\n");
                sb.append("🟢 Your answer: ").append(user).append("\n");

                if (user.equals(correct)) {
                    sb.append("🎯 Result: Correct ✅\n");
                    score++;
                } else {
                    sb.append("❌ Result: Incorrect\n");
                }

                sb.append("\n");
                tv.setText(sb.toString());
                tv.setPadding(0, 16, 0, 16);
                container.addView(tv);
            }

            TextView scoreView = new TextView(this);
            scoreView.setText("📊 Total Score: " + score + "/" + quizArray.length());
            scoreView.setPadding(0, 24, 0, 24);
            container.addView(scoreView);

        } catch (Exception e) {
            e.printStackTrace();
        }

        continueBtn.setOnClickListener(v -> finish());
    }
}
