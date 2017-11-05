package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.Topic;

import oracle.jdbc.internal.OracleTypes;

public class CourseTopicObject {

	public ArrayList<Topic> execute(String course_id) {
		ArrayList<Topic> ret = new ArrayList<Topic>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call VIEW_TOPICS(?,?,?,?)}");
			sttmnt.setString("C_ID", course_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("topicData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("topicData");
			while (rs.next()) {
				Topic t = new Topic();
				t.setTopic_id(rs.getInt("TOPIC_ID"));
				t.setTopic_name(rs.getString("TOPIC_NAME"));
				ret.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
