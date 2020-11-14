package com.company;

import com.company.Exceptions.DivisionException;
import com.company.Exceptions.EnviromentException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.*;

public class CalculatorWithList extends CalculatorFather {
    /**
     * Вывести на экран значение выражения из передваеммого текста с использованием алгоритма списка
     * @param text Передаваемое выражение
     */
    public void printCalculate(String text) {
        try {
            System.out.println(countList(parseText(expression.checkExpression(text))));
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
            return countList(parseText(expression.checkExpression(text)));
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
                    list = arithmeticList(list, division_position);
                    division_position = list.indexOf("/");
                } else if (division_position == -1) {
                    list = arithmeticList(list, multiply_position);
                    multiply_position = list.indexOf("*");
                } else {
                    if (multiply_position < division_position) {
                        list = arithmeticList(list, multiply_position);
                        multiply_position = list.indexOf("*");
                        division_position = list.indexOf("/"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    }
                    if (division_position < multiply_position) {
                        list = arithmeticList(list, division_position);
                        multiply_position = list.indexOf("*"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                        division_position = list.indexOf("/");
                    }
                }
            }
            int add_position = list.indexOf("+");
            int subtract_position = list.indexOf("-");
            while (add_position != -1 || subtract_position != -1) {
                if (add_position == -1) {
                    list = arithmeticList(list, subtract_position);
                    subtract_position = list.indexOf("-");
                } else if (subtract_position == -1) {
                    list = arithmeticList(list, add_position);
                    add_position = list.indexOf("+");
                } else {
                    if (add_position < subtract_position) {
                        list = arithmeticList(list, add_position);
                        add_position = list.indexOf("+");
                        subtract_position = list.indexOf("-"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    }
                    if (subtract_position < add_position) {
                        list = arithmeticList(list, subtract_position);
                        add_position = list.indexOf("+"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                        subtract_position = list.indexOf("-");
                    }
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
    private List<String> arithmeticList(List<String> list, int arifPos) throws DivisionException {
        double count = 0.00;
        switch (list.get(arifPos)) {
            case "*":
                count = Double.parseDouble(list.get(arifPos - 1)) * Double.parseDouble(list.get(arifPos + 1));
                break;
            case "/":
                if (Double.parseDouble(list.get(arifPos + 1)) == 0.0) {
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
    private List<String> parseText(String text) throws EnviromentException {
        List<String> symbols = new ArrayList<String>();
        try {
            symbols = environment.arithmetic("symbols");
        }
        catch (Exception ex) {
            throw new EnviromentException("Ошибка переменной окружения", ex);
        }
        // Преобразование
        for (String symbol: symbols) {
            String newSymbol = " " + symbol + " ";
            text = text.replace(symbol, newSymbol);
        }
        return new LinkedList<String>(Arrays.asList(text.split(" ").clone()));
    }
}
