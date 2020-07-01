package net.robert.kitpvp.lobby.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener {
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event)
	{
		event.setCancelled(true);
	}

}
