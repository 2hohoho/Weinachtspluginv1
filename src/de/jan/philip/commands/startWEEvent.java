package de.jan.philip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.jan.philip.main;

public class startWEEvent implements CommandExecutor 
{
	
	private main plugin;
	
	public startWEEvent(main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.isOp())
			plugin.startWeinachtsEvent();
		return false;
	}

}
