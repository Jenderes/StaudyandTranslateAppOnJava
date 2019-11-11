package com.example.translatorapponjava;

public class WordNames {
    private int WordID;
    private String WordName;
    private String TranslateName;
    private int WordCountRepeat;

    public void setTranslateName(String translateName) {
        this.TranslateName = translateName;
    }
    public void setWordCountRepeat(int wordCountRepeat) {
        this.WordCountRepeat = wordCountRepeat;
    }
    public void setWordID(int wordID) {
        this.WordID = wordID;
    }
    public void setWordName(String wordName) {
        this.WordName = wordName;
    }

    public int getWordCountRepeat() {
        return WordCountRepeat;
    }
    public int getWordID() {
        return WordID;
    }
    public String getTranslateName() {
        return TranslateName;
    }
    public String getWordName() {
        return WordName;
    }

    public void ChangePlusCount(){
        this.WordCountRepeat = this.WordCountRepeat < 6 ? this.WordCountRepeat + 1 : this.WordCountRepeat;
    }
    public void ChangeMinusCount(){
        this.WordCountRepeat = this.WordCountRepeat >= 2 ? this.WordCountRepeat - 2 : 0;
    }
}
