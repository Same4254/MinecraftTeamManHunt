# Minecraft Team ManHunt

  The idea of this project is to allow teams of players to compete with one another to beat the game first. So far this is achieved with a set of commands that allows players to set up teams and set compass locations to other player locations.

## Commands
  The following are commands for this plugin

### Create <team-name>
  Given the team name, this will create a team with the given name. Member of a team cannot hit each other. This will make the player leave whatever team they are currently in. If the team the player is leaving will no longer have any members, that team is removed from memory. Team names cannot contain any spaces!! Any spaces in the name will be removed before being added to the game.
  
### Join <team-name>
  This will have the player join the team with the given team name. If the team they wish to join does not exist, then nothing will happen. Otherwise, they will leave the team they currently are in, if such a team exists, and join the team they desire. This comes with auto complete tabbing in the console :)

### Leave
  Leave the team you are currently in. If you are not part of a team, then nothing will happen. I am only left to ask why you are trying to leave a team when you have no team...??

### Color
  This allows you to specifiy the color of your team. If you are not part of a team, nothing happens. If you are not part of a team and you specify a color and then you create a team, this will **not** use the color you specified. This **only** works when you are in the team. Allowed colors are:
  * BLACK
  * DARK_BLUE
  * DARK_GREEN
  * DARK_AQUA
  * DARK_RED
  * DARK_PURPLE
  * GOLD
  * GRAY
  * DARK_GRAY
  * BLUE
  * GREEN
  * AQUA
  * RED
  * LIGHT_PURPLE
  * YELLOW
  * WHITE

### Follow <player-name>
  Every second every player's position is updated into this datastructure: 

  ```java
    HashMap<Player, HashMap<Environment, Vector>> playerLocations
  ```
  This is saying that every player has a known position in an environement (dimension like the overworld and the nether). Thus, every second, the player updates their known position in the dimension they are in. This has a few consequences:
  * If a player leaves a dimension, their last known location in that dimension will be roughly near the portal (depending on if they hit the 1 second window where they update their position)
  * When you `<follow>` someone, you are subscribing your compass to point to that player's location **in the dimension you are in**. So when you track someone who is in a different dimension, you will be pointed to their last knowne location, which is where they entered the other dimension
  * In the event you enter a dimension that the person you are tracking has not been to, the compass will point you to your portal. This is an arbitrary decision since there is no logical answer to this case.

### Compass
  This will give you a compass. You are welcome ;)
