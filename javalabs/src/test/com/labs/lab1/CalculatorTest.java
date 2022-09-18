package test.com.labs.lab1;

import main.com.labs.lab1.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matcher.*;

public class CalculatorTest {
    private StringCalculator strCalc = new StringCalculator();
    @Before
    public void setUp() {
        System.out.println("Starting Test...");
    }

    @Test
    public void commaDelimiter() throws CalculatorException {
        assertEquals(0, strCalc.add(""));
        assertEquals(1, strCalc.add("1"));
        assertEquals(3, strCalc.add("1,2"));
    }

    @Test
    public void anyAmount() throws CalculatorException {
        assertEquals(30, strCalc.add("3,7,8,2,10"));
    }

    @Test
    public void newStringDelimiter() throws CalculatorException {
        assertEquals(6, strCalc.add("1\n2,3"));
    }

    @Test
    public void userDelimiter() throws CalculatorException {

        assertEquals(3, strCalc.add("//;\\n1;2"));
    }

    @Test
    public void negativeNumbers() throws CalculatorException {
        try {
            strCalc.add("-3,5,-2,7");
        }catch (CalculatorException e) {
            assertEquals("Some values less than zero: -3 -2 ",e.getMessage());
        }
    }

    @Test
    public void aboveThousand() throws CalculatorException {
        assertEquals(1999, strCalc.add("1000,999,1001"));
    }

    @Test
    public void anyLengthOfDelimiter() throws CalculatorException {
        assertEquals(6, strCalc.add("//[**]\\n1**2**3"));
    }

    @Test
    public void severalDelimiter() throws CalculatorException {
        assertEquals(6, strCalc.add("//[*][%]\\n1*2%3"));
    }

    @Test
    public void severalAnyLengthDelimiter() throws CalculatorException {
        assertEquals(6, strCalc.add("//[***][%%]\\n1***2%%3"));
    }
}
