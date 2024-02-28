package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    DBHelper dbHelper;
    TextView question_text;
    Button option_1_btn, option_2_btn, option_3_btn, option_4_btn;
    int index = 0;
    String n = "";
    public int[] array;
    public int count = 0;
    public int count_max = 0;
    public int count_question = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        question_text = findViewById(R.id.question_text);
        option_1_btn = findViewById(R.id.option_1_btn);
        option_2_btn = findViewById(R.id.option_2_btn);
        option_3_btn = findViewById(R.id.option_3_btn);
        option_4_btn = findViewById(R.id.option_4_btn);

        dbHelper = new DBHelper(TestActivity.this);

        Cursor cursor1 = dbHelper.readAllData();
        cursor1.getCount();
        Toast.makeText(this, String.valueOf(cursor1.getCount()), Toast.LENGTH_SHORT).show();

        int size = Integer.parseInt(String.valueOf(cursor1.getCount()));
        if (size > Integer.parseInt(String.valueOf(cursor1.getCount()))) {
            System.out.println("Размер массива не может быть больше 10, так как числа не должны повторяться.");
            return;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(String.valueOf(cursor1.getCount())); i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }

        Cursor cursor = dbHelper.readOneData(1);

        while (cursor.moveToNext()) {
            question_text.setText(cursor.getString(1));
            option_1_btn.setText(cursor.getString(3));
            option_2_btn.setText(cursor.getString(4));
            option_3_btn.setText(cursor.getString(5));
            option_4_btn.setText(cursor.getString(6));
        }
    }

    public void option_1_click(View view) {

        if (index >= 9) {
            Intent intent = new Intent(this, FinichTestActivity.class);
            intent.putExtra("count", String.valueOf(count));
            intent.putExtra("count_max", String.valueOf(count_max));
            intent.putExtra("count_question", String.valueOf(count_question));
            startActivity(intent);
        }

        question_text = findViewById(R.id.question_text);
        option_1_btn = findViewById(R.id.option_1_btn);
        option_2_btn = findViewById(R.id.option_2_btn);
        option_3_btn = findViewById(R.id.option_3_btn);
        option_4_btn = findViewById(R.id.option_4_btn);

        dbHelper = new DBHelper(TestActivity.this);

        Cursor cursor = dbHelper.readOneData(array[index]);

        while (cursor.moveToNext()) {
            if (option_1_btn.getText().toString().trim().equals(cursor.getString(2).toString().trim())) {
                count += Integer.parseInt(cursor.getString(7));
                count_question += 1;
                count_max += Integer.parseInt(cursor.getString(7));
                //Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show();
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }

            }
            else {
                //Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show();
                count_max += Integer.parseInt(cursor.getString(7));
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }
            }
        }
    }

    public void option_2_click(View view) {

        if (index >= 9) {
            Intent intent = new Intent(this, FinichTestActivity.class);
            intent.putExtra("count", String.valueOf(count));
            intent.putExtra("count_max", String.valueOf(count_max));
            intent.putExtra("count_question", String.valueOf(count_question));
            startActivity(intent);
        }

        question_text = findViewById(R.id.question_text);
        option_1_btn = findViewById(R.id.option_1_btn);
        option_2_btn = findViewById(R.id.option_2_btn);
        option_3_btn = findViewById(R.id.option_3_btn);
        option_4_btn = findViewById(R.id.option_4_btn);

        dbHelper = new DBHelper(TestActivity.this);

        Cursor cursor = dbHelper.readOneData(array[index]);

        while (cursor.moveToNext()) {
            if (option_2_btn.getText().toString().trim().equals(cursor.getString(2).toString().trim())) {
                count += Integer.parseInt(cursor.getString(7));
                count_question += 1;
                count_max += Integer.parseInt(cursor.getString(7));
                //Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show();
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }

            }
            else {
                //Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show();
                count_max += Integer.parseInt(cursor.getString(7));
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }
            }
        }
    }
    public void option_3_click(View view) {

        if (index >= 9) {
            Intent intent = new Intent(this, FinichTestActivity.class);
            intent.putExtra("count", String.valueOf(count));
            intent.putExtra("count_max", String.valueOf(count_max));
            intent.putExtra("count_question", String.valueOf(count_question));
            startActivity(intent);
        }

        question_text = findViewById(R.id.question_text);
        option_1_btn = findViewById(R.id.option_1_btn);
        option_2_btn = findViewById(R.id.option_2_btn);
        option_3_btn = findViewById(R.id.option_3_btn);
        option_4_btn = findViewById(R.id.option_4_btn);

        dbHelper = new DBHelper(TestActivity.this);

        Cursor cursor = dbHelper.readOneData(array[index]);

        while (cursor.moveToNext()) {
            if (option_3_btn.getText().toString().trim().equals(cursor.getString(2).toString().trim())) {
                count += Integer.parseInt(cursor.getString(7));
                count_question += 1;
                count_max += Integer.parseInt(cursor.getString(7));
                //Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show();
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }

            }
            else {
                //Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show();
                count_max += Integer.parseInt(cursor.getString(7));
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }
            }
        }
    }

    public void option_4_click(View view) {

        if (index >= 9) {
            Intent intent = new Intent(this, FinichTestActivity.class);
            intent.putExtra("count", String.valueOf(count));
            intent.putExtra("count_max", String.valueOf(count_max));
            intent.putExtra("count_question", String.valueOf(count_question));
            startActivity(intent);
        }

        question_text = findViewById(R.id.question_text);
        option_1_btn = findViewById(R.id.option_1_btn);
        option_2_btn = findViewById(R.id.option_2_btn);
        option_3_btn = findViewById(R.id.option_3_btn);
        option_4_btn = findViewById(R.id.option_4_btn);

        dbHelper = new DBHelper(TestActivity.this);

        Cursor cursor = dbHelper.readOneData(array[index]);

        while (cursor.moveToNext()) {
            if (option_4_btn.getText().toString().trim().equals(cursor.getString(2).toString().trim())) {
                count += Integer.parseInt(cursor.getString(7));
                count_question += 1;
                count_max += Integer.parseInt(cursor.getString(7));
                //Toast.makeText(this, "Правильный ответ", Toast.LENGTH_SHORT).show();
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }

            }
            else {
                //Toast.makeText(this, "Неправильный ответ", Toast.LENGTH_SHORT).show();
                count_max += Integer.parseInt(cursor.getString(7));
                index += 1;

                cursor = dbHelper.readOneData(array[index]);

                while (cursor.moveToNext()) {
                    question_text.setText(cursor.getString(1));
                    option_1_btn.setText(cursor.getString(3));
                    option_2_btn.setText(cursor.getString(4));
                    option_3_btn.setText(cursor.getString(5));
                    option_4_btn.setText(cursor.getString(6));
                }
            }
        }
    }
}