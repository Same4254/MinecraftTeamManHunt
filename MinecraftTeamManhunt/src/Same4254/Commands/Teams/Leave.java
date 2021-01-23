package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class Leave extends Command {

	public Leave() {
		super("leave");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(p);
			if(team == null)
				return false;
			
			team.removePlayer(p);
			
			if(team.getPlayers().size() == 0) {
				team.unregister();
			}
			
			return true;
		}
		
		return false;
	}

	
}
