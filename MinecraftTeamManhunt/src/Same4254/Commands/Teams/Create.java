package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import Same4254.Main;

/**
 *	Given the team name, this will create a team with the given name. Member of a team cannot hit each other. 
 *	This will make the player leave whatever team they are currently in. 
 *	If the team the player is leaving will no longer have any members, that team is removed from memory. 
 *
 *	Team names cannot contain any spaces!! Any spaces in the name will be removed before being added to the game
 *
 *	Usage: "/create <team-name>"
 *
 *	TODO: make pvp configurable?
 */
public class Create extends Command {
	public Create() {
		super("create");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			//The team name the player wants to make (with no spaces)
			String teamName = Main.mergeStrings(args);
			
			//Check if a team with this name already exists. If it does, do nothing.
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
			if(team == null) {
				//Register this new team
				team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(teamName);
				player.sendMessage("You have created a new team named: " + teamName);

				//Execute the join command so that the exact same behavior as /join is followed. No code duplication ;)
				new Join().execute(sender, "join", new String[] {teamName});
				
				team.setPrefix("[" + team.getDisplayName() + "]");
				
				team.setAllowFriendlyFire(false);
				team.setCanSeeFriendlyInvisibles(true);
				
				return true;
			}
			
			player.sendMessage(teamName + " is a team that already exists!");
			return false;
		}
		
		return false;
	}
}
