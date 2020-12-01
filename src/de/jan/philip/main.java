package de.jan.philip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Horse;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import de.jan.philip.Listeners.PlayerChatEvent;
import de.jan.philip.Listeners.PlayerJoin;
import de.jan.philip.Listeners.PlayerPlace;
import de.jan.philip.Listeners.RightClick;
import de.jan.philip.commands.startWEEvent;

public class main extends JavaPlugin {

	int i = 1200;
	int y = 110;
	int z = 37;
	int lenghe = i - 500;
	double speed = 0.01;
	int abwurf = 860;

	int amountPresent = 3;

	// Geschenke für den Adventskalender
	public List<String> presents = Arrays.asList("DIAMOND:6", "ELYTRA:1", "NETHER_STAR:1", "ENDER_PEARL:5",
			"COBBLESTONE:64", "SHULKER_SHELL:1", "COOKED_BEEF:45", "EMERALD:6", "BONE:32", "HEART_OF_THE_SEA:1",
			"TOTEM_OF_UNDYING:1", "DEAD_BUSH:54", "END_CRYSTAL:1", "SOUL_CAMPFIRE:3", "GOLD_BLOCK:1",
			"ENCHANTED_GOLDEN_APPLE:1", "SPIDER_EYE:64", "NETHERITE_SWORD:1", "RED_SHULKER_BOX:1", "TRIDENT:1",
			"OAK_LOG:64", "DIRT:64", "IRON_INGOT:15", "FURNACE:10", "PUMPKIN:43", "BLUE_ORCHID:23", "ANVIL:1",
			"DAMAGED_ANVIL:1", "BEEHIVE:1", "SEA_PICKLE:1", "BEE_SPAWN_EGG:3", "LEATHER:32", "STICKY_PISTON:3",
			"CONDUIT:1", "CHORUS_FRUIT:64", "NAME_TAG:5", "SPRUCE_SIGN:32", "TURTLE_HELMET:1", "CROSSBOW:1");

	boolean aktive;
	public List<String> blocks = new ArrayList<>();
	public HashMap<Location, Block> blocks1 = new HashMap<>();

	public ArrayList<ItemClasse> christmasPresent = new ArrayList<>();

	@Override
	public void onEnable() {
		try {
			// Weinachts event Geschenke!
			christmasPresent.add(new ItemClasse(Material.DIAMOND, 1, 6));
			christmasPresent.add(new ItemClasse(Material.ELYTRA, 1, 1));
			christmasPresent.add(new ItemClasse(Material.NETHER_STAR, 1, 1));
			christmasPresent.add(new ItemClasse(Material.ENDER_PEARL, 1, 5));
			christmasPresent.add(new ItemClasse(Material.COBBLESTONE, 1, 64));
			christmasPresent.add(new ItemClasse(Material.SHULKER_SHELL, 1, 1));
			christmasPresent.add(new ItemClasse(Material.COOKED_BEEF, 1, 45));
			christmasPresent.add(new ItemClasse(Material.EMERALD, 1, 6));
			christmasPresent.add(new ItemClasse(Material.BONE, 1, 32));
			christmasPresent.add(new ItemClasse(Material.HEART_OF_THE_SEA, 1, 1));
			christmasPresent.add(new ItemClasse(Material.TOTEM_OF_UNDYING, 1, 1));
			christmasPresent.add(new ItemClasse(Material.DEAD_BUSH, 1, 64));
			christmasPresent.add(new ItemClasse(Material.END_CRYSTAL, 1, 1));
			christmasPresent.add(new ItemClasse(Material.SOUL_CAMPFIRE, 1, 1));
			christmasPresent.add(new ItemClasse(Material.GOLD_BLOCK, 1, 5));
			christmasPresent.add(new ItemClasse(Material.ENCHANTED_GOLDEN_APPLE, 1, 3));
			christmasPresent.add(new ItemClasse(Material.SPIDER_EYE, 1, 64));
			christmasPresent.add(new ItemClasse(Material.NETHERITE_SWORD, 1, 1));
			christmasPresent.add(new ItemClasse(Material.RED_SHULKER_BOX, 1, 2));
			christmasPresent.add(new ItemClasse(Material.TRIDENT, 1, 1));
			christmasPresent.add(new ItemClasse(Material.OAK_LOG, 1, 64));
			christmasPresent.add(new ItemClasse(Material.DIRT, 1, 64));
			christmasPresent.add(new ItemClasse(Material.IRON_INGOT, 1, 32));
			christmasPresent.add(new ItemClasse(Material.FURNACE, 1, 7));
			christmasPresent.add(new ItemClasse(Material.PUMPKIN, 1, 54));
			christmasPresent.add(new ItemClasse(Material.BLUE_ORCHID, 1, 43));
			christmasPresent.add(new ItemClasse(Material.ANVIL, 1, 2));
			christmasPresent.add(new ItemClasse(Material.DAMAGED_ANVIL, 1, 5));
			christmasPresent.add(new ItemClasse(Material.BEEHIVE, 1, 3));
			christmasPresent.add(new ItemClasse(Material.SEA_PICKLE, 1, 64));
			christmasPresent.add(new ItemClasse(Material.BEE_SPAWN_EGG, 1, 6));
			christmasPresent.add(new ItemClasse(Material.LEATHER, 1, 5));
			christmasPresent.add(new ItemClasse(Material.STICKY_PISTON, 1, 2));
			christmasPresent.add(new ItemClasse(Material.CONDUIT, 1, 1));
			christmasPresent.add(new ItemClasse(Material.CHORUS_FRUIT, 1, 64));
			christmasPresent.add(new ItemClasse(Material.NAME_TAG, 1, 5));
			christmasPresent.add(new ItemClasse(Material.SPRUCE_SIGN, 1, 64));
			christmasPresent.add(new ItemClasse(Material.TURTLE_HELMET, 1, 2));
			christmasPresent.add(new ItemClasse(Material.CROSSBOW, 1, 3));

			Listeners();
			/*
			 * Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			 * 
			 * @Override public void run() { if(printTime().equalsIgnoreCase("07:59")) {
			 * startWeinachtsEvent(); } } }, 0, 20*15);
			 */
			Bukkit.getPluginCommand("startEvent").setExecutor(new startWEEvent(this));
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

				@Override
				public void run() {
					for (Entity ent : Bukkit.getWorld("world").getEntities()) {
						if (ent.getType() == EntityType.ARMOR_STAND) {
							ArmorStand armor = ((ArmorStand) ent);
							if (armor.getCustomName() != null) {
								if (armor.getCustomName().startsWith("§6")) {
									if (Integer.valueOf(printSimpleDateFormat()) > 24)
										armor.setCustomName("§6Bald ist es soweit!");
									else
										armor.setCustomName("§6Tag " + Integer.valueOf(printSimpleDateFormat()));
								}
							}
						}
					}
				}
			}, 5, 20 * 60 * 5);

			Bukkit.getConsoleSender().sendMessage("§8----------------------");
			Bukkit.getConsoleSender().sendMessage("§6A Bukkit Plugin by §52hohoho.de");
			Bukkit.getConsoleSender().sendMessage("§6© Jan-Philip Guba | 2019 - 2021");
			Bukkit.getConsoleSender().sendMessage("§8----------------------");
		} catch (Exception e) {
		}
	}

	@Override
	public void onDisable() {
		remove();

		Bukkit.getConsoleSender().sendMessage("----------------------");
		Bukkit.getConsoleSender().sendMessage("§6A Bukkit Plugin by §52hohoho.de");
		Bukkit.getConsoleSender().sendMessage("§6© Jan-Philip Guba | 2019 - 2021");
		Bukkit.getConsoleSender().sendMessage("----------------------");
	}

	private void Listeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new RightClick(this), this);
		pm.registerEvents(new PlayerPlace(), this);
		pm.registerEvents(new PlayerChatEvent(), this);
	}

	public void startWeinachtsEvent() {
		if (aktive != true) {
			aktive = true;
			playGlocken();
			Bukkit.getOnlinePlayers().forEach(p -> {
				for (int d = 0; d <= amountPresent; d++)
					blocks.add("BONE_BLOCK");
			});
		}
	}

	public void playGlocken() {
		animChrismas();
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (5 + speed)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (6 + speed)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (7 + speed)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (8 + speed)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (9 + speed)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(p -> {
					p.playSound(p.getLocation(), Sound.BLOCK_BELL_USE, 1, 1);
				});
			}
		}, (long) (20 * (10 + speed)));
	}

	public String printSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		Date currentTime = new Date();
		return "" + formatter.format(currentTime);
	}

	public String printTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
		Date currentTime = new Date();
		return "" + formatter.format(currentTime);
	}

	int taskid;

	private void animChrismas() {
		Location start = new Location(Bukkit.getWorld("world"), i, y, z, 90, 0);
		start.add(-3, 1, 0);
		Horse h1 = (Horse) start.getWorld().spawnEntity(start, EntityType.HORSE);
		h1.setAI(false);
		start.add(-5, 0, 0);
		Horse h2 = (Horse) start.getWorld().spawnEntity(start, EntityType.HORSE);
		h2.setAI(false);

		Location start1 = new Location(Bukkit.getWorld("world"), i, y, z, 90, 0);
		start1.add(-3, 1, 2);
		Horse h3 = (Horse) start1.getWorld().spawnEntity(start1, EntityType.HORSE);
		h3.setAI(false);
		start1.add(-5, 0, 0);
		Horse h4 = (Horse) start1.getWorld().spawnEntity(start1, EntityType.HORSE);
		h4.setAI(false);

		set(h1, h2, h3, h4);
		taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				if (i >= lenghe) {
					remove();
					i = i - 1;
					set(h1, h2, h3, h4);
					if (i == abwurf) {
						for (String m : blocks) {
							Location abwurf1 = new Location(Bukkit.getWorld("world"), abwurf, y, z);
							Material m1 = Material.getMaterial(m);
							float x = (float) -1 + (float) (Math.random() * ((0.5 - -0.5) + 1));
							float y = (float) -0.4 + (float) (Math.random() * ((0.4 - -0.4) + 0.1));
							float z = (float) -1 + (float) (Math.random() * ((0.5 - -0.5) + 0.4));

							@SuppressWarnings("deprecation")
							FallingBlock fallingBlock = Bukkit.getWorld("world")
									.spawnFallingBlock(abwurf1.add(0, 10, 0), m1, (byte) 0);
							fallingBlock.setDropItem(false);
							fallingBlock.setVelocity(new Vector(x, y, z));
						}
					}

				} else {
					Bukkit.getScheduler().cancelTask(taskid);
					remove();
					h1.remove();
					h2.remove();
					h3.remove();
					h4.remove();
				}
			}
		}, 0, (long) (5 * speed));
	}

	public void set(Horse h1, Horse h2, Horse h3, Horse h4) {
		Location start = new Location(Bukkit.getWorld("world"), i, y, z);
		/*
		 * 
		 * Kuve 1
		 * 
		 */
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_PLANKS);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_PLANKS);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);

		/*
		 * 
		 * Kuve 1
		 * 
		 */
		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(0, 0, 2);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_PLANKS);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_PLANKS);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.SPRUCE_SLAB);

		/*
		 * 
		 * EBENE 1
		 * 
		 */

		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(2, 1, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(0, 0, 1);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(0, 0, 1);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);

		/*
		 * 
		 * EBENE 1
		 * 
		 */
		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(0, 2, 0);
		start.getBlock().setType(Material.SPRUCE_FENCE);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.ACACIA_FENCE_GATE);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);

		start.add(0, 0, 1);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.ACACIA_STAIRS);
		BlockState st = start.getBlock().getState();
		Directional dir = (Directional) st.getBlockData();
		dir.setFacing(BlockFace.EAST);
		st.setBlockData(dir);
		st.update();

		start.add(-2, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);

		start.add(-1, 0, 1);
		start.getBlock().setType(Material.SPRUCE_FENCE);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.ACACIA_FENCE_GATE);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.RED_WOOL);

		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(1, 3, 0);
		start.add(3, 0, 1);
		start.getBlock().setType(Material.CHEST);
		st = start.getBlock().getState();
		dir = (Directional) st.getBlockData();
		dir.setFacing(BlockFace.WEST);
		st.setBlockData(dir);
		st.update();
		start.add(1, 0, 1);

		Location loch1 = h1.getLocation().add(-1, 0, 0);
		h1.teleport(loch1, TeleportCause.PLUGIN);

		Location loch2 = h2.getLocation().add(-1, 0, 0);
		h2.teleport(loch2, TeleportCause.PLUGIN);

		Location loch3 = h3.getLocation().add(-1, 0, 0);
		h3.teleport(loch3, TeleportCause.PLUGIN);

		Location loch4 = h4.getLocation().add(-1, 0, 0);
		h4.teleport(loch4, TeleportCause.PLUGIN);

		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(6, 0, 1);
		start.getWorld().playEffect(start, Effect.MOBSPAWNER_FLAMES, 1);
	}

	public void remove() {
		Location start = new Location(Bukkit.getWorld("world"), i, y, z);
		/*
		 * 
		 * Kuve 1
		 * 
		 */
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);

		/*
		 * 
		 * Kuve 1
		 * 
		 */
		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(0, 0, 2);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);

		/*
		 * 
		 * EBENE 1
		 * 
		 */

		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(2, 1, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(0, 0, 1);
		start.getBlock().setType(Material.AIR);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(0, 0, 1);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);

		/*
		 * 
		 * EBENE 1
		 * 
		 */
		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(0, 2, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);

		start.add(0, 0, 1);
		start.getBlock().setType(Material.AIR);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(-1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(-2, 0, 0);
		start.getBlock().setType(Material.AIR);

		start.add(-1, 0, 1);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 0);
		start.getBlock().setType(Material.AIR);

		start = new Location(Bukkit.getWorld("world"), i, y, z);
		start.add(1, 3, 0);
		start.getBlock().setType(Material.AIR);
		start.add(3, 0, 1);
		start.getBlock().setType(Material.AIR);
		start.add(1, 0, 1);
		start.getBlock().setType(Material.AIR);

	}

	public static GameProfile getNonPlayerProfile(String skinURL) {
		GameProfile newSkinProfile = new GameProfile(UUID.randomUUID(), null);
		newSkinProfile.getProperties().put("textures",
				new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
		return newSkinProfile;
	}
}
