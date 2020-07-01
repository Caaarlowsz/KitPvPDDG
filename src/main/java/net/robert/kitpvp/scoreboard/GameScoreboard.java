package net.robert.kitpvp.scoreboard;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class GameScoreboard {
	
	//private Build2Guess b2g;
	private Scoreboard board;
	private String name;
	private int i = 20;
	private HashMap<Integer, String> lineId = new HashMap<Integer, String>();
	private String blank = "";
	
	public GameScoreboard(String name)
	{
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard(); 
		@SuppressWarnings("deprecation")
		Objective objective = board.registerNewObjective("Scoreboard", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + name);
		this.name = name;
		this.board = board;
		//this.b2g = b2g;
	}
	
	public void addLine(String line)
	{
		if(i == 0)
			return;
		Score score = board.getObjective(DisplaySlot.SIDEBAR).getScore(line);
		score.setScore(i);
		lineId.put(i, line);
		i--;
	}
	
	public void addBlankLine()
	{
		if(i == 0)
			return;
		Score score = board.getObjective(DisplaySlot.SIDEBAR).getScore(blank);
		score.setScore(i);
		lineId.put(i, blank);
		blank = blank + " ";
		i--;
	}
	
	public void editLine(int line, String newInput)
	{
		board.resetScores(lineId.get(line));
		Score score = board.getObjective(DisplaySlot.SIDEBAR).getScore(newInput);
		score.setScore(line);
		lineId.put(line, newInput);
	}
	
	public void editName(String newName)
	{
		board.getObjective(DisplaySlot.SIDEBAR).setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + newName);	
	}
	
	public Scoreboard getBoard()
	{
		return board;
	}
	
	public String getName()
	{
		return name;
	}

}
