package com.gradience.model;

import java.sql.Date;

public class Exercise {
	int exercise_id;
	int topic_id;
	String exercise_name;
	int total_qs;
	int total_rt;
	Date start;
	Date end;
	int rpoints;
	int wpoints;
	String type;
	String scoring;
	int start_diff;
	int end_diff;
	
	@Override
	public String toString() {
		return exercise_name + ", " + total_qs + " questions, " + (total_rt+1)
				+ " attempts, start " + start + ", end " + end + ", Points-(" + rpoints + "," + wpoints + "), "
				+ type + ", " + scoring;
	}

	public int getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(int exercise_id) {
		this.exercise_id = exercise_id;
	}

	public int getTopic_id() {
		return topic_id;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}

	public String getExercise_name() {
		return exercise_name;
	}

	public void setExercise_name(String exercise_name) {
		this.exercise_name = exercise_name;
	}

	public int getTotal_qs() {
		return total_qs;
	}

	public void setTotal_qs(int total_qs) {
		this.total_qs = total_qs;
	}

	public int getTotal_rt() {
		return total_rt;
	}

	public void setTotal_rt(int total_rt) {
		this.total_rt = total_rt;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getRpoints() {
		return rpoints;
	}

	public void setRpoints(int rpoints) {
		this.rpoints = rpoints;
	}

	public int getWpoints() {
		return wpoints;
	}

	public void setWpoints(int wpoints) {
		this.wpoints = wpoints;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScoring() {
		return scoring;
	}

	public void setScoring(String scoring) {
		this.scoring = scoring;
	}

	public int getStart_diff() {
		return start_diff;
	}

	public void setStart_diff(int start_diff) {
		this.start_diff = start_diff;
	}

	public int getEnd_diff() {
		return end_diff;
	}

	public void setEnd_diff(int end_diff) {
		this.end_diff = end_diff;
	}

}
