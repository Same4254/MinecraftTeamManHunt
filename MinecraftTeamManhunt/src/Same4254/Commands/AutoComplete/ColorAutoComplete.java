package Same4254.Commands.AutoComplete;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class ColorAutoComplete implements TabCompleter {
	//List of all of the known color names to be filtered with the given partial text
	private static ArrayList<String> colorNames;
	
	static {
		colorNames = new ArrayList<>();
		
		//The Allowed colors, as specified by ChatColor
		colorNames.add("BLACK");
		colorNames.add("DARK_BLUE");
		colorNames.add("DARK_GREEN");
		colorNames.add("DARK_AQUA");
		colorNames.add("DARK_RED");
		colorNames.add("DARK_PURPLE");
		colorNames.add("GOLD");
		colorNames.add("GRAY");
		colorNames.add("DARK_GRAY");
		colorNames.add("BLUE");
		colorNames.add("GREEN");
		colorNames.add("AQUA");
		colorNames.add("RED");
		colorNames.add("LIGHT_PURPLE");
		colorNames.add("YELLOW");
		colorNames.add("WHITE");
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(args.length == 0 || args[0].equals(""))
			return colorNames;
		
		ArrayList<String> toRet = new ArrayList<>();
		
		//Util function that was probably crafted for this use case
		//Takes the incomplete text and finds partial matches from the player names list, and puts them into toRet
		StringUtil.copyPartialMatches(args[0], colorNames, toRet);
		
		return toRet;
	}
}
