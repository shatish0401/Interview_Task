package com.Interview.BaseClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Rough {
	
	public static void main(String[] args) {
		SimpleDateFormat sim =new SimpleDateFormat("d");
		Date date = new Date();
		String today = sim.format(date);
		int today_Date = Integer.parseInt(today);
		int j = today_Date+2;
		String xpath = "//div[text()='"+j+"']";
		System.out.println(xpath);
	}

}
