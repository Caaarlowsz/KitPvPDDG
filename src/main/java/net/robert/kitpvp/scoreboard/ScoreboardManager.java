package net.robert.kitpvp.scoreboard;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class ScoreboardManager {
	
	public HashMap<Player, GameScoreboard> playerScoreboard = new HashMap<Player, GameScoreboard>();
	
	public void setScoreboard(Player player, GameScoreboard gameScoreboard)
	{
		playerScoreboard.put(player, gameScoreboard);
		player.setScoreboard(gameScoreboard.getBoard());
	}
	
	public GameScoreboard getScoreboard(Player player)
	{
		return playerScoreboard.get(player);
	}

}
