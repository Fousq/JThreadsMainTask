package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class CubeMatrixTest {
	
	static Logger logger = LogManager.getLogger(CubeMatrixTest.class);
	
	@Test
	public void matrixShouldBeCreated() {
		List<List<Cell>> matrix = new ArrayList<>();
		List<Cell> row = new ArrayList<>();
		Random random = new Random();
		int length = 4;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				row.add(new Cell(random.nextInt(1000)));
			}
			matrix.add(new ArrayList<>(row));
			row.clear();
		}
		logger.debug("Column size: " + matrix.size() + ", Row size: " + 
					matrix.get(0).size());
		try {
			Matrix cubeMatrix = CubeMatrix.getInstance();
			cubeMatrix.setMatrix(matrix);
			for (int i = 0; i < cubeMatrix.columnSize(); i++) {
				for (int j = 0; j < cubeMatrix.rowSize(); j++) {
					logger.debug("Expected: " + matrix.get(i).get(j));
					logger.debug("In cube matrix: " + cubeMatrix.getCell(i, j));
					assertEquals(matrix.get(i).get(j).getValue(), 
								cubeMatrix.getCell(i, j).getValue());
				}
			}
		} catch (MatrixException e) {
			logger.error("Error in init matrix.", e);
			fail();
		}
	}

}
