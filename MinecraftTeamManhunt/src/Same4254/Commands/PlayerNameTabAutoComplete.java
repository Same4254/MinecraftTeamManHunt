package Same4254.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

public class PlayerNameTabAutoComplete implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
		ArrayList<String> playerNames = new ArrayList<String>();
		
		if(args.length == 0)
			return playerNames;
		
		ArrayList<String> toRet = new ArrayList<String>();
		
		for(Player p : players)
			playerNames.add(p.getName());
		
		StringUtil.copyPartialMatches(args[0], playerNames, toRet);
		
		return toRet;
	}
}
