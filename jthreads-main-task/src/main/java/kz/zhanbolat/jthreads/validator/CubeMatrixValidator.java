package kz.zhanbolat.jthreads.validator;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.CubeMatrix;

public class CubeMatrixValidator {
	private static Logger logger = LogManager.getLogger(CubeMatrixValidator.class);
	
	public boolean isValid(String data) {
		String[] dataRows = data.split("[\n]");
		if (dataRows.length < CubeMatrix.MIN_SIZE_OF_MATRIX ||
				dataRows.length > CubeMatrix.MAX_SIZE_OF_MATRIX) {
			return false;
		}
		logger.debug(Arrays.toString(dataRows));
		String[] dataColumns = null;
		for (int i = 0; i < dataRows.length; i++) {
			dataColumns = dataRows[i].split("[ ]");
			logger.debug(Arrays.toString(dataColumns));
			if (dataColumns.length != dataRows.length) {
				return false;
			}
			for (int j = 0; j < dataColumns.length; j++) {
				int number;
				try {
					number = Integer.parseInt(dataColumns[i]);
				} catch(NumberFormatException e) {
					return false;
				}
				if (i == j && number != 0) {
					return false;
				}
			}
		}
		return true;
	}
	
}
