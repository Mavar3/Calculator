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
        Calculator.printCalculateList(test_text);
    }


}
