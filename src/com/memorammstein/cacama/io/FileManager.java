package com.memorammstein.cacama.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringEscapeUtils;
import org.fusesource.jansi.Ansi.Color;

import com.memorammstein.cacama.config.Configuration;

public class FileManager {
	
	private static String logFilename = null;
	private static String logWebpageFilename = null;
	private static File file = null;
	private static File webFile = null;
	private static BufferedWriter bw = null;
	private static BufferedWriter wbw = null;
	
	private static FileManager fm = null;
	
	private FileManager() {
		
	}
	
	public static FileManager getInstance() {
		if (fm == null) {
			fm = new FileManager();
		}
		return fm;
	}

	public synchronized void saveStringlnToFile(Color background, Color foreground, String string) {
		logFilename = Configuration.getInstance().getProperty("logFilename");
		logWebpageFilename = Configuration.getInstance().getProperty("logWebpageFilename");
		try {
			file = new File(logFilename);
			webFile = new File(logWebpageFilename);
			if (!file.exists()) {
				file.createNewFile();
			}
			if (!webFile.exists()) {
				webFile.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(logFilename, true));
			bw.write(string);
			bw.newLine();
		    bw.flush();
		    String html = "<span style=\"color: " + foreground + "; background: " + background + "; font-family: Arial, \"Helvetica Neue\", Helvetica, sans-serif;\">" + StringEscapeUtils.escapeHtml4(string) + "</span><br>";
		    wbw = new BufferedWriter(new FileWriter(logWebpageFilename, true));
		    wbw.write(html);
		    wbw.newLine();
		    wbw.flush();
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
			if (wbw != null) {
				try {
					wbw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
