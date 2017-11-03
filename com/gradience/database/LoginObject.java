package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class LoginObject {

	public HashMap<String, String> execute(String user, String password) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt=null;
		try {
			sttmnt=DBConnection.instance().conn.prepareCall("{call LOG_IN(?,?,?,?)}");
			sttmnt.setString("X", user);
			sttmnt.setString("Y", password);
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
