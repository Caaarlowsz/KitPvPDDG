package net.robert.kitpvp.profile;

import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import net.robert.kitpvp.KitPVP;

@Getter
@Setter
 public class Profile {

	private KitPVP kitpvp;
	
    private UUID uuid;
    private String name;
    private boolean loaded;
    private int kills;
    private int deaths;
    

    public Profile(KitPVP kitpvp, Player player) 
    {
        this.uuid = player.getUniqueId();
        this.name = player.getName();
        this.kitpvp = kitpvp;
        loaded = true;
    }
    
    public UUID getUuid()
    {
    	return uuid;
    }
    
    public String getName()
    {
    	return name;
    }
    
    public void setKills(int kills)
    {
    	this.kills = kills;
    }
    
    public int getKills()
    {
    	return kills;
    }
    
    public void setDeaths(int deaths)
    {
    	this.deaths = deaths;
    }
    
    public int getDeaths()
    {
    	return deaths;
    }
    
    public double getKD()
    {
    	return kills/deaths;
    }
    
    
    public boolean isLoaded()
    {
    	return loaded;
    }
    
    public KitPVP getKitPvp()
    {
    	return kitpvp;
    }

}