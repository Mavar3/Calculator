package com.company;

import jdk.jfr.StackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        try {
            if (args.length - 1 > 0) {
                throw new CommandArgsExeption("Слишком много параметров командной строки!\n" +
                        "Введите выражение в \"<выражение>\" кавычках");
            }
            if (args.length - 1 < 0) {
                throw new CommandArgsExeption("Слишком мало параметров командной строки!\n" +
                        "Введите выражение в \"<выражение>\" кавычках");
            }
        }
        catch (CommandArgsExeption ex) {
            ex.printStackTrace();
        }
        String test_text = args[0];
        Calculator.printCalculate(test_text);
        Calculator.printCalculateList(test_text);


    }


}
