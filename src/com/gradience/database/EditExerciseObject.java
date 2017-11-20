package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.internal.OracleTypes;

public class EditExerciseObject {
	
	public ArrayList<String> execute(String c_id) {
		CallableStatement sttmnt = null;
		ArrayList<String> ret = new ArrayList<String>();
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_EXERCISE_OF_COURSE(?,?)}");
			sttmnt.setString("C_ID", c_id);
			sttmnt.registerOutParameter("exerciseOfCourseData",OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("exerciseOfCourseData");
			if(rs.isBeforeFirst()){
			while(rs.next()){
				ret.add(rs.getString("EXERCISE_ID"));
				ret.add(rs.getString("EXERCISE_NAME"));
				ret.add(rs.getString("TOTAL_QUESTION"));
				ret.add(rs.getString("START_TIME"));
				ret.add(rs.getString("END_TIME"));
				ret.add(rs.getString("RIGHT_PTS"));
				ret.add(rs.getString("WRONG_PTS"));
				ret.add(rs.getString("EXERCISE_TYPE"));
				ret.add(rs.getString("SCORING_POLICY"));
				ret.add(rs.getString("START_DIFFICULTY"));
				ret.add(rs.getString("END_DIFFICULTY"));
				
			}
			}
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
