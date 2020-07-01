package net.robert.kitpvp.game.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import net.robert.kitpvp.KitPVP;
import net.robert.kitpvp.game.Game;
import net.robert.kitpvp.game.PlayerState;

public class LeaveListener implements Listener {
	
	private KitPVP kitpvp;
	private Game game;
	
	public LeaveListener(Game game, KitPVP kitpvp)
	{
		this.game = game;
		this.kitpvp = kitpvp;
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event)
	{
		if(kitpvp.getPlayerState(event.getPlayer()) == PlayerState.LOBBY)
			return;
		
		game.removePlayer(event.getPlayer());
	}
}
