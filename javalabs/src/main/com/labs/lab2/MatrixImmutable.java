package main.com.labs.lab2;

import java.text.DecimalFormat;
import java.util.Objects;
import java.text.DecimalFormat;

public final class MatrixImmutable implements IMatrix{
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final int row;
    private final int column;
    private final double[][] matrix;

    public MatrixImmutable(int row, int column, double[][] matrix) {
        this.row = row;
        this.column = column;
        this.matrix = matrix;
    }
    public MatrixImmutable(int size) {
        this.row = size;
        this.column = size;
        this.matrix = new double[row][column];
    }
    public MatrixImmutable(int row, int column) {
        this.row = row;
        this.column = column;
        this.matrix = new double[row][column];
    }

    private MatrixImmutable (IMatrix toCopy){
        this.column = toCopy.getNumberOfColumn();
        this.row = toCopy.getNumberOfRow();
        this.matrix = toCopy.getMatrix();
    }

    public double[][] getMatrix(){
        return matrix;
    }

    /**
     * This method creates new immutable matrix based on current matrix and fills it with random double values.
     *@return Immutable matrix with the same dimension as the called matrix, filled with random double values (-100 - 100)
     */
    public MatrixImmutable randomMatrix() {
        Matrix mat = new Matrix(this.row, this.column);
        mat.fillRandom();
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }
    /**
     * This method creates immutable matrix based on current matrix with values which user can type by keyboard.
     * @return Immutable matrix with user's value
     * @throws Exception if entered value can't be converted into double
     */
    public MatrixImmutable fillsByUser() {
        Matrix mat = new Matrix(this.row, this.column);
        mat.userFills();
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
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
        return this.row + "x" + this.column;
    }

    @Override
    public int getNumberOfRow() {
        return 0;
    }

    @Override
    public int getNumberOfColumn() {
        return 0;
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
        MatrixImmutable eq = (MatrixImmutable) obj;

        return Objects.deepEquals(this.matrix, eq.matrix)
                && eq.row == this.row
                && eq.column == this.column;
    }

    /**
     * This method creates new immutable diagonal matrix based on given array of doubles
     * @param vector an array of doubles that will be main diagonal in matrix
     * @return immutable diagonal matrix which based on vector
     */
    static public MatrixImmutable createDiagonalMatrix(double[] vector) {
        IMatrix mat = Matrix.createDiagonalMatrix(vector);
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }
    /**
     * This method creates new immutable diagonal matrix which fills with 1 and have dimension equals to size param
     * @param size an int which indicates dimension
     * @return immutable diagonal matrix which filled main diagonal by 1
     */
    static public MatrixImmutable unitMatrix(int size){
        IMatrix mat = Matrix.unitMatrix(size);
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }
    /**
     * This method creates immutable row matrix and fills random values
     * @param size an int which represent dimension
     * @return immutable matrix with 1 column and size-row, values are random
     */
    static public MatrixImmutable rowMatrix(int size){
        IMatrix mat = Matrix.rowMatrix(size);
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }
    /**
     * This method creates immutable column matrix and fills random values
     * @param size an int which represent dimension
     * @return immutable matrix with 1 row and size-column, values are random
     */
    static public MatrixImmutable columnMatrix(int size){
        IMatrix mat = Matrix.columnMatrix(size);
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }

    /**
     * This method add values at same position of two matrix.
     * @param toAdd an object which implements {@link IMatrix} and will be added to current matrix
     * @return immutable matrix with elements that sum of elements for given matrix.
     */
    public MatrixImmutable sum(IMatrix toAdd) {
        Matrix mat = new Matrix(this.row, this.column, this.matrix);
        mat = mat.sum(toAdd);
        MatrixImmutable res = new MatrixImmutable(mat);
        return res;
    }

}
