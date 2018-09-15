package com.debug;

import com.program.Word;

import java.util.ArrayList;

public class Word_Debug extends Word {

    public Word_Debug(String word, ArrayList<String> wordTypes) {
        super(word, wordTypes);
    }

    public void setWord(String word, ArrayList<String> wordTypes) {
        super.word = word;
        super.wordTypes = wordTypes;
        super.recognizeWord();
    }
}
