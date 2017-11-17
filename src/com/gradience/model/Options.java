package com.gradience.model;

public class Options {
	int q_id;
	String q_text;
	int prm_id;
	String prm_name;
	String prm_values;
	int opt_id;
	String opt_text;
	float score;
	boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getQ_id() {
		return q_id;
	}

	public void setQ_id(int q_id) {
		this.q_id = q_id;
	}

	public String getQ_text() {
		return q_text;
	}

	public void setQ_text(String q_text) {
		this.q_text = q_text;
	}

	public int getPrm_id() {
		return prm_id;
	}

	public void setPrm_id(int prm_id) {
		this.prm_id = prm_id;
	}

	public String getPrm_name() {
		return prm_name;
	}

	public void setPrm_name(String prm_name) {
		this.prm_name = prm_name;
	}

	public String getPrm_values() {
		return prm_values;
	}

	public void setPrm_values(String prm_values) {
		this.prm_values = prm_values;
	}

	public int getOpt_id() {
		return opt_id;
	}

	public void setOpt_id(int opt_id) {
		this.opt_id = opt_id;
	}

	public String getOpt_text() {
		return opt_text;
	}

	public void setOpt_text(String opt_text) {
		this.opt_text = opt_text;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}
