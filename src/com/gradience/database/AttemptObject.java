package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Exercise;
import com.gradience.model.Options;

import oracle.jdbc.internal.OracleTypes;

public class AttemptObject {

	public ArrayList<String> fetchQList(int exercise_id) {
		ArrayList<String> qlist = new ArrayList<String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call STATIC_QUESTION_LIST(?,?,?,?)}");
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("questions", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("questions");
				if(rs.isBeforeFirst()){
				while (rs.next()) {
					qlist.add(Integer.toString(rs.getInt("QUESTION_ID")));
				}
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qlist;
	}

	public ArrayList<Options> fetchOptions(int parseInt) {
		ArrayList<Options> olist = new ArrayList<Options>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call STATIC_QUESTION_FETCH(?,?,?,?)}");
			sttmnt.setInt("Q_ID", parseInt);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("optionData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("optionData");
				while (rs.next()) {
					Options o = new Options();
					o.setQ_id(rs.getInt("QUESTION_ID"));
					if (rs.getString("PARAM_NAME").equals("default")) {
						o.setQ_text(rs.getString("QUESTION_TEXT"));
					} else {
						String temp = rs.getString("QUESTION_TEXT");
						String[] name = rs.getString("PARAM_NAME").split(":");
						String[] value = rs.getString("PARAM_VALUES").split(":");
						for (int i = 0; i < name.length; ++i) {
							temp = temp.replaceAll("\\<" + name[i] + ">", value[i]);
						}
						o.setQ_text(temp);
					}
					o.setOpt_id(rs.getInt("OPTION_ID"));
					o.setOpt_text(rs.getString("OPTION_TEXT"));
					o.setPrm_id(rs.getInt("PARAM_ID"));
					o.setSelected(false);
					olist.add(o);
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return olist;
	}

	public int fetchAttempt(int exercise_id, String string) {
		CallableStatement sttmnt = null;
		int id = 0;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call GET_STATIC_ATTEMPT(?,?,?,?)}");
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.setString("U_ID", string);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				id = Integer.parseInt(sttmnt.getString("TEXT"));
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void submitQuiz(int attempt_id, ArrayList<Options> question) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call FILL_GENERATED_EXERCISE(?,?,?,?,?,?,?,?,?,?,?)}");
			sttmnt.setInt("A_ID", attempt_id);
			sttmnt.setInt("Q_ID", question.get(0).getQ_id());
			sttmnt.setInt("PRM_ID", question.get(0).getPrm_id());
			sttmnt.setInt("O_ID1", question.get(0).getOpt_id());
			sttmnt.setInt("O_ID2", question.get(1).getOpt_id());
			sttmnt.setInt("O_ID3", question.get(2).getOpt_id());
			sttmnt.setInt("O_ID4", question.get(3).getOpt_id());
			if (question.get(0).isSelected())
				sttmnt.setString("S1", "T");
			else
				sttmnt.setString("S1", "F");
			if (question.get(1).isSelected())
				sttmnt.setString("S2", "T");
			else
				sttmnt.setString("S2", "F");
			if (question.get(2).isSelected())
				sttmnt.setString("S3", "T");
			else
				sttmnt.setString("S3", "F");
			if (question.get(3).isSelected())
				sttmnt.setString("S4", "T");
			else
				sttmnt.setString("S4", "F");
			sttmnt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int fetchDifficulty(int exercise_id) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call SEND_DYNAMIC_DIFFICULTY(?,?,?)}");
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				String[] temp = sttmnt.getString("TEXT").split(":");
				return (int) Math.floor((Integer.parseInt(temp[0]) + Integer.parseInt(temp[1])) / 2);
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}

	public ArrayList<Options> fetchDOptions(int exercise_id, int diff) {
		ArrayList<Options> olist = new ArrayList<Options>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call SEND_DYNAMIC_QUESTION(?,?,?)}");
			sttmnt.setInt("diff", diff);
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.registerOutParameter("questionData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("questionData");
			while (rs.next()) {
				Options o = new Options();
				o.setQ_id(rs.getInt("QUESTION_ID"));
				if (rs.getString("PARAM_NAME").equals("default")) {
					o.setQ_text(rs.getString("QUESTION_TEXT"));
				} else {
					String temp = rs.getString("QUESTION_TEXT");
					String[] name = rs.getString("PARAM_NAME").split(":");
					String[] value = rs.getString("PARAM_VALUES").split(":");
					for (int i = 0; i < name.length; ++i) {
						temp = temp.replaceAll("\\<" + name[i] + ">", value[i]);
					}
					o.setQ_text(temp);
				}
				o.setOpt_id(rs.getInt("OPTION_ID"));
				o.setOpt_text(rs.getString("OPTION_TEXT"));
				o.setPrm_id(rs.getInt("PARAM_ID"));
				o.setSelected(false);
				olist.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return olist;
	}

	public int submitQuestion(int diff, Exercise e, ArrayList<Options> opts) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call CHECK_DYNAMIC_ANSWER(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sttmnt.setInt("EX_ID", e.getExercise_id());
			sttmnt.setInt("Q_ID", opts.get(0).getQ_id());
			sttmnt.setInt("PRM_ID", opts.get(0).getPrm_id());
			sttmnt.setInt("O_ID1", opts.get(0).getOpt_id());
			sttmnt.setInt("O_ID2", opts.get(1).getOpt_id());
			sttmnt.setInt("O_ID3", opts.get(2).getOpt_id());
			sttmnt.setInt("O_ID4", opts.get(3).getOpt_id());
			if (opts.get(0).isSelected())
				sttmnt.setString("S1", "T");
			else
				sttmnt.setString("S1", "F");
			if (opts.get(1).isSelected())
				sttmnt.setString("S2", "T");
			else
				sttmnt.setString("S2", "F");
			if (opts.get(2).isSelected())
				sttmnt.setString("S3", "T");
			else
				sttmnt.setString("S3", "F");
			if (opts.get(3).isSelected())
				sttmnt.setString("S4", "T");
			else
				sttmnt.setString("S4", "F");
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				String[] temp = sttmnt.getString("TEXT").split(":");
				for (int i = 0; i < temp.length; ++i) {
					opts.get(i).setScore(Float.parseFloat(temp[i]));
				}
				if (diff < e.getEnd_diff()) {
					diff += 1;
				}
			} else {
				String[] temp = sttmnt.getString("TEXT").split(":");
				for (int i = 0; i < temp.length; ++i) {
					opts.get(i).setScore(Float.parseFloat(temp[i]));
				}
				if (diff > e.getStart_diff()) {
					diff -= 1;
				}
			}
			return diff;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return diff;
		}
	}

	public ArrayList<Options> fetchAttemptOptions(int attempt_id) {
		ArrayList<Options> olist = new ArrayList<Options>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call DISPLAY_ATTEMPTED_QUESTIONS(?,?)}");
			sttmnt.setInt("ATTMPTID", attempt_id);
			sttmnt.registerOutParameter("questionInfo", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("questionInfo");
			while (rs.next()) {
				Options o = new Options();
				o.setQ_id(rs.getInt("QUESTION_ID"));
				o.setQ_expl(rs.getString("EXPLANATION"));
				o.setQ_hint(rs.getString("HINT"));
				o.setPrm_id(rs.getInt("PARAM_ID"));
				if (rs.getString("PARAM_NAME").equals("default")) {
					o.setQ_text(rs.getString("QUESTION_TEXT"));
				} else {
					String temp = rs.getString("QUESTION_TEXT");
					String[] name = rs.getString("PARAM_NAME").split(":");
					String[] value = rs.getString("PARAM_VALUES").split(":");
					for (int i = 0; i < name.length; ++i) {
						temp = temp.replaceAll("\\<" + name[i] + ">", value[i]);
					}
					o.setQ_text(temp);
				}
				o.setSelected(rs.getString("SELECTED").equals("T"));
				o.setScore(rs.getFloat("SCORE"));
				o.setOpt_id(rs.getInt("OPTION_ID"));
				o.setOpt_text(rs.getString("OPTION_TEXT"));
				o.setOpt_type(rs.getString("OPTION_TYPE"));
				o.setOpt_expl(rs.getString("OEXPLANATION"));
				olist.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return olist;
	}

}
