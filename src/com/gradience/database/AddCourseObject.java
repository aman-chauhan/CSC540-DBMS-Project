package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import com.gradience.model.Course;

public class AddCourseObject {

	public HashMap<String, String> execute(HashMap<String, String> session, Course c) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call CREATE_COURSE(?,?,?,?,?,?,?,?,?)}");
			sttmnt.setString("P_ID",session.get("username"));
			sttmnt.setString("C_ID",c.getCourse_id());
			sttmnt.setString("C_NAME",c.getCourse_name());
			sttmnt.setString("STRM",c.getStream());
			sttmnt.setDate("STRT_DT",c.getStart_date());
			sttmnt.setDate("END_DT",c.getEnd_date());
			sttmnt.setInt("MX_STDNTS",c.getMax_students());
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			map.put("MSG", sttmnt.getString("MSG"));
			map.put("TEXT", sttmnt.getString("TEXT"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

}
