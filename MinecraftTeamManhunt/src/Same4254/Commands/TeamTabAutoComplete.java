package Same4254.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.StringUtil;

import Same4254.Main;

public class TeamTabAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> teamNames = new ArrayList<String>();
		
		for(Team t : Bukkit.getScoreboardManager().getMainScoreboard().getTeams())
			teamNames.add(t.getDisplayName());

		if(args.length == 0) {
			return teamNames;
		}
		
		String desiredTeam = Main.mergeStrings(args);
		if(desiredTeam.equals("")) {
			return teamNames;
		}
		
		ArrayList<String> toRet = new ArrayList<>();
		
		StringUtil.copyPartialMatches(desiredTeam, teamNames, toRet);
		
		return toRet;
	}
}
