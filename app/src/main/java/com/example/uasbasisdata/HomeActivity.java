package com.example.uasbasisdata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    CardView cardMahasiswa, cardBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cardMahasiswa = (CardView) findViewById(R.id.cardMahasiswa);
        cardBuku = (CardView) findViewById(R.id.cardBuku);

        cardMahasiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mahasiswa = new Intent(HomeActivity.this, RealtimeActivityMahasiswa.class);
                startActivity(mahasiswa);
            }
        });

        cardBuku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buku = new Intent(HomeActivity.this, FirestoreActivityBuku.class);
                startActivity(buku);
            }
        });
    }

    public void logoutProcess(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent tampilanAwal = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(tampilanAwal);
    }
}
