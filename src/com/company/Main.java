package com.company;

public class Main {
    public static void main(String[] args) {
        //Не забыть сделать это через ввод параметра консольной строки
//        System.out.println(args[0]);
        String test_text = "5+10*25.02 -   70 / 35 * 2 + 30 - 20*2";
        Calculator.printCalculate(test_text);
//        Double test = Calculator.calculate(test_text);
//        System.out.printf("Значение полученное мной из переменной: " + test);
    }
}
