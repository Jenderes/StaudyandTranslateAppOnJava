package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ListNames> ListNames = new ArrayList<ListNames>();
    ListView listView;
    DataBaseHelper dbh;
    DBHelper db;
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateAllList();
    }

    public void CreateAllList (){
        db = new DBHelper(this);
        ListNames = db.InputListTable();
        listView = (ListView)findViewById(R.id.ListAllWords);
        MyAdapter adapter = new MyAdapter(this, ListNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent MenuListIntent = new Intent(MainActivity.this, MenuList.class);
                MenuListIntent.putExtra("IDList", ListNames.get(pos).getIDList());
                MenuListIntent.putExtra("NameList", ListNames.get(pos).getListName());
                MenuListIntent.putExtra("CountWord", ListNames.get(pos).getCountWord());
                MenuListIntent.putExtra("Percent", ListNames.get(pos).getPercent());
                startActivity(MenuListIntent);
            }
        });
    }
    public void CrateAllListOtherCon(){
        dbh = new DataBaseHelper(this);
        ListNames = dbh.InputListTable();
        MyAdapter adapter = new MyAdapter(this, ListNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(MainActivity.this, MenuList.class);
                intent.putExtra("IDList", ListNames.get(pos).getIDList());
                intent.putExtra("NameList", ListNames.get(pos).getListName());
                startActivity(intent);
                Toast.makeText(MainActivity.this, "item Onclick: "+pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void TranslateMain(View v){
        Intent intent = new Intent(this,TranslatorActivity.class);
        startActivity(intent);
        this.overridePendingTransition(0, 0);
    }
}
