package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class CreateTopicObject {
	
	public HashMap<String, String> execute(String p_id, String t_name) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt=null;
		try {
			sttmnt=DBConnection.instance().conn.prepareCall("{call LOG_IN(?,?,?,?)}");
			sttmnt.setString("P_ID", p_id);
			sttmnt.setString("T_NAME", t_name);
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
