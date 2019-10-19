package com.example.translatorapponjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<ListNames> {
    private Context mContext;
    private ArrayList<ListNames> ArrayNames;

    public MyAdapter(@NonNull Context context, ArrayList<ListNames> arraynames) {
        super(context, 0, arraynames);
        this.mContext = context;
        this.ArrayNames = arraynames;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        // LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        if(row == null)
            row = LayoutInflater.from(this.mContext).inflate(R.layout.row,parent,false);

        TextView myNameList = row.findViewById(R.id.NameText);
        myNameList.setText(this.ArrayNames.get(position).getListName());

        TextView myNameSum = row.findViewById(R.id.PercentComplete);
        myNameSum.setText(this.ArrayNames.get(position).getPercent()+ "%");

        TextView myNameCount = row.findViewById(R.id.CountWord);
        myNameCount.setText(this.ArrayNames.get(position).getCountWord()+" Words");
        return row;
    }
}
