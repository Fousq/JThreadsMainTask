package kz.zhanbolat.jthreads.entity;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kz.zhanbolat.jthreads.exception.CubeMatrixException;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class CubeMatrix extends Matrix {
	public static final int MIN_SIZE_OF_MATRIX = 8;
	public static final int MAX_SIZE_OF_MATRIX = 12;
	
	public CubeMatrix() {
		super();
	}
	
	public CubeMatrix(List<List<Cell>> matrix) throws MatrixException {
		super(matrix);
	}
	
	@Override
	public void setMatrix(List<List<Cell>> matrix) throws MatrixException {
		if (matrix.size() != matrix.get(0).size()) {
			throw new CubeMatrixException("matrix is not a cube matrix.");
		}
		super.setMatrix(matrix);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
