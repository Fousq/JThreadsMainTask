package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.action.FileAction;
import kz.zhanbolat.jthreads.converter.DataToCubeMatrixConverter;
import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.CubeMatrixException;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class DataToCubeMatrixConverterTest {
	private static Logger logger = LogManager.getLogger(DataToCubeMatrixConverterTest.class);
	private static Matrix matrix;
	private static String data;
	private static FileAction fileAction;
	private static DataToCubeMatrixConverter converter;
	
	@BeforeClass
	public static void init() {
		try {
			matrix = new CubeMatrix();
			matrix.setMatrix(new ArrayList<List<Cell>>() {
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
			converter = new DataToCubeMatrixConverter();
		} catch (MatrixException e) {
			logger.error("Error in init of matrix", e);
		}
	}
	
	@Test
	public void convertShouldBeDoneCorrectly() {
		Matrix cubeMatrix = null;
		try {
			cubeMatrix = converter.convert(data);
		} catch (MatrixException e) {
			logger.error("Error in converting.", e);
			fail();
		}
		assertEquals(matrix.columnSize(), cubeMatrix.columnSize());
		assertEquals(matrix.rowSize(), cubeMatrix.rowSize());
		for (int i = 0; i < cubeMatrix.rowSize(); i++) {
			for (int j = 0; j < cubeMatrix.columnSize(); j++) {
				logger.debug("i: " + i);
				logger.debug("j: " + j);
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
			logger.debug("-------UsingFileAction---------");
			Matrix cubeMatrix = converter.convert(fileAction.loadData());
			assertEquals(matrix.columnSize(), cubeMatrix.columnSize());
			assertEquals(matrix.rowSize(), cubeMatrix.rowSize());
			logger.debug("Size of row: " + matrix.rowSize());
			logger.debug("Size of row: " + cubeMatrix.rowSize());
			logger.debug("Size of column: " + matrix.columnSize());
			logger.debug("Size of column: " + cubeMatrix.columnSize());
			for (int i = 0; i < cubeMatrix.rowSize(); i++) {
				for (int j = 0; j < cubeMatrix.columnSize(); j++) {
					logger.debug("i: " + i);
					logger.debug("j: " + j);
					Cell expectedCell = matrix.getCell(i, j);
					Cell cell = cubeMatrix.getCell(i, j);
					logger.debug("Expected cell: " + expectedCell);
					logger.debug("Cell: " + cell);
					assertEquals(expectedCell.getValue(),cell.getValue());
				}
			}
		} catch (IOException | MatrixException e) {
			logger.error("Error in file or converting.", e);
			fail();
		}
	}
	
}
