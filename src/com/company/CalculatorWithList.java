package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CalculatorWithList extends CalculatorFather {
    /**
     * Вывести на экран значение выражения из передваеммого текста с использованием алгоритма списка
     * @param text Передаваемое выражение
     */
    public void printCalculate(String text) {
        try {
            System.out.println(countList(parseText(checkText(text))));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Вернуть значение выражения из передваеммого текста с использованием алгоритма списка
     * @param text Передаваемое выражение
     * @return double или null при ошибке
     */
    public Double calculate(String text) {
        try {
            return countList(parseText(checkText(text)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Метод подсчёта
     * @param list выражение в виде списка
     * @return значение подсчёта
     */
    private double countList(List<String> list) throws DivisionException {
        try {
            int multiply_position = list.indexOf("*");
            int division_position = list.indexOf("/");
            while (multiply_position != -1 || division_position != -1) {
                if (multiply_position == -1) {
                    list = arifmeticList(list, division_position);
                    division_position = list.indexOf("/");
                } else if (division_position == -1) {
                    list = arifmeticList(list, multiply_position);
                    multiply_position = list.indexOf("*");
                } else {
                    if (multiply_position < division_position) {
                        list = arifmeticList(list, multiply_position);
                        multiply_position = list.indexOf("*");
                        division_position = list.indexOf("/"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    }
                    if (division_position < multiply_position) {
                        list = arifmeticList(list, division_position);
                        multiply_position = list.indexOf("*"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                        division_position = list.indexOf("/");
                    }
                }
            }
            int add_position = list.indexOf("+");
            int subtract_position = list.indexOf("-");
            while (add_position != -1 || subtract_position != -1 && subtract_position != 0) {
                if (add_position == -1) {
                    list = arifmeticList(list, subtract_position);
                    subtract_position = list.indexOf("-");
                } else if (subtract_position == -1) {
                    list = arifmeticList(list, add_position);
                    add_position = list.indexOf("+");
                } else {
                    list = arifmeticList(list, add_position);
                    add_position = list.indexOf("+");
                    subtract_position = list.indexOf("-"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    list = arifmeticList(list, subtract_position);
                    add_position = list.indexOf("+"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    subtract_position = list.indexOf("-");
                }
            }
            return Double.parseDouble(list.get(0));
        }
        catch (DivisionException ex) {
            throw new DivisionException("Ошибка деления!",ex);
        }
    }

    /**
     * Заменяет два числа и арифметический знак, на значение после арифметического действия
     * @param list Уравнение в виде списка
     * @param arifPos Позиция знака
     * @return Новый список. арифметический символ, числа до и после - выкинуту и записан результат действия.
     * В случае ошибки выдаётся null.
     */
    private List<String> arifmeticList(List<String> list, int arifPos) throws DivisionException {
        double count = 0.00;
        switch (list.get(arifPos)) {
            case "*":
                count = Double.parseDouble(list.get(arifPos - 1)) * Double.parseDouble(list.get(arifPos + 1));
                break;
            case "/":
                if (list.get(arifPos + 1) == "0") {
                    throw new DivisionException("На ноль делить нельзя!");
                }
                count = Double.parseDouble(list.get(arifPos - 1)) / Double.parseDouble(list.get(arifPos + 1));
                break;
            case "+":
                count = Double.parseDouble(list.get(arifPos - 1)) + Double.parseDouble(list.get(arifPos + 1));
                break;
            case "-":
                count = Double.parseDouble(list.get(arifPos - 1)) - Double.parseDouble(list.get(arifPos + 1));
                break;
        }
        list.set(arifPos, String.valueOf(count));
        list.remove(arifPos + 1);
        list.remove(arifPos - 1);
        return list;
    }

    /**
     * Разбивает текст на числа и операции, записывает в список как текст
     * @param text текст для разбивания
     * @return Список чисел и операций
     */
    private List<String> parseText(String text) throws EnviromentException{
        List<String> symbols = new ArrayList<String>();
        try {
            symbols = enviromentArifmetic("symbols");
        }
        catch (Exception ex) {
            throw new EnviromentException("Ошибка переменной окружения", ex);
        }
        // Преобразование
        for (String symbol: symbols) {
            String newSimbol = " " + symbol + " ";
            text = text.replace(symbol, newSimbol);
        }
        return new LinkedList<String>(Arrays.asList(text.split(" ").clone()));
    }

    private List<String> enviromentArifmetic(String envName) throws EnviromentException {
        // Считывание переменных из переменных окружения (усложнение кода! В данном случе - этот метод бесполезен)
        var env = System.getenv();
        String inputSymbols = env.get(envName);
        if (inputSymbols == null) {
            throw new EnviromentException("Переменная окружения не определена!");
        }
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < inputSymbols.length(); i++) {
            if (inputSymbols.charAt(i) != '+' && inputSymbols.charAt(i) != '-' && inputSymbols.charAt(i) != '*'
                    && inputSymbols.charAt(i) != '/') {
                throw new EnviromentException("В переменных окружения есть не подходящий символ!");
            }
            symbols.add(Character.toString(inputSymbols.charAt(i)));
        }
        if (symbols.isEmpty()) {
            throw new EnviromentException("Нет символов!");
        }
        return symbols;
    }
}
