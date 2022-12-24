package com.example.pastibisa2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        daftar = (Button) findViewById(R.id.Register);

        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "User-database").allowMainThreadQueries().build();
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText NIM , Password ;
                NIM=findViewById(R.id.NIM);
                Password = findViewById(R.id.Password);
                String nim = NIM.getText().toString();
                String password = Password.getText().toString();
//                Toast.makeText(getBaseContext(),nim+" " +password, Toast.LENGTH_LONG).show();
//                User imam = new User("imam");
            List<User> findUser = db.userDao().findUser(nim);
            if (findUser.isEmpty()) {
                //UNTUK MENGINSERT DATA KE TABEL
                User insert = new User(nim, password);
                db.userDao().insertAll(insert);
                Log.d("pesan" ,  "data sudah dimasukkan ");
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(getBaseContext(),"Gunakan Username yang berbeda OK",Toast.LENGTH_SHORT).show();
                Log.d("pesan" ,  "data tidak boleh sama dimasukkan ");
    //            Log.d("pesan", findUser+" ");

            }

            // DIBAWAH INI NUKEDATA UNTUK MENDESTROY SATU DATA TABEL
//                        db.userDao().nukeData();
                // --> UNTUK CEK ISI DATABASE ATAU TABLE
//                List<User> UserList = db.userDao().getAllPersons();
//                for (User list : UserList){
//                    Log.d("pesan",list.id +". " + list.nim+" "+list.password);
//                }
                NIM.getText().clear();
                Password.getText().clear();
            }

        });

//
    }

}