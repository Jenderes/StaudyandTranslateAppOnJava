package com.example.translatorapponjava;

import java.util.ArrayList;
import java.util.Random;
//Todo: Check CountRepeats not more then 3
public class WordListRecount {
    public static int getfirstrandomword(ArrayList<WordNames> arl) {
        Random rnd = new Random();
        int randomvalue = rnd.nextInt(arl.size()-1)+1;
        return randomvalue;
    }
    public static boolean CheckRightAnswer(WordNames wn,String Answer){
        if (wn.getTranslateName().toLowerCase().equals(Answer.toLowerCase())){
            wn.ChangePlusCount();
            return  true;
        } else {
            wn.ChangeMinusCount();
            return false;
        }
    }
    public static int getrandomword(ArrayList<WordNames> arl,int rand){
        Random rnd = new Random();
        int randomvalue;
        do {
            randomvalue = rnd.nextInt(arl.size()-1)+1;
        }
        while(randomvalue == rand);
        return randomvalue;
    }
}
