package com.example.demo.dao;

public class WritedFunction {
    private String function;
    private double result;
    private double step;
    private double start;
    private double end;
    private int id;
    public WritedFunction(String function, double result, double step, double start, double end, int id){
        this.function = function;
        this.result = result;
        this.step = step;
        this.start = start;
        this.end = end;
        this.id = id;
    }
    protected WritedFunction(){

    }

    public String getFunction() {
        return function;
    }

    public double getEnd() {
        return end;
    }

    public double getResult() {
        return result;
    }

    public double getStart() {
        return start;
    }

    public double getStep() {
        return step;
    }

    public int getId() {
        return id;
    }
}
