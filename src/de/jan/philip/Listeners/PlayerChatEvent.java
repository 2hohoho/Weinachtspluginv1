package de.jan.philip.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		e.setCancelled(true);
		String message = e.getMessage();
		Bukkit.getOnlinePlayers().forEach(d -> {
			d.sendMessage("§7" + e.getPlayer().getName() + " §8>>> §r" + message);
		});
	}

}
