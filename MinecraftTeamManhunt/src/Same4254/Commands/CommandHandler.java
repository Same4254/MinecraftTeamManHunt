package Same4254.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import Same4254.Commands.Teams.Create;
import Same4254.Commands.Teams.Join;

/**
 *	The idea of this class is to handle distributing the work of a given command.
 *	This is useful because it allows commands to be broken into multiple files
 *
 *	Usage: CommandHandler.execute(sender, label, args)
 */
public class CommandHandler {
	//Map the label of the command to its command object
	private static HashMap<String, Command> commands;
	
	//This runs only on the first static call to CommandHandler.execute
	static {
		commands = new HashMap<String, Command>();
		
		//These are put into a list so that the for loop below can access the labels they set internally (they are set in the constructor)
		Command[] commandsToInsert = {
			new Join(), 
			new Create(),
			new Follow()
		};
		
		for(Command c : commandsToInsert)
			commands.put(c.getLabel().toLowerCase(), c);
	}
	
	/**
	 * This will find the command with the given label and have it execute with the given sender and arguments
	 * 
	 * @param sender -> The CommandSender that sent this command 
	 * @param label -> The label of the command
	 * @param args -> Arguments to the command
	 */
	public static boolean execute(CommandSender sender, String label, String[] args) {
		//Search for the command with the given label
		Command c = commands.get(label.toLowerCase());
		if(c == null)
			return false;
		
		return c.execute(sender, label, args);
	}
}
