package com.company;

import com.company.Exceptions.EnviromentException;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    public List<String> arithmetic(String envName) throws EnviromentException {
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
