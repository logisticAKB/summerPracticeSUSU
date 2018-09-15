package com.program;

public class Symbol {

    protected char symbol;
    private String symbolType;

    protected void recognizeSymbol() {
        if (symbol >= '0' && symbol <= '9') symbolType = "digit";
        else if (symbol >= 'a' && symbol <= 'z') symbolType = "word";
        else if (symbol == '=') symbolType = "equal";
        else if (symbol == ';') symbolType = "semicolon";
        else if (symbol == ' ') symbolType = "space";
        else if (symbol == '_') symbolType = "underscore";
        else if (symbol == '+' || symbol == '-') symbolType = "sign";
        else if (symbol == '.') symbolType = "dot";
        else symbolType = "other";
    }
    
    public Symbol(char symbol) {
        this.symbol = symbol;
        recognizeSymbol();
    }

    public char getSymbol() {
        return symbol;
    }

    public String getSymbolType() {
        return symbolType;
    }
}
