package Same4254.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Follow extends Command {

	public Follow() {
		super("follow");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			Player p = Bukkit.getPlayer(args[0]);
			if(p == null)
				return false;
			
			player.setCompassTarget(p.getLocation());
			return true;
		}
		
		return false;
	}
}
