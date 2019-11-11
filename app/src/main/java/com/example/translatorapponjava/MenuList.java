package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuList extends AppCompatActivity {
    String NameList,IDList,PercenIn,CountWordIn;
    TextView ListNamelist,Percent,CountWords;
    RelativeLayout StudyWord,ViewWord,ClearCountWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        InitializeField();

        StudyWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendToStudyActivityFromMenuList();
            }
        });

        ViewWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SentToAllListWordFormMenuList();
            }
        });

        ClearCountWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void InitializeField() {
        Bundle arg = getIntent().getExtras();
        IDList = arg.get("IDList").toString();
        NameList = arg.get("NameList").toString();
        CountWordIn = arg.get("CountWord").toString();
        PercenIn= arg.get("Percent").toString();
        ListNamelist = (TextView)findViewById(R.id.ListNamelist);
        Percent = (TextView)findViewById(R.id.PercentWordThisList);
        CountWords = (TextView)findViewById(R.id.ContWordInThiISList);
        StudyWord = (RelativeLayout)findViewById(R.id.StudyWordList);
        ViewWord = (RelativeLayout)findViewById(R.id.AllWordThisList);
        ClearCountWord = (RelativeLayout)findViewById(R.id.RefreshWordsThisIsList);
        ListNamelist.setText(NameList);
        Percent.setText(PercenIn + "%");
        CountWords.setText(CountWordIn);
    }
    private void SentToAllListWordFormMenuList() {
        Intent IntentAllListWord = new Intent(MenuList.this,ThisIsListWord.class);
        IntentAllListWord.putExtra("IDList",IDList);
        IntentAllListWord.putExtra("NameList",NameList);
        startActivity(IntentAllListWord);
    }

    private void SendToStudyActivityFromMenuList() {
        Intent IntentStudyActivity = new Intent(MenuList.this,StudyActivity.class);
        IntentStudyActivity.putExtra("IDList",IDList);
        IntentStudyActivity.putExtra("NameList",NameList);
        startActivity(IntentStudyActivity);
    }
}
