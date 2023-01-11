package com.example.demo.controller;

public class IntegralInfo {
    private final double start;
    private final double end;
    private final double step;

    public IntegralInfo(double start, double end, double step){
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public double getStart() {
        return start;
    }

    public double getEnd() {
        return end;
    }
}
