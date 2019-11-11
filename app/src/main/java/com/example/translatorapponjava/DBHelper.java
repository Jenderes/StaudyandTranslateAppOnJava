package com.example.translatorapponjava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "javaapp.db";
    private static int DB_VERSION = 2;
    private static String LIST_TABLE_NAME = "list_names";
    private static String LIST_ID = "IDList";
    private static String LIST_NAME = "ListName";
    private static String LIST_COUNT = "CountWord";
    private static String LIST_SUM = "SumrRightAnswer";
    private static String CONNNECTR_TABLE_NAME = "words_list_connector";
    private static String ID_LIST ="ListID";
    private static String ID_WORD = "WordID";
    private static String TRANSLATE_TABLE_NAME = "word_and_translate";
    private static String WORD_ID = "IDWord";
    private static String WORD_NAME = "Word";
    private static String TRANSLATE_NAME = "Translate";
    private static String COUNT_REPEATS = "CountRepeats";

    private static final String CREATING_LIST_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)",LIST_TABLE_NAME,LIST_ID,LIST_NAME);
    private static final String TRANSLATE_WORD_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL ,%s INTEGER NOT NULL)",TRANSLATE_TABLE_NAME,WORD_ID,WORD_NAME,TRANSLATE_NAME,COUNT_REPEATS);
    private static final String CONNECTOR_TABLE = String.format("CREATE TABLE %s (%s INTEGER NOT NULL, %s INTEGER NOT NULL, FOREIGN KEY(%s) REFERENCES  list_names(%s),FOREIGN KEY(%s) REFERENCES word_and_translate(%s))",CONNNECTR_TABLE_NAME,ID_LIST,ID_WORD,ID_LIST,LIST_ID,ID_WORD,WORD_ID);
    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATING_LIST_TABLE);
        db.execSQL(TRANSLATE_WORD_TABLE);
        db.execSQL(CONNECTOR_TABLE);
        Log.d("table",CREATING_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP TABLE IF EXISTS '%s'",LIST_TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF EXISTS '%s'",CONNNECTR_TABLE_NAME));
        db.execSQL(String.format("DROP TABLE IF EXISTS '%s'",TRANSLATE_TABLE_NAME));
        onCreate(db);
    }
    public void addWord(String Word_name, String Translate_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WORD_NAME, Word_name);
        values.put(TRANSLATE_NAME, Translate_name);
        values.put(COUNT_REPEATS, 0);
        db.insert(TRANSLATE_TABLE_NAME, null, values);
    }

    public ArrayList<ListNames> InputListTable(){
        int SumCheck = 0;
        ArrayList<ListNames> ArrayListNames = new ArrayList<ListNames>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT n.IDList,n.ListName,"+
                " (Select Count(w.IDWord) from word_and_translate w, words_list_connector l, list_names t where w.IDWord = l.WordID and l.ListID = t.IDList and t.IDList = n.IDList) AS CountWord, "+
                "(Select sum(w.CountRepeats) from word_and_translate w, words_list_connector l, list_names t where w.IDWord = l.WordID and l.ListID = t.IDList and t.IDList = n.IDList) as SumrRightAnswer "+
                "From list_names n",null);
        if(query.moveToFirst()){
            do{
                ListNames listNames = new ListNames();
                listNames.setIDList(query.getInt(query.getColumnIndex(LIST_ID)));
                listNames.setListName(query.getString(query.getColumnIndex(LIST_NAME)));
                listNames.setCountWord(query.getInt(query.getColumnIndex(LIST_COUNT)));
                listNames.setSumWordsRightAnswer(query.getInt(query.getColumnIndex(LIST_SUM)));
                ArrayListNames.add(listNames);
            }
            while(query.moveToNext());
        }
        query.close();
        db.close();
        return ArrayListNames;
    }
    public ArrayList<WordNames> InpuntWords(String idlist){
        ArrayList<WordNames> ArrayAllWordsList = new ArrayList<WordNames>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        Cursor query = sqldb.rawQuery(String.format("SELECT w.IDWord,Word,Translate,CountRepeats FROM word_and_translate w, words_list_connector l, list_names t where w.IDWord = l.WordID and l.ListID = t.IDList and IDList = %s and w.CountRepeats < 6",Integer.parseInt(idlist)),null);
        if (query.moveToFirst()){
            do {
                WordNames wordnames = new WordNames();
                wordnames.setWordID(query.getInt(query.getColumnIndex(WORD_ID)));
                wordnames.setWordName(query.getString(query.getColumnIndex(WORD_NAME)));
                wordnames.setTranslateName(query.getString(query.getColumnIndex(TRANSLATE_NAME)));
                wordnames.setWordCountRepeat(query.getInt(query.getColumnIndex(COUNT_REPEATS)));
                ArrayAllWordsList.add(wordnames);
            } while (query.moveToNext());
        }
        query.close();
        sqldb.close();
        return  ArrayAllWordsList;
    }
    public void UpdateWordCount(ArrayList<WordNames> arw){
        SQLiteDatabase dbsql = this.getWritableDatabase();
        for (int i = 0; i < arw.size(); i++){
           ContentValues cv = new ContentValues();
           cv.put(COUNT_REPEATS,arw.get(i).getWordCountRepeat());
           int UpdateCount = dbsql.update(TRANSLATE_TABLE_NAME,cv,"id = ?",new String[]{Integer.toString(arw.get(i).getWordID())});
        }
    }
}
