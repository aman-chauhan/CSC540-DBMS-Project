package com.gradience.model;

public class Options {
	int q_id;
	int prm_id;
	int opt_id;
	boolean selected;
	float score;
	String q_text;
	String q_hint;
	String q_expl;
	String prm_name;
	String prm_values;
	String opt_text;
	String opt_type;
	String opt_expl;
	
	@Override
	public String toString() {
		return "Options [q_id=" + q_id + ", prm_id=" + prm_id + ", opt_id=" + opt_id + ", selected=" + selected
				+ ", score=" + score + ", q_text=" + q_text + ", q_hint=" + q_hint + ", q_expl=" + q_expl
				+ ", prm_name=" + prm_name + ", prm_values=" + prm_values + ", opt_text=" + opt_text + ", opt_type="
				+ opt_type + ", opt_expl=" + opt_expl + "]";
	}

	public String getQ_hint() {
		return q_hint;
	}

	public void setQ_hint(String q_hint) {
		this.q_hint = q_hint;
	}

	public String getQ_expl() {
		return q_expl;
	}

	public void setQ_expl(String q_expl) {
		this.q_expl = q_expl;
	}

	public String getOpt_type() {
		return opt_type;
	}

	public void setOpt_type(String opt_type) {
		this.opt_type = opt_type;
	}

	public String getOpt_expl() {
		return opt_expl;
	}

	public void setOpt_expl(String opt_expl) {
		this.opt_expl = opt_expl;
	}

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
