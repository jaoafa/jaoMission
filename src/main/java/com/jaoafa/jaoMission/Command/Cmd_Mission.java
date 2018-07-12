package com.jaoafa.jaoMission.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.jaoafa.jaoMission.JaoMission;

public class Cmd_Mission implements CommandExecutor {
	JavaPlugin plugin;
	public Cmd_Mission(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			JaoMission.SendMessage(sender, cmd, "このコマンドはプレイヤーからのみ実行できます。");
			return true;
		}




		return true;
	}
}
