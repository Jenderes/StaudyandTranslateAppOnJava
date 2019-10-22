package com.example.translatorapponjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {
    TextView textCounter;
    TextView textListName;
    TextView Wordname;
    TextView WordTranslateName;
    TextView CheckAnswer;
    Button buttAnswer;
    EditText AnswerText;
    DBHelper dbw;
    ArrayList<WordNames> WordNames = new ArrayList<WordNames>();
    String listid;
    int RandPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        textListName = (TextView)findViewById(R.id.NameList);
        buttAnswer = (Button)findViewById(R.id.buttonAnswer);
        Bundle arg = getIntent().getExtras();
        listid =arg.get("IDList").toString();
        dbw = new DBHelper(this);
        WordNames = dbw.InpuntWords(listid);
        textListName.setText(listid);
        firstChooseWord();
            buttAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseWord();
            }
        });
    }
    public void firstChooseWord(){
        textCounter = (TextView)findViewById(R.id.CheckCountWord);
        Wordname = (TextView)findViewById(R.id.textQuestion);
        CheckAnswer = (TextView)findViewById(R.id.checkAnswer);
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
        boolean Answer = WordListRecount.CheckRightAnswer(WordNames.get(RandPos),answ);
        WordTranslateName.setText(answ);
        textCounter.setText(Integer.toString(WordNames.get(RandPos).getWordCountRepeat()));
        Wordname.setText(WordNames.get(RandPos).getWordName());
        if (Answer){
            CheckAnswer.setText("Right");
        } else {
            CheckAnswer.setText("Wrong");
        }
        RandPos = WordListRecount.getrandomword(WordNames,RandPos);
        WordTranslateName.setText("");
        textCounter.setText(Integer.toString(WordNames.get(RandPos).getWordCountRepeat()));
        Wordname.setText(WordNames.get(RandPos).getWordName());
        CheckAnswer.setText("");
    }
}
