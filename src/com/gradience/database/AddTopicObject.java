package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class AddTopicObject {

	public HashMap<String, String> execute(String userID, String topicName) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call CREATE_TOPIC(?,?,?,?)}");
			sttmnt.setString("P_ID",userID);
			sttmnt.setString("T_NAME",topicName);
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
