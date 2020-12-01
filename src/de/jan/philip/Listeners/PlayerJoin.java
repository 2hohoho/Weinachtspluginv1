package de.jan.philip.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.jan.philip.main;

public class PlayerJoin implements Listener {
	
	private main plugin;
	public PlayerJoin(main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		p.setResourcePack("https://uc44e4655c6ee2853692d86d41e5.dl.dropboxusercontent.com/cd/0/get/BEKFx9m8v7E3ELz24p12BYI7MdxCZmBA_lD3zzuq8qpar9-9B8DjukD6GjKZOHNYhiBDitp8aWKITxF4lqLVBC4lQzNqDftiyUXgafmnJqFEyeC0mjHhU6Q-m-9O2_m3SCs/file#");
		File file = new File("plugins//Weinachtsgeschenk//" + p.getUniqueId().toString() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		if(!file.exists())
		{
			DecimalFormat df = new DecimalFormat("00");
			for(int i = 1; i < 25; i++)
			{
				int random = (int)(Math.random()*(plugin.presents.size()));
				String m = plugin.presents.get(random);
				yml.set(""+df.format(i), m);
			}
			try {
				yml.save(file);
			} catch (Exception e2) {}
		}
		try {
			yml.load(file);
		} catch (IOException | InvalidConfigurationException e1) {
			e1.printStackTrace();
		}
		
		
		List<String> list = yml.getStringList("oldItems");
		
		if(Integer.valueOf(plugin.printSimpleDateFormat()) < 32 && Integer.valueOf(plugin.printSimpleDateFormat()) > 24)
		{
			
		} else {
			if(!list.contains(plugin.printSimpleDateFormat()))
			{
				p.sendMessage("§8----------------------");
				p.sendMessage("§6Du kannst dein heutiges Geschenk noch abholen!");
				p.sendMessage("§8----------------------");
			}
		}
		
		
	}

}
