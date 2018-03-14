/**
 * 
 */
package com.eg.test.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Anju
 *
 */
public class CalendarUtil {

	/**
	 * 
	 * @return
	 */
	public static String getCurrentDateMinus7(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(System.currentTimeMillis() -  7 * 24 * 3600 * 1000);
		return dateFormat.format(date);
	}

	public static void main(String[] args) {
		System.out.println(getCurrentDateMinus7());
	}
}
