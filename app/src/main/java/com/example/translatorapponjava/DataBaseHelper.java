package com.example.translatorapponjava;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "javaapp.db";
    private static String DB_PATH = "";
    private static int DB_VERSION = 2;
    private static String LIST_ID = "IDList";
    private static String LIST_NAME = "ListName";
    private static String LIST_COUNT = "CountWord";
    private static String LIST_SUM = "SumrRightAnswer";

    private SQLiteDatabase mDataBase;
    private final Context mContext;
    private boolean mNeedUpdate = false;

    public DataBaseHelper(Context context){
        super(context, DB_NAME,null,DB_VERSION);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        this.mContext = context;

        this.getReadableDatabase();
    }
    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DB_PATH + DB_NAME);
            if (dbFile.exists())
                dbFile.delete();
            copyDataBase();
            mNeedUpdate = false;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }
    private boolean checkDataBase() {
        File dbFile = new File(DB_PATH + DB_NAME);
        return dbFile.exists();
    }
    private void copyDataBase() {
        if(!checkDataBase()){
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }
    private void copyDBFile() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }
    public boolean openDataBase() throws SQLException {
        mDataBase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDataBase != null;
    }
    public synchronized void close(){
        if (mDataBase != null)
            mDataBase.close();
        super.close();
    }
    public ArrayList<ListNames> InputListTable(){
        ArrayList<ListNames> ArrayListNames = new ArrayList<ListNames>();
        try {
            this.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor query = db.rawQuery("SELECT n.IDList,n.ListName,"+
                " (Select Count(w.IDWord) from word_and_translate w, words_list_connector l, list_names t where w.IDWord = l.WordID and l.ListID = t.IDList and t.IDList = n.IDList) AS CountWord, "+
                "(Select sum(w.CountRepeats) from word_and_translate w, words_list_connector l, list_names t where w.IDWord = l.WordID and l.ListID = t.IDList and t.IDList = n.IDList) as SumrRightAnswer "+
                "From list_names n",null);
        if(query.moveToFirst()){
            do{
                ListNames listNames = new ListNames();
                listNames.setIDList(query.getInt(query.getInt(query.getColumnIndex(LIST_ID))));
                listNames.setListName(query.getString(query.getColumnIndex(LIST_NAME)));
                listNames.setCountWord(query.getInt(query.getColumnIndex(LIST_COUNT)));
                listNames.setSumWordsRightAnswer(query.getInt(query.getColumnIndex(LIST_SUM)));
                ArrayListNames.add(listNames);
            }
            while(query.moveToNext());
        }
        query.close();
        return ArrayListNames;
    }
}
