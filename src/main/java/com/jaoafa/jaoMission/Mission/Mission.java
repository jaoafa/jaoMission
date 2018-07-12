package com.jaoafa.jaoMission.Mission;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import com.jaoafa.jaoMission.JaoMission;
import com.jaoafa.jaoMission.Lib.Discord;
import com.jaoafa.jaoMission.Lib.MySQL;
import com.jaoafa.jaoSuperAchievement.AchievementAPI.jaoSuperAchievementEvent;

public class Mission {
	private int id;
	private String name;
	private String description;
	private MissionType type;
	private int value;
	private int count;
	private String version;
	public Mission(int id){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoMission_Type WHERE id = ?");
			statement.setInt(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				id = res.getInt("id");
				name = res.getString("name");
				description = res.getString("description");
				try{
					type = MissionType.valueOf(res.getString("type").toUpperCase());
				}catch(IllegalArgumentException e){
					throw new IllegalArgumentException("「" + res.getString("type") + "」というミッションタイプは見つかりません。");
				}
				value = res.getInt("value");
				count = res.getInt("count");
				version = res.getString("version");
			}else{
				throw new IllegalArgumentException("指定されたIDのjaoMissionは見つかりません。");
			}
		} catch (SQLException | ClassNotFoundException e) {
			JaoMission.BugReporter(e);
			throw new IllegalArgumentException("ミッション取得失敗");
		}
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public MissionType getType(){
		return type;
	}
	public int getValue(){
		return value;
	}
	public int getCount(){
		return count;
	}
	public String getVersion(){
		return version;
	}

// ------------------------------------------------------------------------------------------------------------- //

	public boolean isClearMission(OfflinePlayer player, MissionPeriod period){
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("SELECT * FROM jaoMission_Mission WHERE missionid = ? AND periodid = ?");
			statement.setInt(1, id);
			statement.setInt(2, period.getID());
			ResultSet res = statement.executeQuery();
			if(res.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			JaoMission.BugReporter(e);
			return false;
		}
	}
	public void Clear(OfflinePlayer player, MissionPeriod period){
		if(isClearMission(player, period)){
			return;
		}
		try {
			PreparedStatement statement = MySQL.getNewPreparedStatement("INSERT INTO jaoMission (player, uuid, periodid, type) VALUES (?, ?, ?, ?);");
			statement.setString(1, player.getName());
			statement.setString(2, player.getUniqueId().toString());
			statement.setInt(3, period.getID());
			statement.setString(4, period.getType().name().toLowerCase());
			statement.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			JaoMission.BugReporter(e);
		}
		Bukkit.broadcastMessage(JaoMission.getPrefix() + player.getName() + "がミッション「" + period.getMission().getName() + "」をクリアしました！");
		Discord.send("**[jaoMission]** " + player.getName() + "がミッション「" + period.getMission().getName() + "」をクリアしました！");

		jaoSuperAchievementEvent jaoSuperAchievementEvent = new jaoSuperAchievementEvent(offplayer, type);
		Bukkit.getServer().getPluginManager().callEvent(jaoSuperAchievementEvent);
	}
}
