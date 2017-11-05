package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Question;

import oracle.jdbc.internal.OracleTypes;

public class AddExerciseObject {

	public int execute(String p_id, String e_name, int ttl_qs, int ttl_retries, Date start, Date end, float right,
			float wrong, String type, String policy, int strtdiff, int enddiff, int topic_id) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call CREATE_EXERCISE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			sttmnt.setString("P_ID", p_id);
			sttmnt.setString("E_NAME", e_name);
			sttmnt.setInt("TTL_QS", ttl_qs);
			sttmnt.setInt("TTL_RETRIES", ttl_retries);
			sttmnt.setDate("STRT_TIME", start);
			sttmnt.setDate("ND_TIME", end);	
			sttmnt.setFloat("RT_PTS", right);
			sttmnt.setFloat("WNG_PTS", wrong);
			sttmnt.setString("E_TYPE", type);
			sttmnt.setString("SCORE", policy);
			sttmnt.setInt("STRT_DIFFICULTY", strtdiff);
			sttmnt.setInt("ND_DIFFICULTY", enddiff);
			sttmnt.setInt("TPC_ID", topic_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				return Integer.parseInt(sttmnt.getString("TEXT"));
			} else {
				System.out.println(sttmnt.getString("TEXT"));
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<Question> execute2(int ex_id) {
		ArrayList<Question> list=new ArrayList<Question>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call DISPLAY_QUESTION_FOR_EXERCISE(?,?)}");
			sttmnt.setInt("EX_ID", ex_id);
			sttmnt.registerOutParameter("questionData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("questionData");
			while (rs.next()) {
				Question q = new Question();
				q.setQuestion_id(rs.getInt("QUESTION_ID"));
				q.setQuestion_text(rs.getString("QUESTION_TEXT"));
				list.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public void execute3(int ex_id, String q_id) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call ADD_QUESTION_TO_EXERCISE(?,?,?,?)}");
			sttmnt.setInt("Q_ID", Integer.parseInt(q_id));
			sttmnt.setInt("EX_ID", ex_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			if(sttmnt.getString("MSG").equals("success")) {
				System.out.println(q_id+" added successfully to exercise");
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
