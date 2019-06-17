package kz.zhanbolat.jthreads;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import kz.zhanbolat.jthreads.validator.DataValidator;

public class DataValidatorTest {
	private static Logger logger = LogManager.getLogger(DataValidatorTest.class);
	private static DataValidator validator;
	private static Random random;
	
	@BeforeClass
	public static void init() {
		validator = new DataValidator();
		random = new Random();
	}
	
	@Test
	public void returnTrue() {
		StringBuilder data = new StringBuilder("8\n4");
		assertTrue(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("12\n6");
		assertTrue(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("8\n4\n");
		assertTrue(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		logger.debug(data.toString());
		assertTrue(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(6);
		logger.debug(data.toString());
		assertTrue(validator.isValidToConvertToMatrix(data.toString()));
	}
	
	@Test
	public void returnFalse() {
		StringBuilder data = new StringBuilder("7\n4");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("8\n3");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("13\n4");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("12\n7");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("13\n7");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder("\n12\n4\n");
		assertFalse(validator.isValidToGenerateMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 7; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 8; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 13; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 12; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(4);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(3);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				data.append(random.nextInt(100) + " ");
			}
			data.append("\n");
		}
		data.append(7);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
		data = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (random.nextBoolean()) {
					data.append(random.nextInt(100) + " ");
				} else {
					data.append("asd ");
				}
			}
			data.append("\n");
		}
		data.append(6);
		assertFalse(validator.isValidToConvertToMatrix(data.toString()));
	}
	
}
