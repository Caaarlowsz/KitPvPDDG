package net.robert.kitpvp.game.event;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.robert.kitpvp.KitPVP;
import net.robert.kitpvp.game.Game;
import net.robert.kitpvp.profile.ProfileSaver;

public class KillListener implements Listener {
	
	private KitPVP kitpvp;
	private Game game;
	
	public KillListener(Game game, KitPVP kitpvp)
	{
		this.game = game;
		this.kitpvp = kitpvp;
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event)
	{
		if (event.getEntity().getKiller() == null) 
		{
			return;
		}
		
		String killerName = "";
		if ((event.getEntity().getKiller() instanceof Player))
		{
			killerName = event.getEntity().getKiller().getName();
		}
		
		event.setDeathMessage(ChatColor.YELLOW + killerName + ChatColor.RED + " HEEFT " + ChatColor.YELLOW + event.getEntity().getName() + 
				ChatColor.RED + " gekillt! their ");
		
		event.getDrops().clear();
		
		game.removePlayer(event.getEntity());
		
		kitpvp.getProfile(event.getEntity()).setDeaths(kitpvp.getProfile(event.getEntity()).getDeaths() +1);
		kitpvp.getProfile(event.getEntity().getKiller()).setKills(kitpvp.getProfile(event.getEntity().getKiller()).getKills() +1);
		
		new ProfileSaver(kitpvp.getProfile(event.getEntity()));
		new ProfileSaver(kitpvp.getProfile(event.getEntity().getKiller()));
		
		game.updateScoreboard(event.getEntity());
		game.updateScoreboard(event.getEntity().getKiller());
	}

}
