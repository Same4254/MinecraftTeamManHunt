package Same4254.Commands.AutoComplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

public class ColorAutoComplete implements TabCompleter {
	//List of all of the known color names to be filtered with the given partial text
	private static List<String> colorNames;
	
	static {
		colorNames = Arrays.stream(ChatColor.values()).filter(c -> !c.name().equals("MAGIC") && c.isColor())
						   .map(color -> color.name() ).collect(Collectors.toList());
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
