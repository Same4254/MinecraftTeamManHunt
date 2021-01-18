package Same4254.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import Same4254.Commands.Teams.Create;
import Same4254.Commands.Teams.Join;

public class CommandHandler {
	private static HashMap<String, Command> commands;
	
	static {
		commands = new HashMap<String, Command>();
		
		Command[] commandsToInsert = {
			new Join(), 
			new Create(),
			new Follow()
		};
		
		for(Command c : commandsToInsert) {
			commands.put(c.getLabel().toLowerCase(), c);
		}
	}
	
	public static boolean execute(CommandSender sender, String label, String[] args) {
		Command c = commands.get(label.toLowerCase());
		if(c == null)
			return false;
		
		return c.execute(sender, label, args);
	}
}
