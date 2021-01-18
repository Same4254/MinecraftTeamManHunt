package Same4254.Commands.AutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.StringUtil;

import Same4254.Main;

/**
 *	Given some text of a command's argument, auto complete the argument to a team name.
 *	The suggestions are of the current teams.
 *
 * 	TODO: Handle player names that contain spaces?
 */
public class TeamTabAutoComplete implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<String> teamNames = new ArrayList<String>();
		
		for(Team t : Bukkit.getScoreboardManager().getMainScoreboard().getTeams())
			teamNames.add(t.getDisplayName());

		String desiredTeam = Main.mergeStrings(args);
		if(args.length == 0 || desiredTeam.equals(""))
			return teamNames;
		
		ArrayList<String> toRet = new ArrayList<>();
		
		//Util function that was probably crafted for this use case
		//Takes the incomplete text and finds partial matches from the player names list, and puts them into toRet
		StringUtil.copyPartialMatches(desiredTeam, teamNames, toRet);
		
		return toRet;
	}
}
