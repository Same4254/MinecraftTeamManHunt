package Same4254.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

/**
 *	This will give you a compass. You are welcome ;) 
 *
 *	Usage: "/compass"
 */
public class GiveCompass extends Command {
	public GiveCompass() {
		super("compass");
	}

	@Override
	public boolean execute(CommandSender sender, String commandLabel, String[] args) {
		if(sender instanceof Player) {
			//Player that sent this command
			Player player = (Player) sender;
			
			ItemStack compass = new ItemStack(Material.COMPASS, 1);
			
			//Change the compass to a Lodestone compass. This will make the compass work in the nether
			CompassMeta meta = (CompassMeta) compass.getItemMeta();
            meta.setLodestone(player.getLocation());
            meta.setLodestoneTracked(false);
            compass.setItemMeta(meta);
			
			player.getInventory().addItem(compass);
			
			return true;
		}
		
		return false;
	}
}