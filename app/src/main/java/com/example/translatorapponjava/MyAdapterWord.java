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

public class MyAdapterWord extends ArrayAdapter<WordNames> {
    private Context mContext;
    private ArrayList<WordNames> ArrayWord;

    public MyAdapterWord(@NonNull Context context,ArrayList<WordNames> arrayword) {
        super(context, 0,arrayword);
        this.mContext = context;
        this.ArrayWord = arrayword;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null){
            row = LayoutInflater.from(this.mContext).inflate(R.layout.row_word,parent,false);
        }
        TextView WordName = (TextView)row.findViewById(R.id.WordName);
        TextView TranslateName = (TextView)row.findViewById(R.id.WordTranslate);
        TextView CountWordReppeat = (TextView)row.findViewById(R.id.CountRepeatWord);
        WordName.setText("Word: "+ this.ArrayWord.get(position).getWordName());
        TranslateName.setText("Translate: "+this.ArrayWord.get(position).getTranslateName());
        CountWordReppeat.setText("Count: "+this.ArrayWord.get(position).getWordCountRepeat());
        return row;
    }
}
