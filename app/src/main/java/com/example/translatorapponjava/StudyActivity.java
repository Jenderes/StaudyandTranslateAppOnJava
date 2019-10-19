package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudyActivity extends AppCompatActivity {
    TextView textCounter;
    TextView textListName;
    TextView Wordname;
    TextView WordTranslateName;
    TextView CheckAnswer;
    Button buttAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        textListName = (TextView)findViewById(R.id.NameList);
        buttAnswer = (Button)findViewById(R.id.buttonAnswer);
        Bundle arg = getIntent().getExtras();
        textListName.setText(arg.get("NameList").toString());

        buttAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void ChooseWord(){
        textCounter = (TextView)findViewById(R.id.CheckCountWord);
        Wordname = (TextView)findViewById(R.id.textQuestion);
        WordTranslateName = (TextView)findViewById(R.id.textAnswer);
        CheckAnswer = (TextView)findViewById(R.id.checkAnswer);

    }
}
