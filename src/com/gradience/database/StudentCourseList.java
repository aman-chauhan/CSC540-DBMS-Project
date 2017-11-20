package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Course;

import oracle.jdbc.internal.OracleTypes;

public class StudentCourseList {

	public ArrayList<Course> execute(String string) {
		ArrayList<Course> list=new ArrayList<Course>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_COURSES_STUDENT(?,?,?,?)}");
			sttmnt.setString("S_ID", string);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("courseData", OracleTypes.CURSOR);
			sttmnt.execute();
			if(sttmnt.getString("MSG").equals("success")) {
				System.out.println("Courses found.");
				ResultSet rs = (ResultSet) sttmnt.getObject("courseData");
				if(rs.isBeforeFirst()){
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
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
