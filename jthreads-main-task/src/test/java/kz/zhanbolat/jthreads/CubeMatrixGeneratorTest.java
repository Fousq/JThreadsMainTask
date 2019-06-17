package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.MatrixException;
import kz.zhanbolat.jthreads.generator.CubeMatrixGenerator;

public class CubeMatrixGeneratorTest {
	private static Logger logger = LogManager.getLogger(CubeMatrixGeneratorTest.class);
	private static CubeMatrixGenerator generator;
	
	@BeforeClass
	public static void init() {
		generator = new CubeMatrixGenerator();
	}
	
	@Test
	public void cubeMatrixShouldBeCreated() {
		try {
			int n = 8;
			Matrix matrix = generator.generate(n, 100);
			assertEquals(matrix.rowSize(), n);
			assertEquals(matrix.columnSize(), n);
			for (int i = 0; i < matrix.columnSize(); i++) {
				for (int j = 0; j < matrix.rowSize(); j++) {
					Cell cell = matrix.getCell(i, j);
					if (i == j) {
						assertEquals(cell.getValue(), 0);
						assertTrue(cell.isChangable());
					} else {
						assertFalse(cell.isChangable());
					}
				}
			}
			n = 12;
			matrix = generator.generate(n, 100);
			assertEquals(matrix.rowSize(), n);
			assertEquals(matrix.columnSize(), n);
			for (int i = 0; i < matrix.columnSize(); i++) {
				for (int j = 0; j < matrix.rowSize(); j++) {
					Cell cell = matrix.getCell(i, j);
					if (i == j) {
						assertEquals(cell.getValue(), 0);
						assertTrue(cell.isChangable());
					} else {
						assertFalse(cell.isChangable());
					}
				}
			}
		} catch (MatrixException e) {
			logger.error("Cube matrix cannot be created.", e);
			fail();
		}
	}
	
	@Test
	public void throwMatrixExceptionOnValueBelowTheMin() {
		try {
			generator.generate(3, 100);
		} catch (MatrixException e) {
			assertTrue(e.getMessage().startsWith("N size must be between values of"));
		}
	}
	
	@Test
	public void throwMatrixExceptionOnValueAboveTheMax() {
		try {
			generator.generate(13, 100);
		} catch (MatrixException e) {
			assertTrue(e.getMessage().startsWith("N size must be between values of"));
		}
	}
	
}
