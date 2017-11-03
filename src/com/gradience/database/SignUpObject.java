package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class SignUpObject {
	
	public HashMap<String, String> execute(String first_name, String last_name, String user_id, String pass_word, String college, String utype, Date extra) {
		HashMap<String,String> map=new HashMap<String,String>();
		CallableStatement sttmnt=null;
		try {
			sttmnt=DBConnection.instance().conn.prepareCall("{call SIGN_UP(?,?,?,?,?,?,?,?,?)}");
			sttmnt.setString("FIRST_NAME", first_name);
			sttmnt.setString("LAST_NAME", last_name);
			sttmnt.setString("USER_ID", user_id);
			sttmnt.setString("PASS_WORD", pass_word);
			sttmnt.setString("COLLEGE", college);
			sttmnt.setString("UTYPE", utype);
			sttmnt.setDate("EXTRA", extra);
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
