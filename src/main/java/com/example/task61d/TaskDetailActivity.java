package com.example.task61d;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TaskDetailActivity extends AppCompatActivity {

    private LinearLayout quizContainer;
    private Button submitBtn;
    private ProgressBar progressBar;
    private JSONArray quizArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        quizContainer = findViewById(R.id.quizContainer);
        submitBtn = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressBar);

        quizContainer.setVisibility(View.GONE);
        submitBtn.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        fetchQuiz();

        submitBtn.setOnClickListener(v -> {
            ArrayList<String> selectedAnswers = new ArrayList<>();
            for (int i = 0; i < quizArray.length(); i++) {
                RadioGroup group = findViewById(100 + i);
                int checkedId = group.getCheckedRadioButtonId();
                if (checkedId != -1) {
                    RadioButton selected = findViewById(checkedId);
                    selectedAnswers.add(selected.getText().toString());
                } else {
                    selectedAnswers.add("No Answer");
                }
            }

            Intent intent = new Intent(TaskDetailActivity.this, ResultActivity.class);
            intent.putExtra("quiz_data", quizArray.toString());
            intent.putExtra("user_answers", new JSONArray(selectedAnswers).toString());
            startActivity(intent);
        });
    }

    private void fetchQuiz() {
        String url = "http://10.0.2.2:5000/getQuiz";  // 本地模拟器访问

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.GONE);
                    TextView errorText = new TextView(TaskDetailActivity.this);
                    errorText.setText("Failed to load quiz.");
                    quizContainer.addView(errorText);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    runOnUiThread(() -> renderQuiz(json));
                }
            }
        });
    }

    private void renderQuiz(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            quizArray = obj.getJSONArray("quiz");

            for (int i = 0; i < quizArray.length(); i++) {
                JSONObject qObj = quizArray.getJSONObject(i);
                String question = qObj.getString("question");
                JSONArray options = qObj.getJSONArray("options");

                TextView qText = new TextView(this);
                qText.setText((i + 1) + ". " + question);
                qText.setTextSize(16);
                qText.setPadding(0, 16, 0, 8);
                quizContainer.addView(qText);

                RadioGroup group = new RadioGroup(this);
                group.setId(100 + i);
                for (int j = 0; j < options.length(); j++) {
                    RadioButton btn = new RadioButton(this);
                    btn.setText(options.getString(j));
                    group.addView(btn);
                }
                quizContainer.addView(group);
            }

            progressBar.setVisibility(View.GONE);
            quizContainer.setVisibility(View.VISIBLE);
            submitBtn.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
