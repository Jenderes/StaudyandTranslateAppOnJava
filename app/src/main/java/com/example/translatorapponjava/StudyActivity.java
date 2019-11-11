package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    TextView textCounter,  textListName, Wordname, WordTranslateName, CheckAnswer;
    Button buttAnswer,ButtonExit;
    EditText AnswerText;
    ActionBar studyActionbar;

    private Handler mHandler = new Handler();
    private DBHelper dbw;
    private ArrayList<WordNames> WordNames = new ArrayList<WordNames>();
    String listid;
    String listName;
    int RandPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        // Create back button
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        textListName = (TextView)findViewById(R.id.NameList);
        buttAnswer = (Button)findViewById(R.id.buttonAnswer);
        ButtonExit = (Button)findViewById(R.id.buttonExit);
        // get data with home display
        Bundle arg = getIntent().getExtras();
        listid =arg.get("IDList").toString();
        listName = arg.get("NameList").toString();
        // Change toolBar


        // get ArrayList word from database
        dbw = new DBHelper(this);
        WordNames = dbw.InpuntWords(listid);
        // set text Name List
        textListName.setText(listid);
        // Set first word
        firstChooseWord();
            buttAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseWord();
            }
        });
             ButtonExit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     ChangeCountRepets();
                 }
             });
    }

    private void ChangeCountRepets() {
//        new DataBaseThread(WordNames).execute();
          dbw.UpdateWordCount(WordNames);
    }

    public void firstChooseWord(){
        textCounter = (TextView)findViewById(R.id.CheckCountWord);
        Wordname = (TextView)findViewById(R.id.textQuestion);
        CheckAnswer = (TextView)findViewById(R.id.checkAnswer);
        // get random word
        RandPos = WordListRecount.getfirstrandomword(WordNames);
        textCounter.setText(Integer.toString(WordNames.get(RandPos).getWordCountRepeat()));
        Wordname.setText(WordNames.get(RandPos).getWordName());
        CheckAnswer.setText("");
    }
    public void ChooseWord(){
        textCounter = (TextView)findViewById(R.id.CheckCountWord);
        Wordname = (TextView)findViewById(R.id.textQuestion);
        WordTranslateName = (TextView)findViewById(R.id.textAnswer);
        CheckAnswer = (TextView)findViewById(R.id.checkAnswer);
        AnswerText = (EditText)findViewById(R.id.AddAnswer);
        String answ =AnswerText.getText().toString();
        // check answer
        boolean Answer = WordListRecount.CheckRightAnswer(WordNames.get(RandPos),answ);
        WordTranslateName.setText(answ);
        textCounter.setText(Integer.toString(WordNames.get(RandPos).getWordCountRepeat()));
        Wordname.setText(WordNames.get(RandPos).getWordName());
        AnswerText.getText().clear();
        if (Answer){
            CheckAnswer.setText("Right");
            CheckAnswer.setTextColor(Color.parseColor("#06BC1E"));
            WordTranslateName.setTextColor(Color.parseColor("#06BC1E"));
        } else {
            CheckAnswer.setText("Wrong");
            CheckAnswer.setTextColor(Color.parseColor("#B30A21"));
            WordTranslateName.setTextColor(Color.parseColor("#B30A21"));
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NextWord();
            }

        },1000);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    private void NextWord() {
        RandPos = WordListRecount.getrandomword(WordNames,RandPos);
        WordTranslateName.setText("");
        textCounter.setText(Integer.toString(WordNames.get(RandPos).getWordCountRepeat()));
        Wordname.setText(WordNames.get(RandPos).getWordName());
        CheckAnswer.setText("");
    }
}
