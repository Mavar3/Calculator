package com.company;

import com.company.Exceptions.CommandArgsException;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length > 1) {
                throw new CommandArgsException("Слишком много параметров командной строки!\n" +
                        "Введите выражение в \"<выражение>\" кавычках");
            }
            if (args.length < 1) {
                throw new CommandArgsException("Слишком мало параметров командной строки!\n" +
                        "Введите выражение в \"<выражение>\" кавычках");
            }
        }
        catch (CommandArgsException ex) {
            ex.printStackTrace();
        }
        String test_text = args[0];
//        var calculWithText = new CalculatorWithText();
        var calculWithList = new CalculatorWithList();
//        calculWithText.printCalculate(test_text);
        calculWithList.printCalculate(test_text);
//        Double resultWithText = calculWithText.calculate(test_text);
        Double resultWithList = calculWithList.calculate(test_text);
//        System.out.println("Посчитано через текст: " + args[0] + " = " + resultWithText);
        System.out.println("Посчитано через список: " + args[0] + " = " + resultWithList);

    }


}
