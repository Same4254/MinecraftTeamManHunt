package Same4254.Commands.Teams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import Same4254.Main;

public class Create extends Command {
	public Create() {
		super("create");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			String teamName = Main.mergeStrings(args);
			
			Team team = Bukkit.getScoreboardManager().getMainScoreboard().getTeam(teamName);
			if(team == null) {
				team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(teamName);
				player.sendMessage("You have created a new team named: " + teamName);

				new Join().execute(sender, "join", new String[] {teamName});
				
				team.setPrefix(ChatColor.GREEN + "[" + team.getDisplayName() + "]");
				
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
