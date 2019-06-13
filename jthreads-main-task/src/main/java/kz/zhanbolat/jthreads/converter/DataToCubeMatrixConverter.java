package kz.zhanbolat.jthreads.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;

public class DataToCubeMatrixConverter {
	
	private static Logger logger = LogManager.getLogger(DataToCubeMatrixConverter.class);
	
	public Matrix convert(String data) {
		String[] dataRow = data.split("[\n]");
		Matrix matrix = CubeMatrix.getInstance();
		String[] dataColumn = null;
		List<Cell> row = new ArrayList<Cell>();
		for (int i = 0; i < dataRow.length; i++) {
			dataColumn = dataRow[i].split("[ ]");
			for (int j = 0; j < dataColumn.length; j++) {
				int value;
				try {
					value = Integer.parseInt(dataColumn[j]);
				} catch (NumberFormatException e) {
					logger.error("Value is not a integer number. "
							+ "Value will be set to -1.",
							e);
					value = -1;
				}
				row.add(new Cell(value));
			}
			matrix.add(new ArrayList<>(row));
			row.clear();
		}
		return matrix;
	}
	
}
