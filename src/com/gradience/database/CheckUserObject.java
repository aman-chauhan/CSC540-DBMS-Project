package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class CheckUserObject {

	public HashMap<String, String> execute(String username) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt=null;
		try {
			sttmnt=DBConnection.instance().conn.prepareCall("{call CHECK_USER_ID(?,?,?,?)}");
			sttmnt.setString("U_ID", username);
			sttmnt.registerOutParameter("IFEXIST", Types.VARCHAR);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.execute();
			map.put("IFEXIST", sttmnt.getString("IFEXIST"));
			map.put("MSG", sttmnt.getString("MSG"));
			map.put("TEXT", sttmnt.getString("TEXT"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
