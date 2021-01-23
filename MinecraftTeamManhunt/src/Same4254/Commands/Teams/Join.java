package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import Same4254.Main;

/**
 * This will have the player join the team with the given team name. 
 * If the team they wish to join does not exist, then nothing will happen. 
 * Otherwise, they will leave the team they currently are in, if such a team exists, and join the team they desire.
 * 
 * Usage: "/join <team-name>"
 */
public class Join extends Command {
	public Join() {
		super("join");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			String teamName = Main.mergeStrings(args);
			
			//Check if the desired team exists
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
			if(team == null) {
				player.sendMessage(teamName + " is a team that does not exist!");
				return false;
			}
			
			//Check if the player is part of a team. Make sure they are not trying to join the team they are already part of
			Team currentTeam = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
			if(team.equals(currentTeam)) {
				return false;
			}
			
			if(currentTeam != null) {
				//Execute the leave command from this team. No code duplication ;)
				new Leave().execute(sender, "leave", new String[] { currentTeam.getDisplayName() });
			}
			
			//The the player to the team they want
			player.sendMessage("Welcome to the " + teamName);
			team.addPlayer(player);
			
			return true;
		}
		
		return false;
	}
}
