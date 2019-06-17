package kz.zhanbolat.jthreads.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < columnSize(); i++) {
			for (int j = 0; j < rowSize(); j++) {
				builder.append(getCell(i, j).getValue() + " ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(matrix);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix other = (Matrix) obj;
		return Objects.equals(matrix, other.matrix);
	}
	
}
