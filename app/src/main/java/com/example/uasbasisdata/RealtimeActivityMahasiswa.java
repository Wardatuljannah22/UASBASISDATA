package com.example.uasbasisdata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RealtimeActivityMahasiswa extends AppCompatActivity {
    EditText nim, nama, alamat;
    RadioGroup jenis_kelamin;
    RadioButton laki_laki, perempuan;
    Spinner jurusan;
    ListView mahasiswa_list;

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayList<String> listKeys = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_mahasiswa);

        nim = (EditText) findViewById(R.id.nim);
        nama = (EditText) findViewById(R.id.nama);
        alamat = (EditText) findViewById(R.id.alamat);
        jenis_kelamin = (RadioGroup) findViewById(R.id.jenis_kelamin);
        laki_laki = (RadioButton) findViewById(R.id.laki_laki);
        perempuan = (RadioButton) findViewById(R.id.perempuan);
        jurusan = (Spinner) findViewById(R.id.jurusan);
        mahasiswa_list = (ListView) findViewById(R.id.mahasiswa_list);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);
        mahasiswa_list.setAdapter(adapter);

        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mhsRef = database.getReference("mahasiswa");

        mahasiswa_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                int selected = position;
                mhsRef.child(listKeys.get(position)).removeValue();
            }
        });

        // Read from the database
        mhsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String nama = dataSnapshot.child("nama").getValue().toString();
                String nim = dataSnapshot.child("nim").getValue().toString();
                listItems.add(nim + " - " + nama);
                listKeys.add(dataSnapshot.getKey());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = listKeys.indexOf(key);

                if (index != 1) {
                    listItems.remove(index);
                    listKeys.remove(index);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddMahasiswa(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference todoRef = database.getReference("mahasiswa");

        String Nim = nim.getText().toString();
        String Nama = nama.getText().toString();
        String Alamat = alamat.getText().toString();
        String JenisKelamin = "";
        if (laki_laki.isChecked()){
            JenisKelamin = laki_laki.getText().toString();
        } else if(perempuan.isChecked()){
            JenisKelamin = perempuan.getText().toString();
        }
        String Jurusan = "";
        Spinner mhs_jurusan = (Spinner) findViewById(R.id.jurusan);
        Jurusan = mhs_jurusan.getSelectedItem().toString();

        todoRef.push().setValue(new Mahasiswa(Nim, Nama, Jurusan, JenisKelamin, Alamat, false));
        nim.setText("");
        nama.setText("");
        alamat.setText("");
        Toast.makeText(this, "Data sudah tersimpan", Toast.LENGTH_SHORT).show();
    }

    public void logoutProcess(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent tampilanAwal = new Intent(RealtimeActivityMahasiswa.this, MainActivity.class);
        startActivity(tampilanAwal);
    }
}