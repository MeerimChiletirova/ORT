package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText name_input, correct_option_input, option_1_input, option_2_input, option_3_input, option_4_input, option_5_input;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        correct_option_input = findViewById(R.id.correct_option_input);
        option_1_input = findViewById(R.id.option_1_input);
        option_2_input = findViewById(R.id.option_2_input);
        option_3_input = findViewById(R.id.option_3_input);
        option_4_input = findViewById(R.id.option_4_input);
        option_5_input = findViewById(R.id.option_5_input);

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                Data data = new Data(name_input.getText().toString(),
                        correct_option_input.getText().toString(),
                        option_1_input.getText().toString(),
                        option_2_input.getText().toString(),
                        option_3_input.getText().toString(),
                        option_4_input.getText().toString(),
                        option_5_input.getText().toString());
                dbHelper.AddQuestion(data);
            }
        });
    }
}