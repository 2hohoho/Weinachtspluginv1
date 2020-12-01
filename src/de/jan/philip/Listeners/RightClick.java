package de.jan.philip.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.jan.philip.main;

public class RightClick implements Listener{

	
	private main plugin;
	public RightClick(main plugin) {
		this.plugin = plugin;
	}
	public static GameProfile getNonPlayerProfile(String skinURL) {
	    GameProfile newSkinProfile = new GameProfile(UUID.randomUUID(), null);
	    newSkinProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
	    return newSkinProfile;
	}
	@EventHandler
	public void rightclick(PlayerInteractEvent e)
	{
		Player p = e.getPlayer();

		if(e.getClickedBlock() == null || e.getClickedBlock().getType() == Material.AIR)
			return;
		rightclickKalender(e, p);
		rightclickPresent(e, p);
	}
	
	private void rightclickPresent(PlayerInteractEvent e, Player p)
	{
		if(e.getClickedBlock().getType().equals(Material.BONE_BLOCK))
		{
			e.getClickedBlock().setType(Material.AIR);
			p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
			p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BLOCK, 1, 1);
			
			int random = (int)(Math.random()*(plugin.christmasPresent.size()));
			Bukkit.getWorld(p.getLocation().getWorld().getName()).dropItemNaturally(e.getClickedBlock().getLocation(), plugin.christmasPresent.get(random).getItemStack());
			
		}
	}
	
	@SuppressWarnings("deprecation")
	private void rightclickKalender(PlayerInteractEvent e, Player p)
	{
		File file = new File("plugins//Weinachtsgeschenk//" + p.getUniqueId().toString() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
		if(!file.exists())
		{
			try {
				yml.save(file);
			} catch (Exception e2) {}
		}
		try {
			yml.load(file);
		} catch (IOException | InvalidConfigurationException e1) {
			e1.printStackTrace();
		}
		if(e.getClickedBlock().getType() == Material.PURPLE_SHULKER_BOX)
		{
			e.setCancelled(true);
			if(Integer.valueOf(plugin.printSimpleDateFormat()) < 32 && Integer.valueOf(plugin.printSimpleDateFormat()) > 24)
			{
				p.sendMessage("§cEs geht nur vom §61.12.2020 §cbis zum §624.12.2020§c!");
			} else {
				List<String> list = yml.getStringList("oldItems");
					
				if(!list.contains(plugin.printSimpleDateFormat()))
				{
					DecimalFormat df = new DecimalFormat("00");
					for(int i = 1; i < Integer.valueOf(plugin.printSimpleDateFormat())+1; i++)
					{
						String item = yml.getString(df.format(i)).split(":")[0];
						int amount = Integer.valueOf(yml.getString(df.format(i)).split(":")[1]);
						ItemStack item1 = new ItemStack(Material.getMaterial(item), getRandomNumberInRange(1, amount));
						if(!list.contains(df.format(i)))
						{
					        if(e.getPlayer().getInventory().firstEmpty() != -1){
								list.add(df.format(i));
								p.getInventory().addItem(item1);
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					        }else{
					        	p.sendTitle("§cInformation", "§cVolles Inv!");
					        	p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					        	return;
					        }
						}
					}
				} else {
					p.sendTitle("§cInformation", "§cGeschenk bereits abgeholt!");
					p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				}
				
				yml.set("oldItems", list);
				try {
					yml.save(file);
				} catch (Exception e2) {}
			}
		}
	}
	
	private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
	
}
