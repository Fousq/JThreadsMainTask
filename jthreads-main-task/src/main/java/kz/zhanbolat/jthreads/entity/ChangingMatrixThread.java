package kz.zhanbolat.jthreads.entity;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangingMatrixThread extends Thread {
	private static Logger logger = LogManager.getLogger(ChangingMatrixThread.class);
	private Matrix matrix;
	
	public ChangingMatrixThread(Matrix matrix) {
		this.matrix = matrix;
	}
	
	@Override
	public void run() {
		Handler handler = null;
		handler = Handler.getInstance();
		logger.debug(Thread.currentThread().getName() + "Got a handler");
		if (handler != null) {
				try {
					for(int i = 0; i < matrix.columnSize(); i++) {
						handler.handle(Thread.currentThread(), matrix);
						TimeUnit.MILLISECONDS.sleep(10);
					}
				} catch (InterruptedException e) {
					logger.error("Thread was interrupted.", e);
				}
		}
	}
	
}
