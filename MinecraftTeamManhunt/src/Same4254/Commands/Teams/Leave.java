package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

/**
 *	Leave the team you are currently in. 
 *	If you are not part of a team, then nothing will happen. 
 *	I am only left to ask why you are trying to leave a team when you have no team...??
 *
 *	Usage: "/leave"
 */
public class Leave extends Command {
	public Leave() {
		super("leave");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			//If not part of a team, then do nothing
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(p);
			if(team == null)
				return false;
			
			//Disband the team if there are no more players left in the team after leaving
			team.removePlayer(p);
			if(team.getPlayers().size() == 0)
				team.unregister();
			
			return true;
		}
		
		return false;
	}
}
