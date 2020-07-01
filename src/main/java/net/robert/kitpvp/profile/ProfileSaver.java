package net.robert.kitpvp.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.scheduler.BukkitRunnable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProfileSaver extends BukkitRunnable {

    private Profile profile;

    private static final String SAVE_KILLS = "UPDATE ze_players SET kills=?";
    private static final String SAVE_DEATHS = "UPDATE ze_players SET deaths=?";
    
    public ProfileSaver(Profile profile)
    {
    	this.profile = profile;
    }

    @Override
    public void run() 
    {
        Connection connection = null;

        try 
        {
            connection = profile.getKitPvp().getDatabas().getHikari().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_KILLS);
            preparedStatement.setInt(1, profile.getKills());
            preparedStatement.setString(2, profile.getUuid().toString());
            preparedStatement.execute();
            preparedStatement.close();
 
            PreparedStatement preparedStatement_ = connection.prepareStatement(SAVE_DEATHS);
            preparedStatement_.setInt(1, profile.getDeaths());
            preparedStatement_.setString(2, profile.getUuid().toString());
            preparedStatement_.execute();
            preparedStatement_.close();
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        } finally {
            if (connection != null) 
            {
                try {
                    connection.close();
                } catch (SQLException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }

}