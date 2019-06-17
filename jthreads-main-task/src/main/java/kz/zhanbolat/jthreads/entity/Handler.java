package kz.zhanbolat.jthreads.entity;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Handler {
	private static Logger logger = LogManager.getLogger(Handler.class);
	private static Handler instance;
	private static Lock lock = new ReentrantLock();
	private final int MAX_QUANTITY_OF_THREADS = 6;
	private static Semaphore semaphore;
	private int countOfChanges = 0;
	
	private Handler() {
		semaphore = new Semaphore(MAX_QUANTITY_OF_THREADS);
	}
	
	public static Handler getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new Handler();
			}
			return instance;
		} finally {
			lock.unlock();
		}
	}
	
	public void handle(Thread thread, Matrix matrix) {
		lock.lock();
		logger.debug(thread.getName() + " entered the handle fun.");
		try {
			if (countOfChanges == matrix.columnSize()) {
				return;
			}
			int index = countOfChanges;
			Cell cell = matrix.getCell(index, index);
			logger.info("Cell's value before: " + cell.getValue());
			Random random = new Random();
			cell.setValue(random.nextInt(100));
			countOfChanges++;
			logger.info("Cell's value now: " + cell.getValue());
			logger.info("Changed by thread: " + thread.getName());
		} finally {
			logger.debug(thread.getName() + " get out of the handle fun.");
			lock.unlock();
		}
	}
	
}
