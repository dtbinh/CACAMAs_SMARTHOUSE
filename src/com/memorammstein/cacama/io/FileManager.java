package com.memorammstein.cacama.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.memorammstein.cacama.config.Configuration;

public class FileManager {
	
	private static String logFilename = null;
	private static File file = null;
	private static BufferedWriter bw = null;
	
	private static FileManager fm = null;
	
	private FileManager() {
		
	}
	
	public static FileManager getInstance() {
		if (fm == null) {
			fm = new FileManager();
		}
		return fm;
	}

	public synchronized void saveStringlnToFile(String string) {
		logFilename = Configuration.getInstance().getProperty("logFilename");
		try {
			file = new File(logFilename);
			if (!file.exists()) {
				file.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(logFilename, true));
			bw.write(string);
			bw.newLine();
		    bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
