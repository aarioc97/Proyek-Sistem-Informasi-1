package com.example.user.prosi_1;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class BarangInfoAdapter extends ArrayAdapter<PostBarang> {
    private Activity context;
    private List<PostBarang> listBarang;
    private GridView gridview;

    public BarangInfoAdapter(Activity context, List<PostBarang> listBarang) {
        super(context, R.layout.tab_home, listBarang);
        this.context = context;
        this.listBarang = listBarang;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View gridView = inflater.inflate(R.layout.tab_home, null, true);

//        this.gridview = this

//        TextView namaBarang =

        return gridView;
    }
}
