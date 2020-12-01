package de.jan.philip;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class ItemClasse {
	
	private Material m;
	private int minAmount;
	private int maxAmount;
	private int anzahlErg;
	
	public ItemClasse(Material m, int minAmount, int maxAmount) {
		this.m = m;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.anzahlErg = minAmount + (int)(Math.random() * ((maxAmount - minAmount) + 1));
	}
	
	public ItemStack getItemStack() {
		ItemStack item = new ItemStack(m);
		item.setAmount(anzahlErg);
		return item;
	}
}
