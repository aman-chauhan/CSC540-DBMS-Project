package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

public class ViewExerciseQuestionObject {
	
	public ArrayList<String> execute(int ex_id) {
		CallableStatement sttmnt = null;
		ArrayList<String> ret = new ArrayList<String>();
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_EXERCISE_QUESTION(?,?)}");
			sttmnt.setInt("EX_ID", ex_id);
			sttmnt.registerOutParameter("exInfo",OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("exInfo");
			if(rs.isBeforeFirst()){
			while(rs.next()){
				ret.add(rs.getString("QUESTION_TEXT"));
				//ret.add(rs.getString("OPTION_TEXT"));
			}
			}
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
