package Same4254;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import Same4254.Commands.CommandHandler;
import Same4254.Commands.AutoComplete.PlayerNameTabAutoComplete;
import Same4254.Commands.AutoComplete.TeamTabAutoComplete;

public class Main extends JavaPlugin {
	
	/**
	 * Given an array of strings, merge them together into one single string.
	 * This is useful for taking the arguments of a command and putting them all into one string.
	 * 
	 * Example:
	 * 		Command: "/join The Betterest Team Ever"
	 * 		Input to this function: ["The", "Betterest", "Team", "Ever"]
	 * 		Output: "TheBetterestTeamEver"
	 * 
	 * Spaces between the words are eliminated to make the tab completion easier.
	 * 
	 * @param strings -> The array of strings to be merged together
	 * @return -> The merged form of the given strings, with no spaces between
	 */
	public static String mergeStrings(String[] strings) {
		if(strings.length == 0)
			return "";
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < strings.length; i++)
			builder.append(strings[i]);
		
		return builder.toString();
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		//Add the auto completers to the commands. TODO Is there a better way to do this (preferably in the command classes)?
		getCommand("join").setTabCompleter(new TeamTabAutoComplete());
		getCommand("follow").setTabCompleter(new PlayerNameTabAutoComplete());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Allow the command handler to give the args to the right command, rather than a long if-else block
		return CommandHandler.execute(sender, label, args);
	}
}
