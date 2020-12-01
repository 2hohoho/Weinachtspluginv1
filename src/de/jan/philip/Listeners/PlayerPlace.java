package de.jan.philip.Listeners;

import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerPlace implements Listener {

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if (e.getPlayer().getName().equalsIgnoreCase("2hohoho")) {
			if (e.getBlock().getType().equals(Material.PURPLE_SHULKER_BOX)) {
				ArmorStand armor = (ArmorStand) e.getBlock().getLocation().getWorld()
						.spawnEntity(e.getBlock().getLocation().add(0.5, -0.5, 0.5), EntityType.ARMOR_STAND);
				armor.setVisible(false);
				armor.setAI(false);
				armor.setCanPickupItems(false);
				armor.setInvulnerable(true);
				armor.setGravity(false);
				armor.setCustomNameVisible(true);
				armor.setCustomName("§bAdventskalender");

				ArmorStand armor1 = (ArmorStand) e.getBlock().getLocation().getWorld()
						.spawnEntity(e.getBlock().getLocation().add(0.5, -0.8, 0.5), EntityType.ARMOR_STAND);
				armor1.setVisible(false);
				armor1.setAI(false);
				armor1.setCanPickupItems(false);
				armor1.setInvulnerable(true);
				armor1.setGravity(false);
				armor1.setCustomNameVisible(true);
				armor1.setCustomName("§6");
			}
		} else
		{
			if(e.getBlock().getType().equals(Material.PURPLE_SHULKER_BOX))
				e.setCancelled(true);
		}
		if (e.getBlock().getType().equals(Material.BONE_BLOCK))
			e.setCancelled(true);
	}

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer().getName().equalsIgnoreCase("2hohoho")) {
			if (e.getBlock().getType().equals(Material.PURPLE_SHULKER_BOX))
				e.setCancelled(true);
			if (e.getBlock().getType().equals(Material.BONE_BLOCK))
				e.setCancelled(true);
		}
	}
}
