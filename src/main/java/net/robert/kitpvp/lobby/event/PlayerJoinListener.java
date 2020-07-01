package net.robert.kitpvp.lobby.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.robert.kitpvp.KitPVP;
import net.robert.kitpvp.game.PlayerState;
import net.robert.kitpvp.profile.Profile;
import net.robert.kitpvp.profile.ProfileLoader;
import net.robert.kitpvp.scoreboard.GameScoreboard;

public class PlayerJoinListener implements Listener {
	
	private KitPVP kitpvp;
	
	public PlayerJoinListener(KitPVP kitpvp)
	{
		this.kitpvp = kitpvp;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		int maxPlayers = kitpvp.getConf().getInt("Lobby.maxPlayers") -1;
		
		if(maxPlayers == Bukkit.getServer().getOnlinePlayers().size())
		{
			event.getPlayer().kickPlayer(ChatColor.RED + "De game is helaas al vol.");
			event.setJoinMessage("");
			return;
		}

		event.setJoinMessage(ChatColor.GREEN + "+ " + event.getPlayer().getName() + ChatColor.GRAY +  " (" + Bukkit.getServer().getOnlinePlayers().size() + "/" + maxPlayers + ")");
		event.getPlayer().teleport(kitpvp.getLobby().getSpawn());
		event.getPlayer().setFoodLevel(20);
		event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
		event.getPlayer().setGameMode(GameMode.SURVIVAL);
		event.getPlayer().getInventory().clear();
		
		kitpvp.setPlayerState(event.getPlayer(), PlayerState.LOBBY);
		
		final Profile profile = new Profile(kitpvp, event.getPlayer());
        kitpvp.getProfiles().put(event.getPlayer().getUniqueId(), profile);
        new ProfileLoader(profile).runTaskAsynchronously(kitpvp);
		
		setupScoreboard(event.getPlayer(), profile);
	}
	
	public void setupScoreboard(Player player, Profile profile)
	{
		GameScoreboard board = new GameScoreboard("KitPvP");
		board.addLine(ChatColor.GREEN + "" + ChatColor.BOLD + "Kills");
		board.addLine(ChatColor.GRAY + String.valueOf(profile.getKills()));
		board.addBlankLine();
		board.addLine(ChatColor.RED + "" + ChatColor.BOLD + "Deaths");
		board.addLine(ChatColor.GRAY + String.valueOf(profile.getDeaths()));
		board.addBlankLine();
		board.addLine(ChatColor.AQUA + "" + ChatColor.BOLD + "K/D");
		double roundOff = Math.round(profile.getKD() * 100.0) / 100.0;
		board.addLine(ChatColor.GRAY + String.valueOf(roundOff));
		board.addBlankLine();
		kitpvp.getScoreboardManager().setScoreboard(player, board);
	}

}
