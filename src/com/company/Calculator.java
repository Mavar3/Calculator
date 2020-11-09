package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс, предназначенный для расчёта текстового выражения
 */
public class Calculator {
    /**
     * Вывести на экран значение выражения из передваеммого текста
     * @param text Передаваемое выражение
     */
    public static void printCalculate(String text) {
        try {
            System.out.println(count(checkText(text)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Вернуть значение выражения из передваеммого текста
     * @param text Передаваемое выражение
     * @return double или null при ошибке
     */
    public static Double calculate(String text) {
        try {
            return count(checkText(text));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void printCalculateList(String text) {
        try {
            System.out.println(countList(parseText(checkText(text))));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Double calculateList(String text) {
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
     * @param text отформатированное выражение
     * @return значение подсчёта
     */
    private static double count(String text) throws DivisionExeption {
        try {
            int multiply_position = text.indexOf('*');
            int division_position = text.indexOf('/');
            int add_position = text.indexOf('+');
            int subtract_position = text.indexOf('-');
            while (multiply_position != -1 || division_position != -1) {
                if (multiply_position == -1) {
                    text = arifmeticText(text, division_position);
                    division_position = text.indexOf('/');
                } else if (division_position == -1) {
                    text = arifmeticText(text, multiply_position);
                    multiply_position = text.indexOf('*');
                } else {
                    if (multiply_position < division_position) {
                        text = arifmeticText(text, multiply_position);
                        multiply_position = text.indexOf('*');
                        division_position = text.indexOf('/'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    }
                    if (division_position < multiply_position) {
                        text = arifmeticText(text, division_position);
                        multiply_position = text.indexOf('*'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                        division_position = text.indexOf('/');
                    }
                }
            }
            while (add_position != -1 || subtract_position != -1 && subtract_position != 0) {
                if (add_position == -1) {
                    text = arifmeticText(text, subtract_position);
                    subtract_position = text.indexOf('-');
                } else if (subtract_position == -1) {
                    text = arifmeticText(text, add_position);
                    add_position = text.indexOf('+');
                } else {
                    text = arifmeticText(text, add_position);
                    add_position = text.indexOf('+');
                    subtract_position = text.indexOf('-'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    text = arifmeticText(text, subtract_position);
                    add_position = text.indexOf('+'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    subtract_position = text.indexOf('-');
                }
            }
            return Double.parseDouble(text);
        }
        catch (DivisionExeption ex) {
            throw new DivisionExeption("Ошибка деления!",ex);
        }
    }

    /**
     * Метод подсчёта
     * @param text выражение в виде списка
     * @return значение подсчёта
     */
    private static double countList(List<String> text) throws DivisionExeption {
        try {
            int multiply_position = text.indexOf("*");
            int division_position = text.indexOf("/");
            int add_position = text.indexOf("+");
            int subtract_position = text.indexOf("-");
            while (multiply_position != -1 || division_position != -1) {
                if (multiply_position == -1) {
                    text = arifmeticList(text, division_position);
                    division_position = text.indexOf("/");
                } else if (division_position == -1) {
                    text = arifmeticList(text, multiply_position);
                    multiply_position = text.indexOf("*");
                } else {
                    if (multiply_position < division_position) {
                        text = arifmeticList(text, multiply_position);
                        multiply_position = text.indexOf("*");
                        division_position = text.indexOf("/"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    }
                    if (division_position < multiply_position) {
                        text = arifmeticList(text, division_position);
                        multiply_position = text.indexOf("*"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                        division_position = text.indexOf("/");
                    }
                }
            }
            while (add_position != -1 || subtract_position != -1 && subtract_position != 0) {
                if (add_position == -1) {
                    text = arifmeticList(text, subtract_position);
                    subtract_position = text.indexOf("-");
                } else if (subtract_position == -1) {
                    text = arifmeticList(text, add_position);
                    add_position = text.indexOf("+");
                } else {
                    text = arifmeticList(text, add_position);
                    add_position = text.indexOf("+");
                    subtract_position = text.indexOf("-"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    text = arifmeticList(text, subtract_position);
                    add_position = text.indexOf("+"); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
                    subtract_position = text.indexOf("-");
                }
            }
            return Double.parseDouble(text.get(0));
        }
        catch (DivisionExeption ex) {
            throw new DivisionExeption("Ошибка деления!",ex);
        }
    }


    /**
     * Заменяет два числа и арифметический знак, на значение после арифметического действия
     * @param text Уравнение в виде списка
     * @param arifPos Позиция знака
     * @return Новый список. арифметический символ, числа до и после - выкинуту и записан результат действия.
     * В случае ошибки выдаётся null.
     */
    private static List<String> arifmeticList(List<String> text, int arifPos) throws DivisionExeption {
        double count = 0.00;
        switch (text.get(arifPos)) {
            case "*":
                count = Double.parseDouble(text.get(arifPos - 1)) * Double.parseDouble(text.get(arifPos + 1));
                break;
            case "/":
                if (text.get(arifPos + 1) == "0") {
                    throw new DivisionExeption("На ноль делить нельзя!");
                }
                count = Double.parseDouble(text.get(arifPos - 1)) / Double.parseDouble(text.get(arifPos + 1));
                break;
            case "+":
                count = Double.parseDouble(text.get(arifPos - 1)) + Double.parseDouble(text.get(arifPos + 1));
                break;
            case "-":
                count = Double.parseDouble(text.get(arifPos - 1)) - Double.parseDouble(text.get(arifPos + 1));
                break;
        }
        int startOfNum = arifPos - 1;
        int endOfNum = arifPos + 1;
        text.set(arifPos, String.valueOf(count));
        text.remove(endOfNum);
        text.remove(startOfNum);
        return text;
    }

    /**
     * Заменяет два числа и арифметический знак, на значение после арифметического действия
     * @param text Уравнение в виде текста
     * @param arifPos Позиция знака
     * @return Новый текст. арифметический символ, числа до и после - выкинуту и записан результат действия.
     * В случае ошибки выдаётся null.
     */
    private static String arifmeticText(String text, int arifPos) throws DivisionExeption {
        double count = 0.00;
        switch (text.charAt(arifPos)) {
            case '*':
                count = previousNum(text, arifPos) * nextNum(text, arifPos);
                break;
            case '/':
                if (nextNum(text, arifPos) == 0) {
                    throw new DivisionExeption("На ноль делить нельзя!");
                }
                count = previousNum(text, arifPos) / nextNum(text, arifPos);
                break;
            case '+':
                count = previousNum(text, arifPos) + nextNum(text, arifPos);
                break;
            case '-':
                count = previousNum(text, arifPos) - nextNum(text, arifPos);
                break;
        }
        int startOfNum = 0;
        for (int i = arifPos - 1; i >= 0; i--) {
            switch (text.charAt(i)) {
                case '-': case '+': case '/': case '*':
                    startOfNum = i + 1;
                    break;
            }
            if (startOfNum != 0) {
                break;
            }
        }
        int endOfNum = text.length() - 1;
        for (int i = arifPos + 1; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '-': case '+': case '/': case '*':
                    endOfNum = i;
                    break;
            }
            if (endOfNum != text.length() - 1) {
                break;
            }
        }
        if (endOfNum == text.length() - 1) {
            return text.substring(0, startOfNum) + String.valueOf(count);
        }
        return text.substring(0, startOfNum) + String.valueOf(count) + text.substring(endOfNum);
    }

    /**
     * Предыдущее число
     * @param text выражение в виде текста
     * @param position позиция символа от которого считать
     * @return Предыдущее число типа Double
     */
    private static double previousNum(String text, int position) {
        int startOfNum = 0;
        boolean isNegativeNum = false;
        for (int i = position - 1; i >= 0; i--) {
            switch (text.charAt(i)) {
                case '-':
//                    isNegativeNum = true;
                case '+': case '/': case '*':
                    startOfNum = i + 1;
                    break;
            }
            if (startOfNum != 0) {
                break;
            }
        }
        Double textInDouble = Double.parseDouble((text.substring(startOfNum, position)));
//        textInDouble = isNegativeNum ? -textInDouble : textInDouble;
        return textInDouble;
    }

    /**
     * Следующее число
     * @param text выражение в виде текста
     * @param position позиция символа от которого считать
     * @return Следующее число типа Double
     */
    private static double nextNum(String text, int position) {
        int endOfNum = text.length() - 1;
        for (int i = position + 1; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '-': case '+': case '/': case '*':
                    endOfNum = i;
                    break;
            }
            if (endOfNum != text.length() - 1) {
                break;
            }
        }
        String textForConvertDouble = text.length() - 1 == endOfNum ? text.substring(position + 1)
                : text.substring(position + 1, endOfNum);
        Double textInDouble = Double.parseDouble(textForConvertDouble);
        return textInDouble;
    }

    /**
     * Разбивает текст на числа и операции, записывает в список как текст
     * @param text текст для разбивания
     * @return Список чисел и операций
     */
    private static List<String> parseText(String text) {
        // Считывание переменных из переменных окружения (Выделить в отдельный метод)
        var env = System.getenv();
        String inputSymbols = env.get("symbols");
        List<String> symbols = new ArrayList<>();
        for (int i = 0; i < inputSymbols.length(); i++) {
            symbols.add(Character.toString(inputSymbols.charAt(i)));
        }
        // Преобразование
        for (String symbol: symbols) {
            String newSimbol = " " + symbol + " ";
            text = text.replace(symbol, newSimbol);
        }
        return new LinkedList<String>(Arrays.asList(text.split(" ").clone()));
    }

    /**
     * Проверяет текст на допустимые символы
     * @param text исходный текст
     * @return  возвращает текст без пробелов между символами или ошибку
     */
    private static String checkText(String text) throws ExpressionExeption {
        boolean isPreviousNumber = false;
        boolean isPreviousArifmetic = false;
        boolean isPreviousSpace = false;
        boolean isItFirstArifmetic = true;
        boolean isPointExist = false;
        String formatText = "";
        for(int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9':
                    if(isPreviousNumber && isPreviousSpace){
                        throw new ExpressionExeption("Пробелы между цифрами не допустимы!");
                    }
                    isPreviousNumber = true;
                    isPreviousArifmetic = false;
                    isPreviousSpace = false;
                    formatText += text.charAt(i);
                    break;
                case '+': case '-': case '*': case '/':
                    if(isPreviousArifmetic || (isItFirstArifmetic && !isPreviousNumber)) {
                        throw new ExpressionExeption("Невозможно распознать арифметические знаки.");
                    }
                    isPreviousArifmetic = true;
                    isPreviousNumber = false;
                    isPreviousSpace = false;
                    isItFirstArifmetic = false;
                    isPointExist = false;
                    formatText += text.charAt(i);
                    break;
                case ' ':
                    isPreviousSpace = true;
                    break;
                case '.':
                    if (isPreviousSpace || isPreviousArifmetic) {
                        throw new ExpressionExeption("Перед точкой недопустимы иной символ, кроме как цифра!");
                    }
                    if (isPointExist) {
                        throw new ExpressionExeption("Слишком много точек!");
                    }
                    isPointExist = true;
                    formatText += text.charAt(i);
                    break;
                case ',':
                    throw new ExpressionExeption("Запятые не допустимы, используйте символ точки");
                case '(': case '[': case '{': case ')': case ']': case '}':
                    throw new ExpressionExeption("Скобки не допустимы!");
                    // Скобки тоже можно отнести, как и запятую, к недопустимым символам, но для понимания,
                    // решено было вывести отдельное сообщение под них.
                default:
                    throw new ExpressionExeption("В строке есть недопустимый символ!\n");
                }
            }
        if (isPreviousArifmetic) {
            throw new ExpressionExeption("Строка не может заканчиваться на арифметический символ!\n");
        }
        return formatText;
    }
}
