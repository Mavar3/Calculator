package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.company.Exceptions.DivisionException;

public class CalculatorWithRPN extends CalculatorFather {
    /**
     * Вывести на экран значение выражения из передваеммого текста с использованием алгоритма списка
     * @param text Передаваемое выражение
     */
    public void printCalculate(String text) {
        try {
            System.out.println(rpnToAnswer(expressionToRPN(expression.checkExpression(text))));
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
            return rpnToAnswer(expressionToRPN(expression.checkExpression(text)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Перевод обратной польской записи (нотации) в ответ
     * @param rpn Текстовое выражение в виде польской записи
     * @return Ответ в виде Double или исключение DivisionException
     * @throws DivisionException
     */
    private Double rpnToAnswer(String rpn) throws DivisionException {
        Stack<Double> stack = new Stack<>();
        List<String> expression = Arrays.asList(rpn.split(" ").clone());
        Double a = 0.0;
        Double b = 0.0;
        for (int i = 0; i < expression.size(); i++) {
            switch (expression.get(i)) {
                case "+":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b + a);
                    break;
                case "-":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b - a);
                    break;
                case "*":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b * a);
                    break;
                case "/":
                    a = stack.pop();
                    if (a == 0.0) {
                        throw new DivisionException("На ноль делить нельзя!");
                    }
                    b = stack.pop();
                    stack.push(b / a);
                    break;
                default:
                    stack.push(Double.parseDouble(expression.get(i)));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * Перевод выражения в обратную польскую запись (нотацию)
     * @param text Выражение в виде текста
     * @return Выражение в виде обратной польской записи
     */
    private String expressionToRPN(String text) {
        String current = "";
        Stack<Character> stack = new Stack<>();
        int priority;
        for (int i = 0; i < text.length(); i++) {
            priority = getPriority(text.charAt(i));
            switch (priority){
                case 0:
                    current += text.charAt(i);
                    break;
                case 1:
                case 2:
                    current += " ";
                    while(!stack.empty()) {
                        if(getPriority(stack.peek()) >= priority) {
                            current +=stack.pop() + " ";
                        }
                        else {
                            break;
                        }
                    }
                    stack.push(text.charAt(i));
                    break;
            }
        }
        while (!stack.empty()) {
            current += " " + stack.pop();
        }
        return current.trim();
    }

    /**
     * Растановка приоритетов по арифметическим знакам
     * @param symbol Арифметический знак
     * @return 2 = "* /", 1 = "+ -", 0 = Любое число
     */
    private int getPriority(char symbol) {
        switch (symbol) {
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }
}
