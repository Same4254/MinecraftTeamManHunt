package Same4254.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Same4254.Main;

/**
 *	The idea of this command is to set the tracking location of the player's compass to a target player.
 *	This will simply change what player's position you are subscribing to on the compass update cycle
 *	
 *	Usage: "/follow <player-name>"
 */
public class Follow extends Command {
	public Follow() {
		super("follow");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			//Player that sent this command
			Player player = (Player) sender;
			
			//Find the player that is to be tracked
			Player p = Bukkit.getPlayer(args[0]);
			if(p == null)
				return false;
			
			Main.followWho.put(player, p);
			
			return true;
		}
		
		return false;
	}
}
