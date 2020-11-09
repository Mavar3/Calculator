package com.company;

public abstract class CalculatorFather {

    public abstract void printCalculate(String text);
    public abstract Double calculate(String text);


    /**
     * Проверяет текст на допустимые символы
     * @param text исходный текст
     * @return  возвращает текст без пробелов между символами или ошибку
     */
    protected static String checkText(String text) throws ExpressionExeption {
        boolean isPreviousNumber = false;
        boolean isPreviousArifmetic = false;
        boolean isPreviousSpace = false;
        boolean isItFirstArifmetic = true;
        boolean isPointExist = false;
        String formatText = "";
        if (text.length() == 0) {
            throw new ExpressionExeption("Слишком мало параметров");
        }
        for(int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '0': case '1': case '2': case '3':
                case '4': case '5': case '6': case '7':
                case '8': case '9':
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
