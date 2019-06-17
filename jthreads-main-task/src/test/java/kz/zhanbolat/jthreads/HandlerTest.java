package kz.zhanbolat.jthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.action.Handler;
import kz.zhanbolat.jthreads.entity.Cell;
import kz.zhanbolat.jthreads.entity.CubeMatrix;
import kz.zhanbolat.jthreads.entity.Matrix;
import kz.zhanbolat.jthreads.exception.MatrixException;

public class HandlerTest {
	private static Logger logger = LogManager.getLogger(HandlerTest.class);
	private static Matrix cubeMatrix;
	
	@BeforeClass
	public static void init() {
		try {
			List<List<Cell>> matrix = new ArrayList<>();
			List<Cell> row = new ArrayList<>();
			Random random = new Random();
			int length = 8;
			for (int i = 0; i < length; i++) {
				for (int j = 0; j < length; j++) {
					row.add(new Cell(random.nextInt(1000)));
				}
				matrix.add(new ArrayList<>(row));
				row.clear();
			}
			cubeMatrix = new CubeMatrix(matrix);
		} catch (MatrixException e) {
			logger.error("Error in init the matrix.", e);
		}
	}
	
	@Test
	public void handleFunTest() {
		{
			Thread firstThread = new Thread(()->  {
				Handler handler = null;
				handler = Handler.getInstance();
				logger.debug(Thread.currentThread().getName() + "Got a handler");
				if (handler != null) {
					try {
						logger.debug("Some letter");
						for(int i = 0; i < cubeMatrix.columnSize(); i++) {
							handler.handle(Thread.currentThread(), cubeMatrix);
							TimeUnit.NANOSECONDS.sleep(100);
						}
					} catch (InterruptedException e) {
						logger.error("Thread was interrupted.", e);
					}
				}
			});
			Thread secondThread = new Thread(()->  {
				Handler handler = null;
				handler = Handler.getInstance();
				logger.debug(Thread.currentThread().getName() + "Got a handler");
				if (handler != null) {
					try {
						for(int i = 0; i < cubeMatrix.columnSize(); i++) {
							handler.handle(Thread.currentThread(), cubeMatrix);
							TimeUnit.NANOSECONDS.sleep(100);
						}
					} catch (InterruptedException e) {
						logger.error("Thread was interrupted.", e);
					}
				}
			});
			firstThread.start();
			secondThread.start();
		}
		{
			Thread firstThread = new Thread(()->  {
				Handler handler = null;
				handler = Handler.getInstance();
				logger.debug(Thread.currentThread().getName() + "Got a handler");
				if (handler != null) {
						try {
							for(int i = 0; i < cubeMatrix.columnSize(); i++) {
								handler.handle(Thread.currentThread(), cubeMatrix);
								TimeUnit.NANOSECONDS.sleep(100);
							}
						} catch (InterruptedException e) {
							logger.error("Thread was interrupted.", e);
						}
				}
			});
			Thread secondThread = new Thread(()->  {
				Handler handler = null;
				handler = Handler.getInstance();
				logger.debug(Thread.currentThread().getName() + "Got a handler");
				if (handler != null) {
					try {
						for(int i = 0; i < cubeMatrix.columnSize(); i++) {
							handler.handle(Thread.currentThread(), cubeMatrix);
							TimeUnit.NANOSECONDS.sleep(100);
						}
					} catch (InterruptedException e) {
						logger.error("Thread was interrupted.", e);
					}
				}
			});
			firstThread.start();
			secondThread.start();
		}
	}
	
}
