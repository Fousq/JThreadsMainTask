package kz.zhanbolat.jthreads.converter;

import java.util.ArrayList;
import java.util.List;

import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;

public class DataToCubeMatrixConverter {
	
	public static Matrix convert(String data) {
		String[] dataRow = data.split("[\n]");
		Matrix matrix = new CubeMatrix();
		String[] dataColumn = null;
		for (int i = 0; i < dataRow.length; i++) {
			dataColumn = dataRow[i].split("[ ]");
			List<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < dataColumn.length; j++) {
				int value = Integer.parseInt(dataColumn[j]);
				row.add(new Cell(value));
			}
			matrix.add(row);
		}
		return matrix;
	}
	
}
