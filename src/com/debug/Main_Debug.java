package com.debug;

import com.program.Symbol;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main_Debug {

    public static void main(String[] args) throws Exception {
        
        System.out.println("Тестирование методов getSymbol и getSymbolType из класса Symbol");
        char[] symbols = new char[]{'.', '_', ';', '"', 'a', 'ь', 'z', ' ', '9', '=', '+', '-', '%'};
        Symbol_Debug symbol = new Symbol_Debug('a');
        for (char c : symbols) {
            symbol.setSymbol(c);
            System.out.println(symbol.getSymbol() + " " + symbol.getSymbolType());
        }

        System.out.println("Тестирование методов getWord и getWordType из класса Word");
        String[] words = new String[]{"const", ";", " ", "_name", "5lolek", "3.33",
                "-3e+15", "3ee15", "3..14", "name56"};
        String[][] wordTypes = new String[][]{{"id"}, {"semicolon"}, {"space"}, {"id"},
                {"id", "number"}, {"number"}, {"number"}, {"id", "number"}, {"number"}, {"id", "number"}};
        ArrayList<String> types = new ArrayList<>();
        types.add("id");
        Word_Debug testWord = new Word_Debug("init", types);
        for (int i = 0; i < 10; i++) {
            types.clear();
            for (String type : wordTypes[i]) {
                types.add(type);
            }
            testWord.setWord(words[i], types);
            System.out.println(testWord.getWord() + " " + testWord.getWordType());
        }

        ArrayList<String> reserved = new ArrayList<>();
        FileReader fres = new FileReader("src/com/program/txt/reserved.txt");
        Scanner scanRes = new Scanner(fres);
        while(scanRes.hasNext()) {
            reserved.add(scanRes.nextLine());
        }

        Parser_Debug parse = new Parser_Debug("init", reserved);

        System.out.println("Тестирование метода simplifyStr из класса Parser");
        String[] testInStrs = new String[]{"   const n = 3.33;", "CONST N = 3E+12;",
                "co nst n= 3.14  ;", "=", "con s t n = 3 e + 314 ;"};
        for (String inStr : testInStrs) {
            parse.setInStr(inStr);
            parse.simplifyStr();
            System.out.println(parse.getInStr());
        }

        System.out.println("Тестирование метода parseSymbols из класса Parser");
        for (String inStr : testInStrs) {
            parse.setInStr(inStr);
            parse.parseSymbols();
            ArrayList<Symbol> res1 = new ArrayList<>(parse.getSymbSeq());
            for (Symbol s1 : res1) {
                System.out.println(s1.getSymbol() + " " + s1.getSymbolType());
            }
        }

    }
}
