package com.gradience.model;

import java.sql.Timestamp;

public class AHistory1 {
	int attempt_id;
	Timestamp endTime;
	float score;

	public int getAttempt_id() {
		return attempt_id;
	}

	public void setAttempt_id(int attempt_id) {
		this.attempt_id = attempt_id;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
