package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import kz.zhanbolat.jthreads.action.FileAction;
import kz.zhanbolat.jthreads.converter.DataToCubeMatrixConverter;
import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class DataToCubeMatrixConverterTest {
	private static Logger logger = LogManager.getLogger(DataToCubeMatrixConverterTest.class);
	private static Matrix matrix;
	private static String data;
	private static FileAction fileAction;
	
	@BeforeClass
	public static void init() {
		try {
			matrix = new CubeMatrix(new ArrayList<List<Cell>>() {
				{
					add(new ArrayList<Cell>() {
						{
							add(new Cell(1));
							add(new Cell(5));
						}
					});
					add(new ArrayList<Cell>() {
						{
							add(new Cell(4));
							add(new Cell(2));
						}
					});
				}
			});
			data = "1 5\n"
					+ "4 2";
			fileAction = new FileAction("data\\Matrix.txt");
		} catch (MatrixException e) {
			logger.error("Error in init of matrix", e);
		}
	}
	
	@Test
	public void convertShouldBeDoneCorrectly() {
		Matrix cubeMatrix = new DataToCubeMatrixConverter().convert(data);
		assertEquals(matrix.columnSize(), cubeMatrix.columnSize());
		assertEquals(matrix.rowSize(), cubeMatrix.rowSize());
		for (int i = 0; i < cubeMatrix.rowSize(); i++) {
			for (int j = 0; j < cubeMatrix.columnSize(); j++) {
				Cell expectedCell = matrix.getCell(i, j);
				Cell cell = cubeMatrix.getCell(i, j);
				logger.debug("Expected cell: " + expectedCell);
				logger.debug("Cell: " + cell);
				assertEquals(expectedCell.getValue(), cell.getValue());
			}
		}
	}
	
	@Test
	public void convertShouldBeDoneCorrectlyUsingFileAction() {
		try {
			Matrix cubeMatrix = new DataToCubeMatrixConverter().convert(fileAction.loadData());
			assertEquals(matrix.columnSize(), cubeMatrix.columnSize());
			assertEquals(matrix.rowSize(), cubeMatrix.rowSize());
			for (int i = 0; i < cubeMatrix.rowSize(); i++) {
				for (int j = 0; j < cubeMatrix.columnSize(); j++) {
					Cell expectedCell = matrix.getCell(i, j);
					Cell cell = cubeMatrix.getCell(i, j);
					logger.debug("Expected cell: " + expectedCell);
					logger.debug("Cell: " + cell);
					assertEquals(expectedCell.getValue(),cell.getValue());
				}
			}
		} catch (IOException e) {
			logger.error("Error in file.", e);
			fail();
		}
	}
	
}
