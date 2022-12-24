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
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    Button login;
    SharedPreferences SESSION;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //--> cek apakah sudah login atau belum apabila sudah login maka tidak akan bisa masuk login lagi
        SharedPreferences cekSESSION = getApplicationContext().getSharedPreferences("isUserLogin", Context.MODE_PRIVATE);
        String SESSION_NIM = cekSESSION.getString("nim","");
//        Toast.makeText(getBaseContext(), SESSION_NIM, Toast.LENGTH_SHORT).show();
        if(!SESSION_NIM.isEmpty()){
            Intent intent =new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }


        SESSION = getSharedPreferences("isUserLogin", Context.MODE_PRIVATE);
        login = (Button) findViewById(R.id.Login);
        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "User-database").allowMainThreadQueries().build();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText NIM , Password ;
                NIM=findViewById(R.id.NIM);
                Password = findViewById(R.id.Password);
                String nim = NIM.getText().toString();
                String password = Password.getText().toString();
//                Toast.makeText(getBaseContext(),nim+" " +password, Toast.LENGTH_LONG).show();
                List<User> findLogin = db.userDao().findLogin(nim,password);
                if (findLogin.isEmpty()) {
                    Toast.makeText(getBaseContext(), " NIM atau Password Salah" , Toast.LENGTH_SHORT).show();
                    Log.d("pesan" ,  "NIM atau Password Salah ");
                }
                else{
                    SharedPreferences.Editor Put = SESSION.edit();
                    Put.putString("nim",nim);
                    Put.putString("password",password);
                    Toast.makeText(getBaseContext(), " LOGIN SUKSES" , Toast.LENGTH_SHORT).show();
                    Log.d("pesan" ,  "Login Berhasil ");
                    Put.commit();
                    NIM.getText().clear();
                    Password.getText().clear();
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class );
                    startActivity(intent);
                    finish();
                    //            Log.d("pesan", findUser+" ");

                }
                NIM.getText().clear();
                Password.getText().clear();

            }
        });

    }
}