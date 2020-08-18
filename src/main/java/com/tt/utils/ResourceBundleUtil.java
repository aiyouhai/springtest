package com.tt.utils;

import java.util.ResourceBundle;

public class ResourceBundleUtil {
	public static String getString(String propertiesName,String propertiesFild) {
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
		String string = bundle.getString(propertiesFild);
		return string;
	}
	
	public static int getInteger(String propertiesName,String propertiesFild) {
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
		String string = bundle.getString(propertiesFild);
		Integer intValue = Integer.valueOf(string);
		return intValue;
	}
	public static long getLong(String propertiesName,String propertiesFild) {
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
		String string = bundle.getString(propertiesFild);
		long parseLong = Long.parseLong(string);
		return parseLong;
	}
	public static Boolean getBoolean(String propertiesName,String propertiesFild) {
		ResourceBundle bundle = ResourceBundle.getBundle(propertiesName);
		String string = bundle.getString(propertiesFild);
		boolean parseBoolean = Boolean.parseBoolean(string);
		return parseBoolean;
	}
}
