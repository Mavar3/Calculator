//package com.company;
//
//import com.company.Exceptions.DivisionException;

/**
 * Класс, предназначенный для расчёта текстового выражения (На данный момент не рабочий)
 * Не рекомендую к использованию!!!!!
 */

//public class CalculatorWithText extends CalculatorFather {
//    /**
//     * Вывести на экран значение выражения из передваеммого текста
//     * @param text Передаваемое выражение
//     */
//    public void printCalculate(String text) {
//        try {
//            System.out.println(count(expression.checkExpression(text)));
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Вернуть значение выражения из передваеммого текста
//     * @param text Передаваемое выражение
//     * @return double или null при ошибке
//     */
//    public Double calculate(String text) {
//        try {
//            return count(expression.checkExpression(text));
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        }
//    }
//
//    /**
//     * Метод подсчёта
//     * @param text отформатированное выражение
//     * @return значение подсчёта
//     */
//    private double count(String text) throws DivisionException {
//        try {
//            int multiply_position = text.indexOf('*');
//            int division_position = text.indexOf('/');
//            while (multiply_position != -1 || division_position != -1) {
//                if (multiply_position == -1) {
//                    text = arithmeticText(text, division_position);
//                    division_position = text.indexOf('/');
//                } else if (division_position == -1) {
//                    text = arithmeticText(text, multiply_position);
//                    multiply_position = text.indexOf('*');
//                } else {
//                    if (multiply_position < division_position) {
//                        text = arithmeticText(text, multiply_position);
//                        multiply_position = text.indexOf('*');
//                        division_position = text.indexOf('/'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
//                    }
//                    if (division_position < multiply_position) {
//                        text = arithmeticText(text, division_position);
//                        multiply_position = text.indexOf('*'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
//                        division_position = text.indexOf('/');
//                    }
//                }
//            }
//            int add_position = text.indexOf('+');
//            int subtract_position = text.indexOf('-');
//            while (add_position != -1 || subtract_position != -1) {
//                if (add_position == -1) {
//                    text = arithmeticText(text, subtract_position);
//                    subtract_position = text.indexOf('-');
//                } else if (subtract_position == -1 || subtract_position == 0) {
//                    // Когда появиться на первом месте число с минусом, то он начнёт складывать, игнорируя
//                    // последующие минусы. Необходимо решить эту проблему.
//                    text = arithmeticText(text, add_position);
//                    add_position = text.indexOf('+');
//                } else {
//                    if (add_position < subtract_position) {
//                        text = arithmeticText(text, add_position);
//                        add_position = text.indexOf('+');
//                        subtract_position = text.indexOf('-'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
//                    }
//                    if (subtract_position < add_position) {
//                        text = arithmeticText(text, subtract_position);
//                        add_position = text.indexOf('+'); //Но можно просто убрать 1 т.к. он сдвигается на 1 символ
//                        subtract_position = text.indexOf('-');
//                    }
//                }
//            }
//            return Double.parseDouble(text);
//        }
//        catch (DivisionException ex) {
//            throw new DivisionException("Ошибка деления!",ex);
//        }
//    }
//
//    /**
//     * Заменяет два числа и арифметический знак, на значение после арифметического действия
//     * @param text Уравнение в виде текста
//     * @param arifPos Позиция знака
//     * @return Новый текст. арифметический символ, числа до и после - выкинуту и записан результат действия.
//     * В случае ошибки выдаётся null.
//     */
//    private String arithmeticText(String text, int arifPos) throws DivisionException {
//        double count = 0.00;
//        switch (text.charAt(arifPos)) {
//            case '*':
//                count = previousNum(text, arifPos) * nextNum(text, arifPos);
//                break;
//            case '/':
//                if (nextNum(text, arifPos) == 0) {
//                    throw new DivisionException("На ноль делить нельзя!");
//                }
//                count = previousNum(text, arifPos) / nextNum(text, arifPos);
//                break;
//            case '+':
//                count = previousNum(text, arifPos) + nextNum(text, arifPos);
//                break;
//            case '-':
//                count = previousNum(text, arifPos) - nextNum(text, arifPos);
//                break;
//        }
//        int startOfNum = 0;
//        for (int i = arifPos - 1; i >= 0; i--) {
//            switch (text.charAt(i)) {
//                case '-': case '+': case '/': case '*':
//                    startOfNum = i + 1;
//                    break;
//            }
//            if (startOfNum != 0) {
//                break;
//            }
//        }
//        int endOfNum = text.length() - 1;
//        for (int i = arifPos + 1; i < text.length(); i++) {
//            switch (text.charAt(i)) {
//                case '-': case '+': case '/': case '*':
//                    endOfNum = i;
//                    break;
//            }
//            if (endOfNum != text.length() - 1) {
//                break;
//            }
//        }
//        if (endOfNum == text.length() - 1) {
//            return text.substring(0, startOfNum) + String.valueOf(count);
//        }
//        return text.substring(0, startOfNum) + String.valueOf(count) + text.substring(endOfNum);
//    }
//
//    /**
//     * Предыдущее число
//     * @param text выражение в виде текста
//     * @param position позиция символа от которого считать
//     * @return Предыдущее число типа Double
//     */
//    private double previousNum(String text, int position) {
//        int startOfNum = 0;
//        boolean isNegativeNum = false;
//        for (int i = position - 1; i >= 0; i--) {
//            switch (text.charAt(i)) {
//                case '-':
////                    isNegativeNum = true;
//                case '+': case '/': case '*':
//                    startOfNum = i + 1;
//                    break;
//            }
//            if (startOfNum != 0) {
//                break;
//            }
//        }
//        Double textInDouble = Double.parseDouble((text.substring(startOfNum, position)));
////        textInDouble = isNegativeNum ? -textInDouble : textInDouble;
//        return textInDouble;
//    }
//
//    /**
//     * Следующее число
//     * @param text выражение в виде текста
//     * @param position позиция символа от которого считать
//     * @return Следующее число типа Double
//     */
//    private double nextNum(String text, int position) {
//        int endOfNum = text.length() - 1;
//        for (int i = position + 1; i < text.length(); i++) {
//            switch (text.charAt(i)) {
//                case '-': case '+': case '/': case '*':
//                    endOfNum = i;
//                    break;
//            }
//            if (endOfNum != text.length() - 1) {
//                break;
//            }
//        }
//        String textForConvertDouble = text.length() - 1 == endOfNum ? text.substring(position + 1)
//                : text.substring(position + 1, endOfNum);
//        Double textInDouble = Double.parseDouble(textForConvertDouble);
//        return textInDouble;
//    }
//
//
//}