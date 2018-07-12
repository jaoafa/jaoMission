package com.jaoafa.jaoMission.MissionEvent;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.jaoafa.jaoMission.Mission.MissionPeriod;

public class DailyMissionClearEvent extends Event {
	private OfflinePlayer offplayer;
    private MissionPeriod period;
    private static final HandlerList handlers = new HandlerList();

    public DailyMissionClearEvent(OfflinePlayer player, MissionPeriod period){
        this.offplayer = player;
        this.period = period;
    }

    public OfflinePlayer getPlayer(){
    	return offplayer;
    }

    public MissionPeriod getPeriod(){
    	return period;
    }

	@Override
	public HandlerList getHandlers() {
		// TODO 自動生成されたメソッド・スタブ
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
