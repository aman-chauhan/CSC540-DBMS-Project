package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Question;

import oracle.jdbc.internal.OracleTypes;

public class SearchQuestionList {

	public ArrayList<Question> execute_topic(int parseInt) {
		ArrayList<Question> ret = new ArrayList<Question>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call SEARCH_BY_TOPIC_ID(?,?,?,?)}");
			sttmnt.setInt("T_ID", parseInt);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("questionData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("questionData");
				if(rs.isBeforeFirst()){
				while (rs.next()) {
					Question q = new Question();
					q.setQuestion_id(rs.getInt("QUESTION_ID"));
					q.setQuestion_text(rs.getString("QUESTION_TEXT"));
					q.setHint(rs.getString("HINT"));
					q.setExplanation(rs.getString("EXPLANATION"));
					q.setDifficulty(rs.getInt("DIFFICULTY"));
					ret.add(q);
				}
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public ArrayList<Question> execute_question(int parseInt) {
		ArrayList<Question> ret = new ArrayList<Question>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call SEARCH_BY_QUESTION_ID(?,?,?,?)}");
			sttmnt.setInt("Q_ID", parseInt);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("questionData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("questionData");
				while (rs.next()) {
					Question q = new Question();
					q.setQuestion_id(rs.getInt("QUESTION_ID"));
					q.setQuestion_text(rs.getString("QUESTION_TEXT"));
					q.setHint(rs.getString("HINT"));
					q.setExplanation(rs.getString("EXPLANATION"));
					q.setDifficulty(rs.getInt("DIFFICULTY"));
					ret.add(q);
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
