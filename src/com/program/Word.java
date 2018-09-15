package com.program;

import java.util.ArrayList;

public class Word {

    protected String word;
    protected String wordType;
    protected ArrayList<String> wordTypes;

    public Word(String word, ArrayList<String> wordTypes) {
        this.word = word;
        this.wordTypes = wordTypes;
        recognizeWord();
    }

    protected void recognizeWord() {

       String[] typeLevel = new String[4];

        for (String wordT : wordTypes) {

            if (!wordT.equals("id") && !wordT.equals("number")) {
                if (wordT.equals("equal") || wordT.equals("semicolon")) {
                    if (word.length() == 1) wordType = wordTypes.get(0);
                    else wordType = "invalidCharSeq";
                }
                else wordType = wordTypes.get(0);
                return;
            }

            if (wordT.equals("id")) {
                if (isValidId()) typeLevel[0] = "validId";
                else typeLevel[2] = "invalidId";
            }
            else {
                  if (isValidNumber()) typeLevel[1] = "validNumber";
                  else typeLevel[3] = "invalidNumber";
            }
        }

        for (String type : typeLevel) {
            if (type != null) {
                wordType = type;
                break;
            }
        }
    }

    protected boolean isValidId() {

        boolean validFirstSymb =
                (word.charAt(0) == '_' || (word.charAt(0) >= 'a' && word.charAt(0) <= 'z'));
        boolean validSymbols = true;

        for (int i = 1; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c != '_' && (c - '0' < 0 || c - '0' > 9) && (c < 'a' || c > 'z'))
                validSymbols = false;
        }

        return validFirstSymb && validSymbols;
    }

    protected boolean isValidNumber() {

        char[] signUnit = new char[]{' ', ' '};
        char dot_e_unit = ' ';
        String intUnit = "";
        String fracUnit = "";

        String state = "sign_unit";

        for (int i = 0; i != word.length(); i++) {

            switch (state) {
                case "sign_unit":

                    if (dot_e_unit == ' ') {

                        if (word.charAt(i) >= '0' && word.charAt(i) <= '9') {
                            signUnit[0] = '+';
                            i--;
                        }
                        else signUnit[0] = word.charAt(i);
                        state = "int_unit";
                    }
                    else {
                        signUnit[1] = word.charAt(i);
                        state = "frac_unit";
                    }
                    break;

                case "int_unit":

                    if (word.charAt(i) != '.' && word.charAt(i) != 'e')
                        intUnit += word.charAt(i);
                    else if (word.charAt(i) == '.') {
                        state = "frac_unit";
                        dot_e_unit = '.';
                    } else {
                        state = "sign_unit";
                        dot_e_unit = 'e';
                    }
                    break;

                case "frac_unit":

                    fracUnit += word.charAt(i);
                    break;
            }
        }

        boolean validFirstSign = signUnit[0] == '+' || signUnit[0] == '-';

        boolean validSecondSign =
                dot_e_unit == ' '
                        || ((dot_e_unit == '.' && signUnit[1] == ' ')
                        || (dot_e_unit == 'e' && (signUnit[1] == '+' || signUnit[1] == '-')));

        boolean validInt = true;
        boolean validFrac = true;

        for (int i = 0; i < intUnit.length(); i++) {
            char c = intUnit.charAt(i);
            if (c < '0' || c > '9') validInt = false;
        }

        for (int i = 0; i < fracUnit.length(); i++) {
            char c = fracUnit.charAt(i);
            if (c < '0' || c > '9') validFrac = false;
        }

        return validFirstSign && validSecondSign && validInt && validFrac;
    }

    public String getWordType() {
        return wordType;
    }

    public String getWord() {
        return word;
    }
}
