package com.company;

public abstract class CalculatorFather {
    public abstract void printCalculate(String text);
    public abstract Double calculate(String text);
    protected Expression expression = new Expression();
    protected Environment environment = new Environment();
}
