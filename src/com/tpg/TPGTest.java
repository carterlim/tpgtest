package com.tpg;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TPGTest {
	public static void Question1() {
		try {
			File file = new File("C:\\ipv4.txt");
			List<String> ips = IPValidationRegex.filterInvalidIPs(file);
			
			System.out.println("\nQuestion 1: ");
			for (String ip : ips)
				System.out.println(ip);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	public static void Question2() {
		List<StudentInfo> studentInfoList = new ArrayList<StudentInfo>();
		StudentInfo studentInfo = null;
		try {
			studentInfo = new StudentInfo(33, "Tina", 3.68);
			studentInfoList.add(studentInfo);

			studentInfo = new StudentInfo(85, "Louis", 3.85);
			studentInfoList.add(studentInfo);
			
			studentInfo = new StudentInfo(56, "Samil", 3.75);
			studentInfoList.add(studentInfo);
			
			studentInfo = new StudentInfo(19, "Samar", 3.75);
			studentInfoList.add(studentInfo);
			
			studentInfo = new StudentInfo(22, "Lorry", 3.76);
			studentInfoList.add(studentInfo);

			String[] fields = new String[] { "gpa", "name", "id" };
			boolean[] orderTyps = new boolean[] { false, true, true };
			BeanComparator beanComparator = new BeanComparator(fields, orderTyps);
			Collections.sort(studentInfoList, beanComparator);
			
			System.out.println("\nQuestion 2: ");
			for (StudentInfo studInfo : studentInfoList)
				System.out.println(studInfo.getId() + ": " + studInfo.getName() + " (" + studInfo.getGpa() + ")");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			System.out.println("\nQuestion 1 executed: " + TimingMeasureTool.measure("com.tpg.TPGTest", "Question1", new Class[0]));
			System.out.println("\nQuestion 2 executed: " + TimingMeasureTool.measure("com.tpg.TPGTest", "Question2", new Class[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
