package kz.zhanbolat.jthreads.action;

public class DataAction {
	
	public String obtainMatrixData(String data) {
		String[] dataRows = data.split("[\n]");
		StringBuilder matrix = new StringBuilder();
		for (int i = 0; i < dataRows.length - 1; i++) {
			matrix.append(dataRows[i] + "\n");
		}
		return matrix.toString();
	}
	
	public String obtainThreadsData(String data) {
		String[] dataRows = data.split("[\n]");
		return dataRows[dataRows.length - 1];
	}
	
}
