package main.com.labs.lab2;

import java.util.Scanner;

public class EnterValues {
    public Object enterDouble() throws Exception {
        double res;
        Scanner scanner = new Scanner(System.in);
        try {
            res = scanner.nextDouble();
        } catch (Exception e) {
            throw new Exception();
        }
        return res;
    }
}
