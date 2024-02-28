package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class JurnalActivity extends AppCompatActivity {

    RecyclerView recyclerView1;
    JurnalDBHelper dbHelper;
    ArrayList<String> jurnal_id, jurnal_correct, jurnal_count, jurnal_count_max, jurnal_count_question, jurnal_time, qwe;
    String dates;
    Adapter customAdapter1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal);

        recyclerView1 = findViewById(R.id.recyclerView1);
        dbHelper = new JurnalDBHelper(JurnalActivity.this);
        jurnal_id = new ArrayList<>();
        jurnal_correct = new ArrayList<>();
        jurnal_count = new ArrayList<>();
        jurnal_count_max = new ArrayList<>();
        jurnal_count_question = new ArrayList<>();
        jurnal_time = new ArrayList<>();

        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "Результатов нет", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                jurnal_id.add(cursor.getString(0));
                jurnal_correct.add(cursor.getString(1));
                jurnal_count.add("Вы набрали " + cursor.getString(2) + " балл");
                jurnal_count_max.add("из " + cursor.getString(3) + " баллов");
                jurnal_count_question.add(cursor.getString(4)+"/10");
                dates = cursor.getString(5).toString();
                Toast.makeText(this, dates, Toast.LENGTH_SHORT).show();
                jurnal_time.add(dates);
            }
        }

        customAdapter1 = new Adapter(JurnalActivity.this, this, jurnal_id, jurnal_correct, jurnal_count, jurnal_count_max, jurnal_count_question, jurnal_time, jurnal_correct, jurnal_correct);
        recyclerView1.setAdapter(customAdapter1);
        customAdapter1.notifyDataSetChanged();

        recyclerView1.setLayoutManager(new LinearLayoutManager(JurnalActivity.this));
    }
}