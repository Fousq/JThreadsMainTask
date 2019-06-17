package kz.zhanbolat.jthreads.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.exception.CubeMatrixException;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class CubeMatrixGenerator {
	private static Logger logger = LogManager.getLogger(CubeMatrixGenerator.class);
	private static Random random = new Random();
	
	public CubeMatrix generate(int n, int bound)
							throws MatrixException {
		if (n > CubeMatrix.MAX_SIZE_OF_MATRIX || 
			n < CubeMatrix.MIN_SIZE_OF_MATRIX) {
			throw new CubeMatrixException("N size must be between values of "
										  + CubeMatrix.MIN_SIZE_OF_MATRIX 
										  + " and " 
										  + CubeMatrix.MAX_SIZE_OF_MATRIX);
		}
		List<List<Cell>> matrix = new ArrayList<>();
		List<Cell> row = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					row.add(new Cell(0));
				} else {
					row.add(new Cell(random.nextInt(bound), false));
				}
			}
			matrix.add(new ArrayList<>(row));
			row.clear();
		}
		return new CubeMatrix(matrix);
	}
	
}
