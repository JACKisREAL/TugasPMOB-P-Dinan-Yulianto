package com.example.pastibisa2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView nim,password;
        nim = findViewById(R.id.homenim);
        password = findViewById(R.id.homePassword);
        SharedPreferences SESSION = getApplicationContext().getSharedPreferences("isUserLogin", Context.MODE_PRIVATE);
        String text1 = SESSION.getString("nim","");
        String text2 = SESSION.getString("password", "");
        nim.setText(text1);
        password.setText(text2);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor Put = SESSION.edit();
                Put.clear();
                Put.commit();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}