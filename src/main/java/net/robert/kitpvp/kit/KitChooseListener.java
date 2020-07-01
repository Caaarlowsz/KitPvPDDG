package net.robert.kitpvp.kit;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.robert.kitpvp.KitPVP;

public class KitChooseListener implements Listener {
	
	private KitPVP kitpvp;
	
	public KitChooseListener(KitPVP kitpvp)
	{
		this.kitpvp = kitpvp;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(!(event.getInventory().getHolder() instanceof KitMenu))
			return;
		
		event.setCancelled(true);
		
		Player player = (Player) event.getWhoClicked();
		
		if(event.getCurrentItem().getType() == Material.IRON_SWORD) 
		{
			KitUtil.setKit(player, "iron");
			player.sendMessage(kitpvp.sendMessage("Je hebt de iron kit gekozen."));
		}

		
		if(event.getCurrentItem().getType() == Material.IRON_SWORD)
		{
			KitUtil.setKit(player, "diamond");
			player.sendMessage(kitpvp.sendMessage("Je hebt de diamond kit gekozen."));
		}
	}

}
