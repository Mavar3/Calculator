package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String test_text = "398 + 519/83 - 8124";
        Calculator.printCalculate(test_text);
//        List<String> symbols = new ArrayList<String>();
//        symbols.add("+");
        var env = System.getenv();

        String inputSymbols = env.get("symbols");
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < inputSymbols.length(); i++) {
            symbols.add(Character.toString(inputSymbols.charAt(i)));
            //if (inputSymbols.charAt(i) == '+' || inputSymbols.charAt(i) == '*' || inputSymbols.charAt(i) == '^') {
            //    symbols.set(i, "\\" + symbols.get(i));
            //}
        }
        System.out.println(symbols.size());
    }


}
