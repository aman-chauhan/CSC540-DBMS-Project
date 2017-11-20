package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gradience.model.User;

import oracle.jdbc.internal.OracleTypes;

public class CourseTAList {

	public ArrayList<User> execute(String course_id) {
		ArrayList<User> ret = new ArrayList<User>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_TA(?,?)}");
			sttmnt.setString("C_ID", course_id);
			sttmnt.registerOutParameter("taData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("taData");
			if(rs.isBeforeFirst()){
			while (rs.next()) {
				User u=new User();
				u.setUser_id(rs.getString("STUDENT_ID"));
				u.setFname(rs.getString("FIRST_NAME"));
				u.setLname(rs.getString("LAST_NAME"));
				ret.add(u);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
