package net.robert.kitpvp.lobby.event;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.robert.kitpvp.KitPVP;
import net.robert.kitpvp.profile.ProfileSaver;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event, KitPVP kitpvp)
	{
		event.setQuitMessage(ChatColor.RED + "- " + event.getPlayer().getName());
		new ProfileSaver(kitpvp.getProfile(event.getPlayer()));
	}

}
