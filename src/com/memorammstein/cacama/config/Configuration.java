package com.memorammstein.cacama.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	
	private String filename = "config.properties";
	private Properties prop = null;
	private OutputStream output = null;
	private InputStream input = null;
	
	private static Configuration config = null;
	
	private Configuration() {
		initialConfig();
	}
	
	public static Configuration getInstance() {
		if (config == null) {
			config = new Configuration();
		}
		return config;
	}
	
	public void initialConfig() {
		prop = new Properties();
		output = null;
		if (isPropertiesFileEmptyOrDoesntExist()) {
			try {
				 
				output = new FileOutputStream(filename);
		 
				prop.setProperty("I_exist", "yes");
				prop.setProperty("PrintColor", "true");
				prop.setProperty("RefreshTimeInMillis", "1500");
				prop.setProperty("logFilename", "outputManager.log");
				prop.setProperty("temperature_minimum", "22.5");
				prop.setProperty("temperature_maximum", "24.85");
				prop.setProperty("ilumination_minimum", "15");
				prop.setProperty("ilumination_maximum", "85");
				//each password digit must be diferent
				prop.setProperty("AccessControl_pass_digit1", "0");
				prop.setProperty("AccessControl_pass_digit2", "1");
				prop.setProperty("AccessControl_pass_digit3", "2");
				prop.setProperty("AccessControl_pass_digit4", "3");
				prop.setProperty("AccessControl_pass_digit5", "4");
				prop.setProperty("AccessControl_pass_digit6", "5");
		 
				prop.store(output, null);
		 
			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	private boolean isPropertiesFileEmptyOrDoesntExist() {
		prop = new Properties();
		input = null;
		
		try {
			input = new FileInputStream(filename);
			prop.load(input);
			if (prop.getProperty("I_exist").equals("yes")) {
				return false;
			}
		} catch (FileNotFoundException e) {
			//file doesn't exist
			return true;
		} catch (IOException ex) {
			ex.printStackTrace();
			return true;
		}
		return true;
	}
	
	public synchronized String getProperty(String key) {
		prop = new Properties();
		input = null;
		String propertyRetrieved = null;
		while (propertyRetrieved == null) {
			try {
				input = new FileInputStream(filename);
				prop.load(input);
				propertyRetrieved = prop.getProperty(key);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return propertyRetrieved;
	}
	
}
