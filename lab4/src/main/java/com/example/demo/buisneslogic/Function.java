package com.example.demo.buisneslogic;

public class Function {
    String name = "sin(x)^(3/4)";
    public double countFunc(double num){
        return Math.pow(Math.sin(num),3.0/4);
    }

    public double countIntegral(double start, double end, double step){
        int n = (int) ((end-start)/step);
        double sum = 0;

        sum+=(countFunc(start)+countFunc(end))/2;
        for (int i=1; i<n; i++){
            sum+=countFunc(start+step*i);
        }
        double averSum = 0;
        for (int i=1; i<n; i++){
            averSum+=countFunc((2*start+step*(2*i-1))/2);
        }
        averSum*=2;
        sum+=averSum;

        sum*=step/3;
        return sum;
    }

    public String getName() {
        return name;
    }
}
