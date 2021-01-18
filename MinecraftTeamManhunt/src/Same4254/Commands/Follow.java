package Same4254.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *	The idea of this command is to set the tracking location of the player's compass to a target player.
 *	
 *	Usage: "/follow <player-name>"
 *
 *	TODO: handle different dimensions.
 *	TODO: automatic updates?
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
			
			player.setCompassTarget(p.getLocation());
			return true;
		}
		
		return false;
	}
}
