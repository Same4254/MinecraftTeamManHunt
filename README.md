# Minecraft Team ManHunt

  The idea of this project is to allow teams of players to compete with one another to beat the game first. So far this is achieved with a set of commands that allows players to set up teams and set compass locations to other player locations.

## Commands
  The following are commands for this plugin

### Create <team-name>
  Given the team name, this will create a team with the given name. Member of a team cannot hit each other. This will make the player leave whatever team they are currently in. If the team the player is leaving will no longer have any members, that team is removed from memory. Team names cannot contain any spaces!! Any spaces in the name will be removed before being added to the game.
  
### Join <team-name>
  This will have the player join the team with the given team name. If the team they wish to join does not exist, then nothing will happen. Otherwise, they will leave the team they currently are in, if such a team exists, and join the team they desire. This comes with auto complete tabbing in the console :)

### Follow <player-name>
  This simply sets the compass location to the player with the given name. If that player does not exist, or is not online, nothing will happen. This does not update in real time, so if you want to update the location, enter this command again. 
