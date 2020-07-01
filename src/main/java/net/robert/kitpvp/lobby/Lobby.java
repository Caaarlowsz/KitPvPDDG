package net.robert.kitpvp.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldCreator;

import net.robert.kitpvp.KitPVP;

public class Lobby {
	
	//private KitPVP kitpvp;
	private Location spawn;
	
	public Lobby(KitPVP kitpvp, String worldName)
	{
		//this.kitpvp = kitpvp;
		Bukkit.createWorld(new WorldCreator(worldName));
		double x = kitpvp.getConf().getDouble("Locations.lobby.X");
		double y = kitpvp.getConf().getDouble("Locations.lobby.Y");
		double z = kitpvp.getConf().getDouble("Locations.lobby.Z");
		spawn = new Location(Bukkit.getWorld(worldName), x,y,z);
	}
	
	
	public Location getSpawn()
	{
		return spawn;
	}
}
