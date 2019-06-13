package kz.zhanbolat.jthreads;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import kz.zhanbolat.jthreads.action.FileAction;

public class FileActionTest {

	private static Logger logger = LogManager.getLogger(FileActionTest.class);
	private static FileAction fileAction;
	
	@BeforeClass
	public static void init() {
		fileAction = new FileAction();
	}
	
	@Test
	public void loadDataShouldThrowIOException() {
		try {
			fileAction.setFile("../data/data.txt");
			fileAction.loadData();
			fail();
		} catch(IOException e) {
			logger.error("Got problem in reading from the file.", e);
			assertTrue(e.getMessage().startsWith("..\\data\\data.txt"));
		}
	}
	
	@Test
	public void loadDataShouldThrowIOExceptionUsingFile() {
		try {
			File file = new File("../data/data.txt");
			fileAction.setFile(file);
			fileAction.loadData();
			fail();
		} catch(IOException e) {
			logger.error("Got problem in reading from the file.", e);
			assertTrue(e.getMessage().startsWith("..\\data\\data.txt"));
		}
	}

	@Test
	public void loadDataShouldReturnString() {
		try {
			String[] expected = new String[] {"aasd\r","afd\r", "123\r"};
			fileAction.setFile("data\\Matrix.txt");
			String data = fileAction.loadData();
			logger.debug(data);
			String[] dataArray = data.split("[ \n]");
			assertEquals(expected.length, dataArray.length);
			for (int i = 0; i < expected.length; i++) {
				assertThat(expected[i], is(dataArray[i]));
			}
		} catch (IOException e) {
			logger.error("Got problem in reading from the file.", e);
			fail();
		}
	}
	
}
