package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.time.LocalDate;

public class FinichTestActivity extends AppCompatActivity {

    TextView count_txt, count_max_txt, count_question_txt, correct;

    int count, count_max, count_question;
    String result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finich_test);

        count = Integer.parseInt(getIntent().getStringExtra("count"));
        count_max = Integer.parseInt(getIntent().getStringExtra("count_max"));
        count_question = Integer.parseInt(getIntent().getStringExtra("count_question"));

        count_txt = findViewById(R.id.count_txt);
        count_max_txt = findViewById(R.id.count_max_txt);
        count_question_txt = findViewById(R.id.count_question_txt);
        correct = findViewById(R.id.correct);

        if (Integer.parseInt(getIntent().getStringExtra("count").toString()) >= (Integer.parseInt(getIntent().getStringExtra("count_max").toString())/2)) {
            correct.setText("Вы прошли тест");
        }
        else {
            correct.setText("Вы не прошли тест");
        }

        count_txt.setText(getIntent().getStringExtra("count"));
        count_max_txt.setText("из " + getIntent().getStringExtra("count_max") + " баллов");
        count_question_txt.setText(getIntent().getStringExtra("count_question") + "/10");
    }

    public void jurnal_open(View view) {
        if (Integer.parseInt(getIntent().getStringExtra("count").toString()) >= (Integer.parseInt(getIntent().getStringExtra("count_max").toString())/2)) {
            result = "Вы прошли тест";
        }
        else {
            result = "Вы не прошли тест";
        }

        JurnalDBHelper dbHelper = new JurnalDBHelper( FinichTestActivity.this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dbHelper.AddListInJurnal(result,
                    count,
                    count_max,
                    count_question,
                    LocalDate.now().toString());
        }

        Intent intent = new Intent(this, JurnalActivity.class);
        startActivity(intent);
    }
}