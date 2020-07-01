package net.robert.kitpvp.database;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.zaxxer.hikari.HikariDataSource;

public class Database {
	
    private HikariDataSource hikari;
	
	public Database()
	{
		Bukkit.getServer().getLogger().info(">> Connecting to Database.....");
		YamlConfiguration bukkitYml = YamlConfiguration.loadConfiguration(new File(Bukkit.getWorldContainer().getAbsoluteFile(), "bukkit.yml"));
		String username = bukkitYml.getString("database.username");
		String password = bukkitYml.getString("database.password");
		
		hikari = new HikariDataSource();
	    hikari.setMaximumPoolSize(10);
	    hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        hikari.addDataSourceProperty("serverName", "localhost");
        hikari.addDataSourceProperty("port", "3306");
        hikari.addDataSourceProperty("databaseName", "tesdb");
        hikari.addDataSourceProperty("user", username);
        hikari.addDataSourceProperty("password", password);

	}
	
	public HikariDataSource getHikari()
	{
		return hikari;
	}

}
