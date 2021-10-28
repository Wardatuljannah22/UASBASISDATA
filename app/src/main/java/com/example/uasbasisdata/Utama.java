package com.example.uasbasisdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Utama extends AppCompatActivity {
    private int waktu_loading = 3000; //3000=3 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(Utama.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        },waktu_loading);
    }
}
