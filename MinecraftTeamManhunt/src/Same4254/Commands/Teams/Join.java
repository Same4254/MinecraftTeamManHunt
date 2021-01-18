package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import Same4254.Main;

public class Join extends Command {
	public Join() {
		super("join");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			String teamName = Main.mergeStrings(args);
			
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
			if(team == null) {
				player.sendMessage(teamName + " is a team that does not exist!");
				return false;
			}
			
			Team currentTeam = Bukkit.getScoreboardManager().getMainScoreboard().getPlayerTeam(player);
			if(currentTeam != null) {
				if(currentTeam.getPlayers().size() == 1)
					currentTeam.unregister();
			}
			
			player.sendMessage("Welcome to the " + teamName);
			team.addPlayer(player);
			
			return true;
		}
		
		return false;
	}
}
