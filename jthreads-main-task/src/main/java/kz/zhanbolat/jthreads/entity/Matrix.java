package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kz.zhanbolat.jthreads.exception.MatrixException;

public abstract class Matrix {
	private List<List<Cell>> matrix;
	
	public Matrix() {
		matrix = new ArrayList<>();
	}
	
	public Matrix(List<List<Cell>> matrix) throws MatrixException {
		setMatrix(matrix);
	}

	public List<List<Cell>> getMatrix() {
		return Collections.unmodifiableList(matrix);
	}

	public void setMatrix(List<List<Cell>> matrix) throws MatrixException {
		this.matrix = matrix;
	}
	
	public List<Cell> getRow(int index) {
		return Collections.unmodifiableList(matrix.get(index));
	}
	
	public List<Cell> getColumn(int index) {
		List<Cell> column = new ArrayList<>();
		for (List<Cell> row : matrix) {
			column.add(row.get(index));
		}
		return Collections.unmodifiableList(column);
	}
	
	public Cell getCell(int row, int column) {
		return matrix.get(row).get(column);
	}
	
	public int rowSize() {
		return matrix.get(0).size();
	}
	
	public int columnSize() {
		return matrix.size();
	}
	
	public void add(List<Cell> row) {
		matrix.add(row);
	}
	
}
