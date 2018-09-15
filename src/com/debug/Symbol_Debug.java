package com.debug;

import com.program.Symbol;

public class Symbol_Debug extends Symbol {

    public Symbol_Debug(char symbol) {
        super(symbol);
    }

    public void setSymbol(char symbol) {
        super.symbol = symbol;
        super.recognizeSymbol();
    }
}
