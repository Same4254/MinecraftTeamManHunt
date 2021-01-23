package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

/**
 * This allows you to specify the color of your team. 
 * If you are not part of a team, nothing happens. 
 * If you are not part of a team and you specify a color and then you create a team, this will not use the color you specified. 
 * This only works when you are in the team.
 * 
 * Usage: "/color <color>"
 * 
 * TODO: can we do better than a large if-else block?
 */
public class Color extends Command {
	public Color() {
		super("color");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			//If not part of a team, or there is no color specified, do nothing
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
			if(team == null || args.length == 0) {
				return false;
			}
			
			String colorName = args[0];
			ChatColor color = null;

			//unfortunately the ChatColor class does not have these colors stored in readable format, only in char codes
			if(colorName.equalsIgnoreCase("BLACK")) {
				color = ChatColor.BLACK;
			} else if(colorName.equalsIgnoreCase("DARK_BLUE")) {
				color = ChatColor.DARK_BLUE;
			} else if(colorName.equalsIgnoreCase("DARK_GREEN")) {
				color = ChatColor.DARK_GREEN;
			} else if(colorName.equalsIgnoreCase("DARK_AQUA")) {
				color = ChatColor.DARK_AQUA;
			} else if(colorName.equalsIgnoreCase("DARK_RED")) {
				color = ChatColor.DARK_RED;
			} else if(colorName.equalsIgnoreCase("DARK_PURPLE")) {
				color = ChatColor.DARK_PURPLE;
			} else if(colorName.equalsIgnoreCase("GOLD")) {
				color = ChatColor.GOLD;
			} else if(colorName.equalsIgnoreCase("GRAY")) {
				color = ChatColor.GRAY;
			} else if(colorName.equalsIgnoreCase("DARK_GRAY")) {
				color = ChatColor.DARK_GRAY;
			} else if(colorName.equalsIgnoreCase("BLUE")) {
				color = ChatColor.BLUE;
			} else if(colorName.equalsIgnoreCase("GREEN")) {
				color = ChatColor.GREEN;
			} else if(colorName.equalsIgnoreCase("AQUA")) {
				color = ChatColor.AQUA;
			} else if(colorName.equalsIgnoreCase("RED")) {
				color = ChatColor.RED;
			} else if(colorName.equalsIgnoreCase("LIGHT_PURPLE")) {
				color = ChatColor.LIGHT_PURPLE;
			} else if(colorName.equalsIgnoreCase("YELLOW")) {
				color = ChatColor.YELLOW;
			} else if(colorName.equalsIgnoreCase("WHITE")) {
				color = ChatColor.WHITE;
			}
			
			if(color == null)
				return false;
			
			team.setPrefix(color + "[" + team.getDisplayName() + "]");
		}
		
		return false;
	}
}
