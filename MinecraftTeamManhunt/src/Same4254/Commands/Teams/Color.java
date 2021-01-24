package Same4254.Commands.Teams;

import java.util.Arrays;

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
			
			//***Thanks to Mike in the CSE discord for spotting this!!***
			ChatColor color = Arrays.stream(ChatColor.values())
									.filter(c -> (c.name().equalsIgnoreCase(colorName) &&
												  !colorName.equalsIgnoreCase(ChatColor.MAGIC.name())))
									.findFirst()
									.orElse(null);
			
			if(color == null || !color.isColor())
				return false;
			
			team.setPrefix(color + "[" + team.getDisplayName() + "]");
		}
		
		return false;
	}
}
