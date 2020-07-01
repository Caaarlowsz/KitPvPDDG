package net.robert.kitpvp.lobby.event;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.robert.kitpvp.game.Game;
import net.robert.kitpvp.kit.KitMenu;

public class SignListener implements Listener {
	
	private Game game;
	
	public SignListener(Game game)
	{
		this.game = game;
	}

	@EventHandler 
	public void onWarp(PlayerInteractEvent e)
	{
		Player player = e.getPlayer();
	     if (!(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
			Block b = e.getClickedBlock();
			BlockState state = b.getState();
			if(state instanceof Sign)
			{
				Sign s = (Sign) state;
				if(s.getLine(0).contains("[Join KitPvP]"))
				{
					game.addPlayer(player);
				}
				
				if(s.getLine(0).contains("[Kit Kiezer]"))
				{
					new KitMenu(player);
				}
			}
	}
}
