package com.program;

import java.util.*;

public class Parser {

    protected String inStr;
    protected ArrayList<Symbol> symbSeq;
    protected ArrayList<Word> wordSeq;
    private Map<String, List<String>> dictionary;
    private HashSet<String> reservedWords;

    private void setDictionary() {

        dictionary = new HashMap<>();

        dictionary.put("digit", Arrays.asList("id", "number"));
        dictionary.put("word", Arrays.asList("id", "number"));
        dictionary.put("equal", Collections.singletonList("equal"));
        dictionary.put("semicolon", Collections.singletonList("semicolon"));
        dictionary.put("space", Collections.singletonList("space"));
        dictionary.put("underscore", Collections.singletonList("id"));
        dictionary.put("sign", Collections.singletonList("number"));
        dictionary.put("dot", Collections.singletonList("number"));
        dictionary.put("other", Collections.singletonList("other"));
    }

    public Parser(String inStr, ArrayList<String> reserved) {

        this.inStr = inStr;
        this.symbSeq = new ArrayList<>();
        this.wordSeq = new ArrayList<>();
        setDictionary();
        reservedWords = new HashSet<>();
        reservedWords.addAll(reserved);
    }

    public void simplifyStr() {

        inStr = inStr.toLowerCase();
        char prev_c = ' ';
        int eq_i = -1;
        for (int i = 0; i < inStr.length(); i++) {
            char c = inStr.charAt(i);
            if (c == '=') eq_i = i;

            if (c == ';' && prev_c == ' ' && i != 0)
                inStr = inStr.substring(0, i - 1) + inStr.substring(i);

            if (c == ' ' && prev_c == ' ') {
                inStr = inStr.substring(0, i) + inStr.substring(i + 1);
                i--;
            }
            prev_c = c;
        }
        if (eq_i > 0) {
            if (inStr.charAt(eq_i - 1) == ' ') {
                inStr = inStr.substring(0, eq_i - 1) + inStr.substring(eq_i);
                eq_i--;
            }
            if (eq_i != inStr.length() - 1 && inStr.charAt(eq_i + 1) == ' ')
                inStr = inStr.substring(0, eq_i + 1) + inStr.substring(eq_i + 2);
        }
        inStr += ' ';
    }

    public void parseSymbols() {

        for (int i = 0; i < inStr.length(); i++) {
            char c = inStr.charAt(i);
            symbSeq.add(new Symbol(c));
        }
    }

    public void parseWords() {

        String curWord = "";
        ArrayList<String[]> possibleWordTypes = new ArrayList<>();
        String symbolType;
        ArrayList<String> wordTypes = new ArrayList<>();

        for (int i = 0; i < symbSeq.size(); i++) {
            Symbol symbol = symbSeq.get(i);
            symbolType = symbol.getSymbolType();

            if (curWord.equals("")) {

                for (String wordType : dictionary.get(symbolType))
                    possibleWordTypes.add(new String[]{wordType, "-"});

                curWord += symbol.getSymbol();
                continue;
            }
            else for (String[] pair : possibleWordTypes) pair[1] = "-";

            for (String wordType : dictionary.get(symbolType)) {

                for (String[] pair : possibleWordTypes) {
                    if (wordType.equals(pair[0])) pair[1] = "+";
                }
            }

            wordTypes.clear();
            for (int j = 0; j < possibleWordTypes.size(); j++) {
                String[] pair = possibleWordTypes.get(j);
                if (pair[1].equals("-")) {
                    wordTypes.add(pair[0]);
                    possibleWordTypes.remove(pair);
                    j--;
                }
            }

            if (possibleWordTypes.size() > 0)
                curWord += symbol.getSymbol();
            else {
                wordSeq.add(new Word(curWord, wordTypes));
                curWord = "";
                possibleWordTypes.clear();
                i--;
            }
        }
    }

    public boolean parseSequence() {

        String sequence = "";
        String validSequence = "keyWordConst space validId equal validNumber semicolon ";

        for (Word word : wordSeq) {

            String type = word.getWordType();
            if (type.equals("validId") && reservedWords.contains(word.getWord()))
                if (word.getWord().equals("const")) sequence += "keyWordConst ";
                else sequence += "keyWord ";
            else sequence += type + " ";
        }

        System.out.println(sequence);//debug

        return sequence.equals(validSequence);
    }
}