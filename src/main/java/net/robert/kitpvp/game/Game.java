package net.robert.kitpvp.game;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import net.robert.kitpvp.KitPVP;
import net.robert.kitpvp.scoreboard.GameScoreboard;

public class Game {
	
	private KitPVP kitpvp;
	private String mapName;
	private ArrayList<Location> spawns;
	private ArrayList<Player> players;
	
	public Game(KitPVP kitpvp, String mapName)
	{
		this.kitpvp = kitpvp;
		this.mapName = mapName;
		spawns = new ArrayList<Location>();
		players = new ArrayList<Player>();
		loadSpawnPoints();
	}
	
	public void addPlayer(Player player)
	{
		player.teleport(getSpawn());
		players.add(player);
		player.sendMessage(kitpvp.sendMessage("Je bent nu KitPVP gejoined."));
	}
	
	public void updateScoreboard(Player player)
	{
		GameScoreboard board = kitpvp.getScoreboardManager().getScoreboard(player);
		board.editLine(19, ChatColor.GRAY + String.valueOf(kitpvp.getProfile(player).getKills()));
		board.editLine(16, ChatColor.GRAY + String.valueOf(kitpvp.getProfile(player).getDeaths()));
		board.editLine(16, ChatColor.GRAY + String.valueOf(kitpvp.getProfile(player).getKD()));
	}
	
	public void loadSpawnPoints()
	{
		World world = Bukkit.getWorld(getMapName());
		for(Chunk chunk : world.getLoadedChunks())
		{
			for(BlockState state : chunk.getTileEntities())
			{
				if(state instanceof Sign)
				{
					Sign sign = (Sign) state;
					if(sign.getLine(0).equalsIgnoreCase("[spawnpoint]"))
					{
						setSpawn(sign.getLocation());
						//TODO Weghalen [>rollback manager]
						//sign.getBlock().setType(Material.AIR);
					}
				}
			}
		}
	}
	
	public void removePlayer(final Player player)
	{
		getPlayers().remove(player);
		kitpvp.setPlayerState(player, PlayerState.LOBBY);
		if(player.isOnline())
		{
			Bukkit.getScheduler().runTaskLater(kitpvp, 
					new Runnable()
			{
				@Override
				public void run() 
				{
					player.teleport(kitpvp.getLobby().getSpawn());
				}
			}
			, 1L);
		}
	}
	
	public void setMapName(String mapName)
	{
		this.mapName = mapName;
	}
	
	public String getMapName()
	{
		return mapName;
	}
	
	public void setSpawn(Location spawn)
	{
		spawns.add(spawn);
	}
	
	public Location getSpawn()
	{
		return spawns.get(new Random().nextInt(spawns.size()));
	}
	
	public ArrayList<Player> getPlayers()
	{
		return players; 
	}
}
