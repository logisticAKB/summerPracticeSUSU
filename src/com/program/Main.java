package com.program;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        FileReader fin = new FileReader("src/com/program/txt/input.txt");
        Scanner scan = new Scanner(fin);
        String inStr = scan.nextLine();

        ArrayList<String> reserved = new ArrayList<>();
        FileReader fres = new FileReader("src/com/program/txt/reserved.txt");
        Scanner scanRes = new Scanner(fres);
        while(scanRes.hasNext()) {
            reserved.add(scanRes.nextLine());
        }

        Parser parse = new Parser(inStr, reserved);

        parse.simplifyStr();

        parse.parseSymbols();
        parse.parseWords();

        FileWriter fout = new FileWriter("src/com/program/txt/output.txt");

        if (parse.parseSequence()) fout.write("ACCEPT");
        else fout.write("REJECT");

        fin.close();
        fres.close();
        fout.close();
    }
}
