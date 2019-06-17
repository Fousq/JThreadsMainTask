package kz.zhanbolat.jthreads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.zhanbolat.jthreads.action.DataAction;
import kz.zhanbolat.jthreads.action.FileAction;
import kz.zhanbolat.jthreads.converter.DataToCubeMatrixConverter;
import kz.zhanbolat.jthreads.entity.ChangingMatrixThread;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.MatrixException;
import kz.zhanbolat.jthreads.generator.CubeMatrixGenerator;
import kz.zhanbolat.jthreads.validator.DataValidator;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LogManager.getLogger(App.class);
	
    public static void main(String[] args) {
    	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    	FileAction fileAction;
    	String data;
    	DataValidator dataValidator = new DataValidator();
    	CubeMatrixGenerator generator = new CubeMatrixGenerator();
    	DataToCubeMatrixConverter converter = new DataToCubeMatrixConverter();
    	DataAction dataAction = new DataAction();
    	Matrix matrix = null;
    	int m = 0;
    	logger.info("Enter the path to the file to init the matrix: ");
    	try {
    		fileAction = new FileAction(bf.readLine());
    		data = fileAction.loadData();
    		if (dataValidator.isValidToGenerateMatrix(data)) {    				
    			int n = Integer.parseInt(data.split("[\n]")[0]);
    			m = Integer.parseInt(data.split("[\n]")[1]);
    			logger.info("Enter the bound for making random values: ");
    			int bound = Integer.parseInt(bf.readLine());
    			matrix = generator.generate(n, bound);
    		}
    		if (dataValidator.isValidToConvertToMatrix(data)) {
    			String matrixData = dataAction.obtainMatrixData(data);
    			logger.debug(matrixData);
    			matrix = converter.convert(matrixData);
    			m = Integer.parseInt(dataAction.obtainThreadsData(data));
    		}
    		if (matrix == null) {
    			logger.info("Cannot init the matrix." 
    						+ "File should have infomations looking like "
    						+ "one of the belowe variants: \n"
    						+ "1.[N]-matrix size\n [M]-threads size\n"
    						+ "2.[MATRIX{NxN - size}]-your matrix\n "
    						+ "[M]-threads size");
    			return;
    		} else {
    			logger.info("Matrix was created.");
    			logger.info(matrix);
    		}
    	} catch (IOException e) {
    		logger.error("Error in input stream reader.", e);
    	} catch (MatrixException e) {
			logger.error("Error in creating matrix. Please check the file.", e);
		}
    	List<ChangingMatrixThread> threads = new ArrayList<>(m);
    	for (int i = 0; i < m; i++) {
    		ChangingMatrixThread thread = new ChangingMatrixThread(matrix);
    		threads.add(thread);
    		thread.start();
    	}
    }
}
