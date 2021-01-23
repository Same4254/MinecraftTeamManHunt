package Same4254;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import Same4254.Commands.CommandHandler;
import Same4254.Commands.AutoComplete.ColorAutoComplete;
import Same4254.Commands.AutoComplete.PlayerNameTabAutoComplete;
import Same4254.Commands.AutoComplete.TeamTabAutoComplete;

//TODO: what happens when a player disconnects while being tracked??
public class Main extends JavaPlugin {
	//Track what player (key) is following who (value)
	//Hunter to victim key-value pairs essentially
	public static HashMap<Player, Player> followWho;
	
	//Position of every player in the dimensions
	public static HashMap<Player, HashMap<Environment, Vector>> playerLocations;
	
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
		
		followWho = new HashMap<>();
		playerLocations = new HashMap<>();
		
		//Schedule a task to run every second. This will update the compasses
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
			//Update every players position into playerLocations
			//This only updates the location of the dimension they are currently in
			for(Player p : Bukkit.getOnlinePlayers()) {
				Location playerLocation = p.getLocation();
				if(playerLocation.getWorld() == null)
					continue;
				
				playerLocations.computeIfAbsent(p, key -> new HashMap<>()).put(playerLocation.getWorld().getEnvironment(), playerLocation.toVector());
			}
			
			//Update everyone's compass
			for(Map.Entry<Player, Player> entry : followWho.entrySet()) {
				Player hunter = entry.getKey();
				Player victim = entry.getValue();
				
				Location hunterLocation = hunter.getLocation();
				if(hunterLocation.getWorld() == null)
					continue;
				
				//find the victim's location in the dimension the hunter is in
				Vector toPoint = playerLocations.computeIfAbsent(victim, key -> new HashMap<>())
												.computeIfAbsent(hunter.getWorld().getEnvironment(), key -> hunterLocation.toVector());
				
				//Convert the Vector into a Location, which includes dimension information
				Location l = new Location(hunterLocation.getWorld(), toPoint.getX(), toPoint.getY(), toPoint.getZ());
				
				hunter.setCompassTarget(l);
				
				/**
				 * Change the meta data of the lodestone compass. 
				 * Unfortunately the location to point to is meant to be fixed, and is part of the item's metadata.
				 * This is unlike the regular compass when can variably point anywhere.
				 * By changing the metadata, this cause the item to kind of reset.
				 * This is why the compass seems to "tick" when using it.
				 * Would like to have a better solution, but the lodestone compass works in the nether.
				 * I don't think there is anything to be done about this without modding the game.
				 */
				PlayerInventory inv = hunter.getInventory();
				for (int j = 0; j < inv.getSize(); j++) {
                    ItemStack stack = inv.getItem(j);
                    if (stack == null) continue;
                    if (stack.getType() != Material.COMPASS) continue;

                    CompassMeta meta = (CompassMeta) stack.getItemMeta();
                    meta.setLodestone(l);
                    meta.setLodestoneTracked(false);
                    stack.setItemMeta(meta);
                }
			}
		}, 0L, 1 * 20);
		
		//Add the auto completers to the commands. TODO Is there a better way to do this (preferably in the command classes)?
		getCommand("join").setTabCompleter(new TeamTabAutoComplete());
		getCommand("follow").setTabCompleter(new PlayerNameTabAutoComplete());
		getCommand("color").setTabCompleter(new ColorAutoComplete());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Allow the command handler to give the args to the right command, rather than a long if-else block
		return CommandHandler.execute(sender, label, args);
	}
}
