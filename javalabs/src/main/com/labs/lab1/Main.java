package main.com.labs.lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string: ");
        String str = scanner.nextLine();
        StringCalculator stringCalculator = new StringCalculator();
        int sum = -1;
        try {
            sum = stringCalculator.add(str);
        } catch (CalculatorException e) {
            System.err.println("OH... SOMETHING WENT WRONG: "+ e.getMessage());
        }
        if (sum != -1) {
            System.out.println("Sum: " + sum);
        }
    }
}
