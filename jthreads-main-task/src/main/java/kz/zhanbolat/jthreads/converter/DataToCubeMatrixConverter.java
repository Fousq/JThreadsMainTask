package kz.zhanbolat.jthreads.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.CubeMatrixException;
import kz.zhanbolat.jthreads.exception.MatrixException;
import kz.zhanbolat.jthreads.validator.CubeMatrixValidator;

public class DataToCubeMatrixConverter {
	
	private static Logger logger = LogManager.getLogger(DataToCubeMatrixConverter.class);
	private static CubeMatrixValidator validator = new CubeMatrixValidator();
	
	public Matrix convert(String data) throws MatrixException {
		if (!validator.isValid(data)) {
			throw new CubeMatrixException("Data is not valid to convert to "
					+ "integer cube matrix.");
		}
		String[] dataRow = data.split("[\n]");
		List<List<Cell>> matrix = new ArrayList<>();
		String[] dataColumn = null;
		List<Cell> row = new ArrayList<Cell>();
		for (int i = 0; i < dataRow.length; i++) {
			dataColumn = dataRow[i].split("[ ]");
			for (int j = 0; j < dataColumn.length; j++) {
				int value;
				value = Integer.parseInt(dataColumn[j]);
				if (i == j) {
					row.add(new Cell(value));
				} else {
					row.add(new Cell(value, false));
				}
			}
			matrix.add(new ArrayList<>(row));
			row.clear();
		}
		return new CubeMatrix(matrix);
	}
	
}
