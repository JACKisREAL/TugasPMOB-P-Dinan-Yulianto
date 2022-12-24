package com.example.pastibisa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences SESSION = getApplicationContext().getSharedPreferences("isUserLogin", Context.MODE_PRIVATE);
        String SESSION_NIM = SESSION.getString("nim","");
        Toast.makeText(getBaseContext(), SESSION_NIM, Toast.LENGTH_SHORT).show();
        if(!SESSION_NIM.isEmpty()){
            Intent intent =new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        Button login, register;

        login = findViewById(R.id.login);
        register = findViewById(R.id.resgister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrasi = new Intent(MainActivity.this, RegisterActivity.class );
                startActivity(registrasi);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
                finish();
            }
        });
//        //test saja
//        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
//                UserDatabase.class, "mahasiswa-database").allowMainThreadQueries().build();
//        List<User> UserList = db.userDao().getAllPersons();
//        for (User list : UserList){
//            Log.d("pesan",list.nim+" "+list.password);
//        }

    }
}