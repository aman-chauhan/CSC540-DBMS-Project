package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

import com.gradience.database.DBConnection;
import com.gradience.model.Course;

import oracle.jdbc.internal.OracleTypes;

public class TeacherCourseList {

	public ArrayList<Course> execute(String string) {
		ArrayList<Course> list = new ArrayList<Course>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_TEACHER_COURSE(?,?)}");
			sttmnt.setString("P_ID", string);
			sttmnt.registerOutParameter("courseData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("courseData");
			while (rs.next()) {
				Course c = new Course();
				c.setCourse_id(rs.getString("COURSE_ID"));
				c.setCourse_name(rs.getString("COURSE_NAME"));
				c.setStream(rs.getString("STREAM"));
				c.setStart_date(rs.getDate("START_DATE"));
				c.setEnd_date(rs.getDate("END_DATE"));
				c.setMax_students(rs.getInt("MAX_STUDENTS"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
