package com.company;

import com.company.Exceptions.ExpressionException;

public class Expression {
    /**
     * Проверяет текст на допустимые символы
     * @param expression исходный текст
     * @return  возвращает текст без пробелов между символами или ошибку
     */
    protected static String checkExpression(String expression) throws ExpressionException {
        boolean isPreviousNumber = false;
        boolean isPreviousArifmetic = false;
        boolean isPreviousSpace = false;
        boolean isItFirstArifmetic = true;
        boolean isPointExist = false;
        String formatText = "";
        if (expression.length() == 0) {
            throw new ExpressionException("Слишком мало параметров");
        }
        for(int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '0': case '1': case '2': case '3':
                case '4': case '5': case '6': case '7':
                case '8': case '9':
                    if(isPreviousNumber && isPreviousSpace){
                        throw new ExpressionException("Пробелы между цифрами не допустимы!");
                    }
                    isPreviousNumber = true;
                    isPreviousArifmetic = false;
                    isPreviousSpace = false;
                    formatText += expression.charAt(i);
                    break;
                case '+': case '-': case '*': case '/':
                    if(isPreviousArifmetic || (isItFirstArifmetic && !isPreviousNumber)) {
                        throw new ExpressionException("Невозможно распознать арифметические знаки.");
                    }
                    isPreviousArifmetic = true;
                    isPreviousNumber = false;
                    isPreviousSpace = false;
                    isItFirstArifmetic = false;
                    isPointExist = false;
                    formatText += expression.charAt(i);
                    break;
                case ' ':
                    isPreviousSpace = true;
                    break;
                case '.':
                    if (isPreviousSpace || isPreviousArifmetic) {
                        throw new ExpressionException("Перед точкой недопустимы иной символ, кроме как цифра!");
                    }
                    if (isPointExist) {
                        throw new ExpressionException("Слишком много точек!");
                    }
                    isPointExist = true;
                    formatText += expression.charAt(i);
                    break;
                case ',':
                    throw new ExpressionException("Запятые не допустимы, используйте символ точки");
                case '(': case '[': case '{': case ')': case ']': case '}':
                    throw new ExpressionException("Скобки не допустимы!");
                    // Скобки тоже можно отнести, как и запятую, к недопустимым символам, но для понимания,
                    // решено было вывести отдельное сообщение под них.
                default:
                    throw new ExpressionException("В строке есть недопустимый символ!\n");
            }
        }
        if (isPreviousArifmetic) {
            throw new ExpressionException("Строка не может заканчиваться на арифметический символ!\n");
        }
        return formatText;
    }
}
