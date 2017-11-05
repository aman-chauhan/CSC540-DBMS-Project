package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

public class AddQuestionObject {

	public HashMap<String, String> execute1(String text, String hint, String explain, int difficulty, int topic,
			String username) {
		HashMap<String, String> map = new HashMap<String, String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call ADD_QUESTION(?,?,?,?,?,?,?,?)}");
			sttmnt.setInt("T_ID", topic);
			sttmnt.setString("P_ID", username);
			sttmnt.setString("Q_TXT", text);
			sttmnt.setString("Q_HINT", hint);
			sttmnt.setString("Q_EXP", explain);
			sttmnt.setInt("Q_DIFF", difficulty);
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

	public HashMap<String, String> execute2(int qid, String string, String string2) {
		HashMap<String, String> map = new HashMap<String, String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call ADD_PARAMETER(?,?,?,?,?)}");
			sttmnt.setInt("Q_ID", qid);
			sttmnt.setString("PNAME", string);
			sttmnt.setString("PVALUE", string2);
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

	public HashMap<String, String> execute3(int qid, int pid, String otext, String otype, String oxplain) {
		HashMap<String, String> map = new HashMap<String, String>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call ADD_OPTION(?,?,?,?,?,?,?)}");
			sttmnt.setInt("Q_ID", qid);
			sttmnt.setInt("PRM_ID", pid);
			sttmnt.setString("O_TEXT", otext);
			sttmnt.setString("O_TYPE", otype);
			sttmnt.setString("O_EXPLANATION", oxplain);
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
