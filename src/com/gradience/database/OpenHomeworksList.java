package com.gradience.database;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.gradience.model.AHistory1;
import com.gradience.model.Exercise;

import oracle.jdbc.internal.OracleTypes;

public class OpenHomeworksList {

	public ArrayList<Exercise> execute(String course_id) {
		ArrayList<Exercise> list = new ArrayList<Exercise>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call LIVE_HOMEWORK(?,?,?,?)}");
			sttmnt.setString("C_ID", course_id);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("openExerciseData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				System.out.println(sttmnt.getString("TEXT"));
				ResultSet rs = (ResultSet) sttmnt.getObject("openExerciseData");
				if(rs.isBeforeFirst()){
				while (rs.next()) {
					Exercise e = new Exercise();
					e.setExercise_id(rs.getInt("EXERCISE_ID"));
					e.setExercise_name(rs.getString("EXERCISE_NAME"));
					e.setTotal_qs(rs.getInt("TOTAL_QUESTION"));
					e.setTotal_rt(rs.getInt("TOTAL_RETRIES"));
					e.setStart(rs.getDate("START_TIME"));
					e.setEnd(rs.getDate("END_TIME"));
					e.setRpoints(rs.getInt("RIGHT_PTS"));
					e.setWpoints(rs.getInt("WRONG_PTS"));
					e.setType(rs.getString("EXERCISE_TYPE"));
					e.setScoring(rs.getString("SCORING_POLICY"));
					e.setStart_diff(rs.getInt("START_DIFFICULTY"));
					e.setEnd_diff(rs.getInt("END_DIFFICULTY"));
					e.setTopic_id(rs.getInt("TOPIC_ID"));
					list.add(e);
				}
				}
			} else {
				System.out.println(sttmnt.getString("TEXT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<AHistory1> execute3(int exercise_id, String string) {
		ArrayList<AHistory1> list = new ArrayList<AHistory1>();
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call OPEN_EXERCISE_INFO(?,?,?,?,?)}");
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.setString("S_ID", string);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("attemptData", OracleTypes.CURSOR);
			sttmnt.execute();
			ResultSet rs = (ResultSet) sttmnt.getObject("attemptData");
			while (rs.next()) {
				AHistory1 a = new AHistory1();
				a.setAttempt_id(rs.getInt("ATTEMPT_ID"));
				a.setEndTime(rs.getTimestamp("END_TIME"));
				a.setScore(rs.getFloat("SCORE"));
				list.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int execute2(int exercise_id, String string) {
		CallableStatement sttmnt = null;
		try {
			sttmnt = DBConnection.instance().conn.prepareCall("{call OPEN_EXERCISE_INFO(?,?,?,?,?)}");
			sttmnt.setInt("EX_ID", exercise_id);
			sttmnt.setString("S_ID", string);
			sttmnt.registerOutParameter("MSG", Types.VARCHAR);
			sttmnt.registerOutParameter("TEXT", Types.VARCHAR);
			sttmnt.registerOutParameter("attemptData", OracleTypes.CURSOR);
			sttmnt.execute();
			if (sttmnt.getString("MSG").equals("success")) {
				return Integer.parseInt(sttmnt.getString("TEXT"));
			} else {
				return 0;
			}
		} catch (SQLException e) {
			return -1;
		}
	}

}
