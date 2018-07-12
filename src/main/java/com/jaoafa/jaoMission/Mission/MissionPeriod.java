package com.jaoafa.jaoMission.Mission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import com.jaoafa.jaoMission.JaoMission;
import com.jaoafa.jaoMission.Lib.DateUtil;
import com.jaoafa.jaoMission.Lib.MySQL;

public class MissionPeriod {
	private int id;
	private int missionid;
	private MissionType type;
	private int start;
	private int end;

	public MissionPeriod(int id){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoMission_Mission WHERE id = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				id = res.getInt("id");
				missionid = res.getInt("missionid");
				try{
					type = MissionType.valueOf(res.getString("type").toUpperCase());
				}catch(IllegalArgumentException e){
					throw new IllegalArgumentException("「" + res.getString("type") + "」というミッションタイプは見つかりません。");
				}
				start = res.getInt("start");
				end = res.getInt("end");
			}else{
				throw new IllegalArgumentException("指定されたIDのMissionPeriodは見つかりません。");
			}
		} catch (SQLException | ClassNotFoundException e) {
			JaoMission.BugReporter(e);
			throw new IllegalArgumentException("ミッション取得失敗");
		}
	}
	public static MissionPeriod Create(Mission mission, int start, int end){
		int id = -1;
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("INSERT INTO jaoMission_Mission (missionid, type, start, end) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, mission.getID());
			statement.setString(2, mission.getType().name().toLowerCase());
			statement.setInt(3, start);
			statement.setInt(4, end);
			ResultSet res = statement.getGeneratedKeys();
			if(res != null && res.next()){
				id = res.getInt(1);
			}else{
				throw new IllegalStateException();
			}
		} catch (SQLException | ClassNotFoundException e) {
			JaoMission.BugReporter(e);
			throw new IllegalArgumentException("ミッション取得失敗");
		}
		return new MissionPeriod(id);
	}
	public static MissionPeriod CreateDaily(Mission mission){
		// その週の日曜から土曜まで。
		Calendar start = DateUtil.getDay_FIRST(new Date());
		Calendar end = DateUtil.getDay_END(new Date());
		return Create(mission, (int) (start.getTimeInMillis() / 1000), (int) (end.getTimeInMillis() / 1000));
	}
	public static MissionPeriod CreateWeekly(Mission mission){
		// その週の日曜から土曜まで。
		Calendar start = DateUtil.getDayOfWeek_FIRST(new Date(), Calendar.SUNDAY, Calendar.SUNDAY);
		Calendar end = DateUtil.getDayOfWeek_END(new Date(), Calendar.SATURDAY, Calendar.SATURDAY);
		return Create(mission, (int) (start.getTimeInMillis() / 1000), (int) (end.getTimeInMillis() / 1000));
	}
	public int getID(){
		return id;
	}
	public int getMissionID(){
		return missionid;
	}
	public Mission getMission(){
		return new Mission(missionid);
	}
	public MissionType getType(){
		return type;
	}
	public int getStart(){
		return start;
	}
	public int getEnd(){
		return end;
	}
}
