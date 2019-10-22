package com.example.translatorapponjava;

public class ListNames {
    private int IDList;
    private String ListName;
    private int CountWord;
    private int SumWordsRightAnswer;
    private int sum;
    public int getIDList() {
        return IDList;
    }
    public int getCountWord() {
        return CountWord;
    }
    public int getSumWordsRightAnswer() {
        return SumWordsRightAnswer;
    }
    public String getListName() {
        return ListName;
    }
    public void setCountWord(int countWord) {
        this.CountWord = countWord;
    }
    public void setIDList(int IDLists) {
        IDList = IDLists;
    }
    public void setListName(String listName) {
        this.ListName = listName;
    }
    public void setSumWordsRightAnswer(int sumWordsRightAnswer) {
        this.SumWordsRightAnswer = sumWordsRightAnswer;
    }

    public int getPercent(){
        return CountWord != 0 ? Math.round((SumWordsRightAnswer/(CountWord * 5))*100) : 0;
    }
}
