package Same4254.Commands.AutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

/**
 *	Given some text of a command's argument, auto complete the argument to a player name.
 *	The suggestions are of the *online* players. 
 *
 * 	TODO: Handle player names that contain spaces?
 */
public class PlayerNameTabAutoComplete implements TabCompleter {
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		ArrayList<String> playerNames = new ArrayList<String>();
		
		if(args.length == 0 || args[0].equals(""))
			return playerNames;
		
		ArrayList<String> toRet = new ArrayList<String>();
		
		for(Player p : players)
			playerNames.add(p.getName());
		
		//Util function that was probably crafted for this use case
		//Takes the incomplete text and finds partial matches from the player names list, and puts them into toRet
		StringUtil.copyPartialMatches(args[0], playerNames, toRet);
		
		return toRet;
	}
}
