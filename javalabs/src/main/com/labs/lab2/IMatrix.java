package main.com.labs.lab2;

import java.util.Objects;

public interface IMatrix {

    public void printMatrix();
    public double[] getRow(int index);

    public double[] getColumn(int index);

    public double getValue(int row, int column) throws Exception;

    public String getDimension();

    public int getNumberOfRow();

    public int getNumberOfColumn();

    public double[][] getMatrix();


}
