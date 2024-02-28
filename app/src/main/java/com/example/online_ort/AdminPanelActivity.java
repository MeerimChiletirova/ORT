package com.example.online_ort;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
    }

    public void open_admin_panel(View view) {
        EditText login_text = findViewById(R.id.login_text);
        EditText password_text = findViewById(R.id.password_text);

        if (login_text.getText().toString().equals("123") && password_text.getText().toString().equals("123")) {
            Intent intent = new Intent(this, QuestionsPanel.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, AdminPanelActivity.class);
            startActivity(intent);
        }
    }
}