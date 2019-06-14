package kz.zhanbolat.jthreads.entity;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import kz.zhanbolat.jthreads.exception.CubeMatrixException;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class CubeMatrix extends Matrix {
	
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
	
}
