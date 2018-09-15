package com.debug;

import com.program.Parser;
import com.program.Symbol;
import com.program.Word;

import java.util.ArrayList;

public class Parser_Debug extends Parser {

    public Parser_Debug(String inStr, ArrayList<String> reserved) {
        super(inStr, reserved);
    }

    public void setInStr(String inStr) {
        super.inStr = inStr;
    }

    public String getInStr() {
        return super.inStr;
    }

    public void setSymbSeq(ArrayList<Symbol> symbSeq) {
        super.symbSeq = symbSeq;
    }

    public ArrayList<Symbol> getSymbSeq() {
        return super.symbSeq;
    }

    public void setWordSeq(ArrayList<Word> wordSeq) {
        super.wordSeq = wordSeq;
    }

    public ArrayList<Word> getWordSeq() {
        return super.wordSeq;
    }
}
