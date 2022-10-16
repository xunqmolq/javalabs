package main.com.labs.lab2;

import java.lang.Math;
import java.util.Objects;
import java.text.DecimalFormat;


public class Matrix implements IMatrix{
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private int row, column;
    private double[][] matrix;

    public Matrix(int size) {
        this.row = size;
        this.column = size;
        this.matrix = new double[row][column];
    }

    public Matrix(int row, int column) {
        this.column = column;
        this.row = row;
        this.matrix = new double[row][column];
    }

    public Matrix(Matrix matrixToCopy) {
        this.matrix = matrixToCopy.getMatrix();
        this.row = matrixToCopy.getNumberOfRow();
        this.column = matrixToCopy.getNumberOfColumn();
    }

    protected Matrix (int row, int column, double[][] matrix){
        this.matrix = matrix;
        this.row = row;
        this.column = column;
    }
    /**
     * This method fills matrix with random double values in range (-100 - 100).
     * @return void
     */
    public void fillRandom() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = Math.random() * (201) - 100;
            }
        }
    }
    /**
     * This method fills matrix with values which user can type by keyboard.
     * @return void
     * @throws Exception if entered value can't be converted into double
     */
    public void userFills() {
        EnterValues enterValues = new EnterValues();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.println("Enter a value at position " + i + ' ' + j + ':');
                while (true) {
                    try {
                        matrix[i][j] = (double) enterValues.enterDouble();
                    } catch (Exception e) {
                        System.err.println("Incorrect value!");
                        continue;
                    }
                    break;
                }
            }
        }
    }
    /**
     *   This method print to the terminal matrix like this:
     *         1,0 2,3 2,5
     *         3,5 -3,0 2,0
     *         1,3 2,6 -3,6
     *   @return matrix to the terminal
     */
    public void printMatrix() {
        for (int i = 0; i < row; i++) {
            String string = "";
            for (int j = 0; j < column; j++) {
                string += df.format(matrix[i][j]) + " ";
            }
            System.out.println(string);
        }
    }


    /**
     * This method return array of doubles which contains in matrix like a row by index
     *        @param index an int, which reveal position of row
     *        @return row by index
     */
    public double[] getRow(int index) {
        double[] row = new double[this.row];
        for (int i = 0; i < this.row; i++) {
            row[i] = matrix[index][i];
        }
        return row;
    }
    /**
     * This method return array of doubles which contains in matrix like a column by index
     *      @param index indicates position of column
     *      @return column by index
     */
    public double[] getColumn(int index) {
        double[] column = new double[this.column];
        for (int i = 0; i < this.column; i++) {
            column[i] = matrix[i][index];
        }
        return column;
    }
    /**
     * This method returns value at position (row, column) if given position exists in matrix
     * @param row indicates row of element
     * @param column indicates column of element
     * @return matrix's value at given position
     * @throws Exception if current position out of matrix
     */
    public double getValue(int row, int column) throws Exception {
        double result;
        try {
            result = matrix[row][column];
        } catch (Exception e) {
            throw new Exception("Position not available");
        }
        return result;
    }

    /**
     * This method converts row and column of current matrix and returns into format "row x column"
     * @return string which represent dimension in format (row x column)
     */
    public String getDimension() {
        return this.row + " x " + this.column;
    }
    /**
     * Generates a hash code for a sequence of input values. The hash code is generated as if all the input values were placed into an matrix
     *  @return hash code of matrix
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column, matrix);
    }
    /**
     * Returns {@code true} if the arguments are equal to each other and {@code false} otherwise. Two {@code null} values are equal.
     * @param obj - an object which will compared to current matrix
     * @return boolean which shows object are equal to each other
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Matrix eq = (Matrix) obj;

        return Objects.deepEquals(this.matrix, eq.matrix)
                && eq.row == this.row
                && eq.column == this.column;
    }
    /**
     * This method creates new diagonal matrix based on given array of doubles
     * @param vector an array of doubles that will be main diagonal in matrix
     * @return diagonal matrix which based on vector
     */
    static public Matrix createDiagonalMatrix(double[] vector) {
        Matrix res = new Matrix(vector.length);
        for (int i = 0; i < vector.length; i++) {
            res.matrix[i][i] = vector[i];
        }
        return res;
    }
    /**
     * This method creates new diagonal matrix which fills with 1 and have dimension equals to size param
     * @param size an int which indicates dimension
     * @return diagonal matrix which filled by 1 on main diagonal
     */
    static public Matrix unitMatrix(int size){
        Matrix res = new Matrix(size);
        for (int i = 0; i < size; i++) {
            res.matrix[i][i] = 1;
        }
        return res;
    }

    /**
     * This method creates row matrix and fills random values
     * @param size an int which represent dimension
     * @return matrix with 1 column and size-row, values are random
     */
    static public Matrix rowMatrix(int size){
        Matrix res = new Matrix (1, size);
        res.fillRandom();
        return res;
    }
    /**
     * This method creates column matrix and fills random values
     * @param size an int which represent dimension
     * @return matrix with 1 row and size-column, values are random
     */
    static public Matrix columnMatrix(int size){
        Matrix res = new Matrix (size, 1);
        res.fillRandom();
        return res;
    }
    /**
     * This method add values at same position of two matrix.
     * @param toAdd an object which implements {@link IMatrix} and will be added to current matrix
     * @return matrix with elements that sum of elements for given matrix.
            */
    public Matrix sum(IMatrix toAdd) {
        if (this.row != toAdd.getNumberOfRow()
                || this.column != toAdd.getNumberOfColumn()){
            throw new Error("Matrices are not the same size");
        }
        Matrix res = new Matrix(this.row, this.column);
        for (int i = 0; i < res.getNumberOfRow(); i++){
            for(int j = 0; j < res.getNumberOfColumn(); j++){
                try {
                    res.matrix[i][j] = this.matrix[i][j] + toAdd.getValue(i,j);
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }
        }
        return res;
    }

    /**
     * This method multiple each element of matrix on given number
     * @param factor an double is number by which to multiply
     */
    public void multiply(double factor){
        if (factor == 1){
            return;
        }
        for (int i = 0; i < this.getNumberOfRow(); i++){
            for (int j = 0; j < this.getNumberOfColumn(); j++){
                this.matrix[i][j] = this.matrix[i][j] * factor;
            }
        }
    }

    public int getNumberOfRow() {
        return row;
    }

    public void setNumberOfRow(int row) {
        this.row = row;
    }

    public int getNumberOfColumn() {
        return column;
    }

    public void setNumberOfColumn(int column) {
        this.column = column;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
