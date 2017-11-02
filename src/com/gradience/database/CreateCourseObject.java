package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class CreateCourseObject {
	
	public HashMap<String, String> execute(String p_id, String c_id, String c_name, String strm, Date strt_dt, Date end_dt, int mx_stdnts ) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt=null;
		try {
			sttmnt=DBConnection.instance().conn.prepareCall("{call LOG_IN(?,?,?,?)}");
			sttmnt.setString("P_ID", p_id);
			sttmnt.setString("C_ID", c_id);
			sttmnt.setString("C_NAME", c_name);
			sttmnt.setString("STRM", strm);
			sttmnt.setDate("STRT_DT", strt_dt);
			sttmnt.setDate("END_DT", end_dt);
			sttmnt.setInt("MX_STDNTS", mx_stdnts);
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
