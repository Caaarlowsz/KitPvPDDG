package net.robert.kitpvp.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.scheduler.BukkitRunnable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProfileLoader extends BukkitRunnable {

    private Profile profile;

    private static final String INSERT = "INSERT INTO ze_players VALUES(?, ?, ?, ?) ON DUPLICATE KEY UPDATE name=?";
    private static final String SELECT_KILLS = "SELECT kills FROM ze_players WHERE uuid=?";
    private static final String SELECT_DEATHS = "SELECT deaths FROM ze_players WHERE uuid=?";
    
    public ProfileLoader(Profile profile)
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

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, profile.getUuid().toString());
            preparedStatement.setString(2, profile.getName());
            preparedStatement.setInt(3, 0);
            preparedStatement.setInt(4, 0);
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement(SELECT_KILLS);
            preparedStatement.setString(1, profile.getUuid().toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) 
            {
                profile.setKills(resultSet.getInt("kills"));
            }
            
            preparedStatement = connection.prepareStatement(SELECT_DEATHS);
            preparedStatement.setString(1, profile.getUuid().toString());

            ResultSet resultSet2 = preparedStatement.executeQuery();

            if (resultSet.next()) 
            {
                profile.setDeaths(resultSet.getInt("deaths"));
            }

            preparedStatement.close();
            resultSet.close();
            resultSet2.close();
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