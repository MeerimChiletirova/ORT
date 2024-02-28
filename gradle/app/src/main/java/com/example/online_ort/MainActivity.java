package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startTest(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
    public void adminPanel(View view) {
        Intent intent = new Intent(this, AdminPanelActivity.class);
        startActivity(intent);
    }
    public void exitApp(View view) {
        super.onBackPressed();
    }
}