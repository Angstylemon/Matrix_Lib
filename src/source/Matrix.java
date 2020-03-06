package source;

import java.util.List;
import java.util.ArrayList;

public class Matrix {
	private double[][] data;
	private int rows;
	private int cols;
	
	public Matrix(int r, int c) {
		if (r <= 0 || c <= 0) {
			throw new RuntimeException("Invalid matrix size");
		}
		
		rows = r;
		cols = c;
		data = new double[rows][cols];
	}
	
	public double element(int r, int c) {
		return data[r][c];
	}
	
	public static Matrix transpose(Matrix m) {
		Matrix T = new Matrix(m.cols, m.rows);
		
		for (int r = 0; r < m.rows; r++) {
			for (int c = 0; c < m.cols; c++) {
				T.data[c][r] = m.data[r][c];
			}
		}
		
		return T;
	}
	
	public static Matrix add(Matrix a, Matrix b) {
		if (a.rows != b.rows && a.cols != b.cols) {
			throw new RuntimeException("Incompatible dimensions");
		}
		
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		for (int r = 0; r < a.rows; r++) {
			for (int c = 0; c < a.cols; c++) {
				newMatrix.data[r][c] = a.data[r][c] + b.data[r][c];
			}
		}
		
		return newMatrix;
	}
	
	public static Matrix subtract(Matrix a, Matrix b) {
		if (a.rows != b.rows && a.cols != b.cols) {
			throw new RuntimeException("Incompatible dimensions");
		}
		
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		for (int r = 0; r < newMatrix.rows; r++) {
			for (int c = 0; c < newMatrix.cols; c++) {
				newMatrix.data[r][c] = a.data[r][c] - b.data[r][c];
			}
		}
		
		return newMatrix;
	}
	
	public static Matrix multiply(Matrix a, Matrix b) {
		if (a.rows != b.rows && a.cols != b.cols) {
			throw new RuntimeException("Incompatible dimensions");
		}
		
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		
		for (int r = 0; r < a.rows; r++) {
			for (int c = 0; c < a.cols; c++) {
				newMatrix.data[r][c] = a.data[r][c] * b.data[r][c];
			}
		}
		
		return newMatrix;
	}
	
	public static Matrix divide(Matrix a, Matrix b) {
		if (a.rows != b.rows && a.cols != b.cols) {
			throw new RuntimeException("Incompatible dimensions");
		}
		
		Matrix newMatrix = new Matrix(a.rows, a.cols);
		
		for (int r = 0; r < a.rows; r++) {
			for (int c = 0; c < a.cols; c++) {
				newMatrix.data[r][c] = a.data[r][c] / b.data[r][c];
			}
		}
		
		return newMatrix;
	}
	
	public static Matrix dotProduct(Matrix a, Matrix b) {
		if (a.cols != b.rows) {
			throw new RuntimeException("Incompatible dimensions");
		}
		
		Matrix newMatrix = new Matrix(a.rows, b.cols);
		
		for (int r = 0; r < newMatrix.rows; r++) {
			for (int c = 0; c < newMatrix.cols; c++) {
				double sum = 0;
				
				for (int i = 0; i < a.cols; i++) {
					sum += a.data[r][i] * b.data[i][c];
				}
				
				newMatrix.data[r][c] = sum;
			}
		}
		
		return newMatrix;
	}
	
	public void add(double x) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				data[r][c] += x;
			}
		}
	}
	
	public void subtract(double x) {
		add(-1 * x);
	}
	
	public void multiply(double x) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				data[r][c] = data[r][c] * x;
			}
		}
	}
	
	public void divide(double x) {
		multiply(1/x);
	}
	
	public void randomise() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				data[r][c] = Math.random()*2 - 1;
			}
		}
	}
	
	public Matrix sigmoid() {
		Matrix newMatrix = new Matrix(rows, cols);
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				double x = data[r][c];
				newMatrix.data[r][c] = 1/(1 + Math.exp(-x));
			}
		}
		
		return newMatrix;
	}
	
	public Matrix dsigmoid() {
		Matrix newMatrix = new Matrix(rows, cols);
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				double x = data[r][c];
				newMatrix.data[r][c] = x * (1 - x);
			}
		}
		
		return newMatrix;
	}
	
	public static Matrix convertToMatrix(double[] arr) {
		Matrix matrix = new Matrix(arr.length, 1);
		
		for (int r = 0; r < arr.length; r++) {
			matrix.data[r][0] = arr[r];
		}
		
		return matrix;
	}
	
	public double[] convertToArray() {
		double[] array = new double[rows*cols];
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				array[cols*r + c] = data[r][c];
			}
		}
		
		return array;
	}
	
	public String toString() {
		String returnString = "";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				returnString += String.format("%.6f", data[r][c]) + "  ";
			}
			returnString += "\n";
		}
		
		return returnString;
	}
	
	
	
	
	public void mutate(double mutation_rate) {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				double mutation_chance = Math.random();
				
				if (mutation_chance < mutation_rate) {
					data[r][c] = Math.random()*2 - 1;
				}
			}
		}
	}
	
	public Matrix copy() {
		Matrix matrixCopy = new Matrix(rows, cols);
		
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				matrixCopy.data[r][c] = data[r][c];
			}
		}
		
		return matrixCopy;
	}
}
