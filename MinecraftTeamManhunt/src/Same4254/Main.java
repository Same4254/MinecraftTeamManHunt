package Same4254;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import Same4254.Commands.CommandHandler;
import Same4254.Commands.PlayerNameTabAutoComplete;
import Same4254.Commands.TeamTabAutoComplete;

public class Main extends JavaPlugin {
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
		
//		Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
//
//		Team t = board.getTeam("Doubters");
//		if(t == null) {
//			t = board.registerNewTeam("Doubters");
//			t.setAllowFriendlyFire(false);
//		}
//		
//		Team t2 = board.getTeam("Believers");
//		if(t2 == null) {
//			t2 = board.registerNewTeam("Believers");
//			t2.setAllowFriendlyFire(false);
//		}
//		
//		t.setPrefix(ChatColor.GREEN + t.getDisplayName());
//		t2.setPrefix(ChatColor.RED + t2.getDisplayName());
		
		getCommand("join").setTabCompleter(new TeamTabAutoComplete());
		getCommand("follow").setTabCompleter(new PlayerNameTabAutoComplete());
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return CommandHandler.execute(sender, label, args);
	}
}
