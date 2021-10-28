package com.example.uasbasisdata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class FirestoreActivityBuku extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference buku_ref = db.collection("buku");
    EditText judulbuku;
    EditText penulis;
    EditText penerbit;
    EditText stok;
    ListView buku_list;
    ArrayList<Buku> buku_item = new ArrayList<>();
    BukuAdapter adapter;
    private String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore_buku);
        judulbuku = (EditText) findViewById(R.id.judulbuku);
        penulis = (EditText) findViewById(R.id.penulis);
        penerbit = (EditText) findViewById(R.id.penerbit);
        stok = (EditText) findViewById(R.id.stok);
        buku_list = (ListView) findViewById(R.id.buku_list);

        buku_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Buku buku = adapter.getItem(position);
                judulbuku.setText(buku.getJudulbuku());
                penulis.setText(buku.getPenulis());
                penerbit.setText(buku.getPenerbit());
                stok.setText(buku.getStok());
                selectedId = buku.getId();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        buku_ref.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null){
                    return;
                }
                buku_item.clear();
                for (QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    Buku buku = documentSnapshot.toObject(Buku.class);
                    buku.setId(documentSnapshot.getId());
                    buku_item.add(buku);
                }
                adapter = new BukuAdapter(FirestoreActivityBuku.this, buku_item);
                adapter.notifyDataSetChanged();
                buku_list.setAdapter(adapter);
            }
        });
    }

    public void AddBuku(View view) {
        String JudulBuku = judulbuku.getText().toString();
        String Penulis = penulis.getText().toString();
        String Penerbit = penerbit.getText().toString();
        String Stok = stok.getText().toString();

        Map<String, Object> user = new HashMap<>(); //Mengubah dari bentuk string ke objek
        user.put("judulbuku", JudulBuku);
        user.put("penulis", Penulis);
        user.put("penerbir", Penerbit);
        user.put("stok", Stok);

        buku_ref.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        judulbuku.setText(null);
                        penulis.setText(null);
                        penerbit.setText(null);
                        stok.setText(null);
                        Toast.makeText(FirestoreActivityBuku.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivityBuku.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void UpdateBuku(View view) {
        String JudulBuku = judulbuku.getText().toString();
        String Penulis = penulis.getText().toString();
        String Penerbit = penerbit.getText().toString();
        String Stok = stok.getText().toString();

        Buku buku = new Buku(JudulBuku, Penulis, Penerbit, Stok);
        buku_ref.document(selectedId).set(buku)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        judulbuku.setText(null);
                        penulis.setText(null);
                        penerbit.setText(null);
                        stok.setText(null);
                        Toast.makeText(FirestoreActivityBuku.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivityBuku.this, "Error"+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void DeleteBuku(View view) {
        String JudulBuku = judulbuku.getText().toString();
        String Penulis = penulis.getText().toString();
        String Penerbit = penerbit.getText().toString();
        String Stok = stok.getText().toString();

        Buku buku = new Buku(JudulBuku, Penulis,Penerbit, Stok);
        buku_ref.document(selectedId).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        judulbuku.setText(null);
                        penulis.setText(null);
                        penerbit.setText(null);
                        stok.setText(null);
                        Toast.makeText(FirestoreActivityBuku.this, "Deleted", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(FirestoreActivityBuku.this, "Error"+e, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void logoutProcess(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent tampilanAwal = new Intent(FirestoreActivityBuku.this, MainActivity.class);
        startActivity(tampilanAwal);
    }
}
