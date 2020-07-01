package net.robert.kitpvp.kit;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitUtil {
	
	private static ArrayList<ItemStack> ironKit = new ArrayList<ItemStack>();
	private static ArrayList<ItemStack> diamondKit = new ArrayList<ItemStack>();
	
	//TODO, configureerbaar maken + Kit object maken met kit content, menu logo en menu naam
	
	public KitUtil() 
	{
		intializeIronKit();
		intializeDiamondKit();
	}
	
	private void intializeIronKit()
	{
		ironKit.add(new ItemStack(Material.IRON_BOOTS));
		ironKit.add(new ItemStack(Material.IRON_LEGGINGS));
		ironKit.add(new ItemStack(Material.IRON_CHESTPLATE));
		ironKit.add(new ItemStack(Material.IRON_HELMET));
		
		ironKit.add(new ItemStack(Material.IRON_SWORD));
	}
	
	
	private void intializeDiamondKit() 
	{
		diamondKit.add(new ItemStack(Material.DIAMOND_BOOTS));
		diamondKit.add(new ItemStack(Material.DIAMOND_LEGGINGS));
		diamondKit.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
		diamondKit.add(new ItemStack(Material.DIAMOND_HELMET));
		
		diamondKit.add(new ItemStack(Material.DIAMOND_SWORD));
	}
	
	public static ArrayList<ItemStack> getIronKit() 
	{
		return ironKit;		
	}
	
	public static ArrayList<ItemStack> getDiamondKit() 
	{
		return diamondKit;		
	}
	
	public static void setKit(Player player, String name)  
	{
		ArrayList<ItemStack> kit = null;
		
		if(name.contains("iron"))
			kit = ironKit;
		if(name.contains("diamond"))
			kit = diamondKit;

		player.getInventory().setContents((ItemStack[]) kit.toArray());
	}


}
