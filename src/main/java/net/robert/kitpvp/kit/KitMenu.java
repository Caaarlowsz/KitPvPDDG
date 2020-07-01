package net.robert.kitpvp.kit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class KitMenu implements InventoryHolder{
	
	private Inventory inventory;
	
	public KitMenu(Player player) 
	{
		inventory = Bukkit.createInventory(this, 27, ChatColor.DARK_AQUA + "Kit Kiezer");
		
		ItemStack iron = new ItemStack(Material.IRON_SWORD);
		ItemStack diamond = new ItemStack(Material.DIAMOND_SWORD);
		
		ItemMeta ironMeta = iron.getItemMeta();
		ironMeta.setDisplayName(ChatColor.RED + "Iron Kit");
		iron.setItemMeta(ironMeta);
		
		ItemMeta diamondMeta = diamond.getItemMeta();
		diamondMeta.setDisplayName(ChatColor.RED + "Diamond Kit");
		diamond.setItemMeta(diamondMeta);
		
		inventory.setItem(0, iron);
		inventory.setItem(1, diamond);
		
		player.openInventory(inventory);
	}
	
	@Override
	public Inventory getInventory() {
		return inventory;
	}

}
