package com.example.online_ort;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuestionsPanel extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button, search_button;
    EditText search_text;

    DBHelper dbHelper;
    ArrayList<String> question_id, question_name, question_correct_option, question_option_1, question_option_2, question_option_3, question_option_4, question_option_5;
    CustomAdapter customAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_panel);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionsPanel.this, AddActivity.class);
                startActivity(intent);
            }
        });

        search_text = findViewById(R.id.search_text);
        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper = new DBHelper(QuestionsPanel.this);
                question_id = new ArrayList<>();
                question_name = new ArrayList<>();
                question_correct_option = new ArrayList<>();
                question_option_1 = new ArrayList<>();
                question_option_2 = new ArrayList<>();
                question_option_3 = new ArrayList<>();
                question_option_4 = new ArrayList<>();
                question_option_5 = new ArrayList<>();

                Cursor cursor = dbHelper.readSearchData(search_text.getText().toString());
                if(cursor.getCount() == 0) {
                    Toast.makeText(QuestionsPanel.this, "Ничего не найдено", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (cursor.moveToNext()) {
                        question_id.add(cursor.getString(0));
                        question_name.add(cursor.getString(1));
                        question_correct_option.add(cursor.getString(2));
                        question_option_1.add(cursor.getString(3));
                        question_option_2.add(cursor.getString(4));
                        question_option_3.add(cursor.getString(5));
                        question_option_4.add(cursor.getString(6));
                        question_option_5.add(cursor.getString(7));
                    }
                }

                customAdapter = new CustomAdapter(QuestionsPanel.this, QuestionsPanel.this, question_id, question_name, question_correct_option, question_option_1, question_option_2, question_option_3, question_option_4, question_option_5);
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(QuestionsPanel.this));
            }
        });


        dbHelper = new DBHelper(QuestionsPanel.this);
        question_id = new ArrayList<>();
        question_name = new ArrayList<>();
        question_correct_option = new ArrayList<>();
        question_option_1 = new ArrayList<>();
        question_option_2 = new ArrayList<>();
        question_option_3 = new ArrayList<>();
        question_option_4 = new ArrayList<>();
        question_option_5 = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(QuestionsPanel.this, this, question_id, question_name, question_correct_option, question_option_1, question_option_2, question_option_3, question_option_4, question_option_5);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuestionsPanel.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Вопросов нет", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                question_id.add(cursor.getString(0));
                question_name.add(cursor.getString(1));
                question_correct_option.add(cursor.getString(2));
                question_option_1.add(cursor.getString(3));
                question_option_2.add(cursor.getString(4));
                question_option_3.add(cursor.getString(5));
                question_option_4.add(cursor.getString(6));
                question_option_5.add(cursor.getString(7));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all) {
            Toast.makeText(this, "Все записи удалены!", Toast.LENGTH_SHORT).show();
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.deleteAllData();
            Intent intent = new Intent(this, QuestionsPanel.class);
            startActivity(intent);  
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}