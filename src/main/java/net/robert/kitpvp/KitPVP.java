package net.robert.kitpvp;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.robert.kitpvp.database.Database;
import net.robert.kitpvp.game.Game;
import net.robert.kitpvp.game.PlayerState;
import net.robert.kitpvp.game.event.KillListener;
import net.robert.kitpvp.kit.KitChooseListener;
import net.robert.kitpvp.lobby.Lobby;
import net.robert.kitpvp.lobby.event.FoodListener;
import net.robert.kitpvp.lobby.event.PlayerJoinListener;
import net.robert.kitpvp.lobby.event.PlayerQuitListener;
import net.robert.kitpvp.lobby.event.SignListener;
import net.robert.kitpvp.profile.Profile;
import net.robert.kitpvp.scoreboard.ScoreboardManager;
import net.robert.kitpvp.util.Files;

public class KitPVP extends JavaPlugin {
	
	/**
	 * @author Inferides // DDG proef
	 */
	
	private Game game;
	private Lobby lobby;
	private ScoreboardManager scoreboardManager;
	private Files config;
	private Database database;
    private HashMap<UUID, Profile> profiles;
    private HashMap<Player, PlayerState> playerState;
    
		
	@Override
	public void onEnable()
	{
		config = new Files(getDataFolder(), "config");
		if(!(config.fileExists()))
		{
			System.out.println("[KITPVP] Config niet gevonden! Er word een nieuwe gemaakt.");
			createConfig(config);
		}
		game = new Game(this, "KitPvP");
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		Bukkit.getPluginManager().registerEvents(new KillListener(game, this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
		Bukkit.getPluginManager().registerEvents(new SignListener(game), this);
		Bukkit.getPluginManager().registerEvents(new KitChooseListener(this), this);
		scoreboardManager = new ScoreboardManager();
		config.loadFile();
		lobby = new Lobby(this, "lobby");
		database = new Database();
		profiles = new HashMap<UUID, Profile>();
		playerState = new HashMap<Player, PlayerState>();
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public void createConfig(Files config)
	{
		System.out.println("[KITPVP] Config wordt aangemaakt..");
		if(config == null)
		{
			return;
		}
		config.createFile();
		config.set("Locations.lobby.X", 0);
		config.set("Locations.lobby.Y", 0);
		config.set("Locations.lobby.Z", 0); 
		config.set("Lobby.maxPlayers", 10);
		config.saveFile();
	}
	
	public Game getGame()
	{
		return game;
	}

	public Lobby getLobby()
	{
		return lobby;
	}
	
	
	public String sendMessage(String msg)
	{
		return ChatColor.GRAY + "[" + ChatColor.AQUA + "KIT" + ChatColor.GRAY + "]" + ChatColor.WHITE + " " + msg;
	}
	
	public Files getConf()
	{
		return config;
	}
	
	public ScoreboardManager getScoreboardManager()
	{
		return scoreboardManager;
	}
	
	public Database getDatabas()
	{
		return database;
	}
	
	public HashMap<UUID, Profile> getProfiles()
	{
		return profiles;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public Profile getProfile(Player player)
	{
		return profiles.get(player);
	}
	
	public PlayerState getPlayerState(Player player) 
	{
		return playerState.get(player);
	}

	
	public void setPlayerState(Player player, PlayerState playerState) 
	{
		this.playerState.put(player, playerState);
	}
}
