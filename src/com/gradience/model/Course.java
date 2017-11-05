package com.gradience.model;

import java.sql.Date;

public class Course {
	String course_id;
	String course_name;
	String stream;
	Date start_date;
	Date end_date;
	int max_students;

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public int getMax_students() {
		return max_students;
	}

	public void setMax_students(int max_students) {
		this.max_students = max_students;
	}

}
