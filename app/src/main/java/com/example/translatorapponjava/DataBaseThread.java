package com.example.translatorapponjava;

import android.os.AsyncTask;

import java.util.ArrayList;

public class DataBaseThread extends AsyncTask<ArrayList<WordNames>,Void,Void> {
    private ArrayList<WordNames> ArrayNameList;
    public DataBaseThread(ArrayList<WordNames> ArrayNameList){
        this.ArrayNameList = new ArrayList<>(ArrayNameList);
    }

    @Override
    protected Void doInBackground(ArrayList<WordNames>... arrayLists) {
//        DBHelper dbh = new DBHelper(StudyActivity.);
//        ArrayList<WordNames> wordname = arrayLists[0];
//        DBHelper.UpdateWordCount(wordname);
        return null;
    }
}
