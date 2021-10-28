package com.example.uasbasisdata;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BukuAdapter extends ArrayAdapter<Buku> {
    public BukuAdapter(Context context, ArrayList<Buku> buku){
        super(context, 0, buku);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView==null){
            convertView=((Activity)getContext()).getLayoutInflater().inflate(R.layout.buku_list, parent,false);
        }
        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView judulbuku = (TextView) convertView.findViewById(R.id.judulbuku);
        TextView penulis = (TextView) convertView.findViewById(R.id.penulis);

        Buku buku = getItem(position);
        id.setText(buku.getId());
        judulbuku.setText(buku.getJudulbuku());
        penulis.setText(buku.getPenulis());
        return convertView;
    }
}
