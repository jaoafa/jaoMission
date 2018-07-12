package com.jaoafa.jaoMission.Mission;

public enum MissionType {
	DAILY("デイリーミッション"),
	WEEKLY("ウィークリーミッション");

    private String text;
    private MissionType(String text){
        this.text = text;
    }
    public String getText(){
    	return text;
    }
}
