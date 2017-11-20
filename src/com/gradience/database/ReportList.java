package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gradience.model.Record;

import oracle.jdbc.internal.OracleTypes;

public class ReportList {

	public ArrayList<Record> execute(String course_id) {
		ArrayList<Record> ret = new ArrayList<Record>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call COURSE_REPORT(?,?)}");
			sttmnt.setString("COURSEID", course_id);
			sttmnt.registerOutParameter("REPORTDATA", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("REPORTDATA");
			if(rs.isBeforeFirst()){
			while (rs.next()) {
				Record r = new Record();
				r.setUserid(rs.getString("USER_ID"));
				r.setFname(rs.getString("FIRST_NAME"));
				r.setLname(rs.getString("LAST_NAME"));
				r.setExerciseid(rs.getInt("EXERCISE_ID"));
				r.setExercisename(rs.getString("EXERCISE_NAME"));
				r.setMarks(rs.getFloat("MARKS"));
				ret.add(r);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public ArrayList<Record> execute2(String course_id, String id) {
		ArrayList<Record> ret = new ArrayList<Record>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call COURSE_STUDENT_REPORT(?,?,?)}");
			sttmnt.setString("COURSEID", course_id);
			sttmnt.setString("STUDENTID", id);
			sttmnt.registerOutParameter("REPORTDATA", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("REPORTDATA");
			while (rs.next()) {
				Record r = new Record();
				r.setUserid(rs.getString("USER_ID"));
				r.setFname(rs.getString("FIRST_NAME"));
				r.setLname(rs.getString("LAST_NAME"));
				r.setExerciseid(rs.getInt("EXERCISE_ID"));
				r.setExercisename(rs.getString("EXERCISE_NAME"));
				r.setMarks(rs.getFloat("MARKS"));
				ret.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
