package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
// TODO: Chane intent listID
public class ThisIsListWord extends AppCompatActivity {
    ArrayList<WordNames> ArrayWord= new ArrayList<WordNames>();
    ListView listView;
    DBHelper db;
    String ListName,ListID;
    TextView NameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this_is_list_word);
        db = new DBHelper(this);
        Bundle arg = getIntent().getExtras();
        ListID = arg.get("IDList").toString();
        ListName = arg.get("NameList").toString();
        ArrayWord = db.InpuntWords(ListID);
        listView = (ListView)findViewById(R.id.AllWordsList);
        NameList =(TextView)findViewById(R.id.NameListAllList);
        NameList.setText(ListName);
        MyAdapterWord adapter = new MyAdapterWord(this, ArrayWord);
        listView.setAdapter(adapter);
    }
}
