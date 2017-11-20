package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Topic;

import oracle.jdbc.internal.OracleTypes;

public class SearchTopicList {

	public ArrayList<Topic> execute(String temp) {
		ArrayList<Topic> ret = new ArrayList<Topic>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call SEARCH_TOPIC(?,?,?,?)}");
			sttmnt.setString("T_NM", temp);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("topicsData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("topicsData");
				if(rs.isBeforeFirst()){
				while (rs.next()) {
					Topic t = new Topic();
					t.setTopic_id(rs.getInt("TOPIC_ID"));
					t.setTopic_name(rs.getString("TOPIC_NAME"));
					ret.add(t);
				}
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
