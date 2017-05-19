package com.tpg;


public class StudentInfo {
	public int id;
	public String name;
	public double gpa;
	
	public StudentInfo(int id, String name, double gpa) {
		this.id = id;
		this.name = name;
		this.gpa = gpa;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getGpa() {
		return gpa;
	}
	
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
}
