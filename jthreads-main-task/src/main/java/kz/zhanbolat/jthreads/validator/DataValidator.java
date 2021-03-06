package kz.zhanbolat.jthreads.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.CubeMatrix;

public class DataValidator {
	private static Logger logger = LogManager.getLogger(DataValidator.class);
	private static final short INDEX_OF_N = 0;
	private static final short INDEX_OF_M = 1;
	private static final int THREADS_MIN_SIZE = 4;
	private static final int THREADS_MAX_SIZE = 6;
	
	public boolean isValidToConvertToMatrix(String data) {
		String[] dataRows = data.split("[\n]");
		if (dataRows.length < CubeMatrix.MIN_SIZE_OF_MATRIX + 1) {
			return false;
		}
		CubeMatrixValidator validator = new CubeMatrixValidator();
		StringBuilder matrix = new StringBuilder();
		for (int i = 0; i < dataRows.length - 1; i++) {
			matrix.append(dataRows[i] + "\n");
			logger.debug(matrix.toString());
		}
		if (!validator.isValid(matrix.toString())) {
			return false;
		}
		int n = dataRows.length - 1;
		int m;
		try {
			m = Integer.parseInt(dataRows[dataRows.length - 1]);
		} catch (NumberFormatException e) {
			return false;
		}
		logger.debug("N : " + n);
		logger.debug("M : " + m);
		return (n >= CubeMatrix.MIN_SIZE_OF_MATRIX && 
					n <= CubeMatrix.MAX_SIZE_OF_MATRIX) && 
				(m >= THREADS_MIN_SIZE && m <= THREADS_MAX_SIZE);
	}
	
	public boolean isValidToGenerateMatrix(String data) {
		String[] dataRows = data.split("[\n]");
		if (dataRows.length < 2 || dataRows.length > 2) {
			return false;
		}
		String[] dataColumns;
		int n;
		int m;
		for (int i = 0; i < dataRows.length; i++) {
			dataColumns = dataRows[i].split("[ ]");
			if (dataColumns.length != 1) {
				return false;
			}
		}
		try {
			n = Integer.parseInt(dataRows[INDEX_OF_N]);
			m = Integer.parseInt(dataRows[INDEX_OF_M]);
		} catch (NumberFormatException e) {
			return false;
		}
		logger.debug("N : " + n);
		logger.debug("M : " + m);
		return (n >= CubeMatrix.MIN_SIZE_OF_MATRIX && 
					n <= CubeMatrix.MAX_SIZE_OF_MATRIX) && 
				(m >= THREADS_MIN_SIZE && m <= THREADS_MAX_SIZE);
	}
	
}
