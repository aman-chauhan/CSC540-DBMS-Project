package com.gradience.model;

public class Record {
	String userid;
	String fname;
	String lname;
	int exerciseid;
	String exercisename;
	float marks;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getExerciseid() {
		return exerciseid;
	}

	public void setExerciseid(int exerciseid) {
		this.exerciseid = exerciseid;
	}

	public String getExercisename() {
		return exercisename;
	}

	public void setExercisename(String exercisename) {
		this.exercisename = exercisename;
	}

	public float getMarks() {
		return marks;
	}

	public void setMarks(float marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return userid + "\t" + fname + "\t" + lname + "\t" + exercisename + "\t" + marks;
	}

}
