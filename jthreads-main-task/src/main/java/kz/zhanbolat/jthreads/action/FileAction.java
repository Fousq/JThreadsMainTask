package kz.zhanbolat.jthreads.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileAction {
	private static Logger logger = LogManager.getLogger(FileAction.class);
	private File file;
	
	public FileAction() {
		
	}
	
	public FileAction(File file) {
		this.file = file;
	}
	
	public FileAction(String path) {
		this.file = new File(path);
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public void setFile(String path) {
		this.file = new File(path);
	}
	
	public String loadData() throws IOException {
		StringBuilder data = new StringBuilder();
		BufferedReader bf = new BufferedReader(new FileReader(file.getPath()));
		for (String line = bf.readLine(); line != null; line = bf.readLine()) {
			data.append(line);
			data.append("\n");
		}
		return data.toString();
	}
	
}
