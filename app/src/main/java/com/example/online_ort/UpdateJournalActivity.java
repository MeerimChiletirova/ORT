package com.example.online_ort;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateJournalActivity extends AppCompatActivity {
    EditText name_input2, correct_option_input2, option_1_input2, option_2_input2, option_3_input2, option_4_input2, option_5_input2;
    Button update_btn, delete_btn;

    String id, name, correct_option, option_1, option_2, option_3, option_4, option_5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input2 = findViewById(R.id.name_input2);
        correct_option_input2 = findViewById(R.id.correct_option_input2);
        option_1_input2 = findViewById(R.id.option_1_input2);
        option_2_input2 = findViewById(R.id.option_2_input2);
        option_3_input2 = findViewById(R.id.option_3_input2);
        option_4_input2 = findViewById(R.id.option_4_input2);
        option_5_input2 = findViewById(R.id.option_5_input2);

        update_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.delete_btn);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(UpdateJournalActivity.this);
                dbHelper.updateDate(id, name_input2.getText().toString(),
                        correct_option_input2.getText().toString(),
                        option_1_input2.getText().toString(),
                        option_2_input2.getText().toString(),
                        option_3_input2.getText().toString(),
                        option_4_input2.getText().toString(),
                        option_5_input2.getText().toString());
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("correct_option") && getIntent().hasExtra("option_1") &&
                getIntent().hasExtra("option_2") && getIntent().hasExtra("option_3") &&
                getIntent().hasExtra("option_4") && getIntent().hasExtra("option_5")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            correct_option = getIntent().getStringExtra("correct_option");
            option_1 = getIntent().getStringExtra("option_1");
            option_2 = getIntent().getStringExtra("option_2");
            option_3 = getIntent().getStringExtra("option_3");
            option_4 = getIntent().getStringExtra("option_4");
            option_5 = getIntent().getStringExtra("option_5");

            name_input2.setText(name);
            correct_option_input2.setText(correct_option);
            option_1_input2.setText(option_1);
            option_2_input2.setText(option_2);
            option_3_input2.setText(option_3);
            option_4_input2.setText(option_4);
            option_5_input2.setText(option_5);
        }
        else {
            Toast.makeText(this, "Вопросов нет", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить " + name + " ?");
        builder.setMessage("Вы действительно хотите удалить " + name + " ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                JurnalDBHelper dbHelper = new JurnalDBHelper(UpdateJournalActivity.this);
                dbHelper.deleteOneRowJournal(id);
                finish();
                Intent intent = new Intent(UpdateJournalActivity.this,JurnalActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}