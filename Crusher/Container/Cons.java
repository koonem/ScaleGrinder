package Crusher.Container;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Cons {
	public static int SCALE_ID = 243;
	public static int DUST_ID = 241;
	public static int BOOTH_ID = 782;

	public static boolean bankNow;
	public static boolean grindNow = true;
		
	

	public static Area bankArea = new Area(new Tile[] { new Tile(3177, 3447, 0),
			new Tile(3194, 3447, 0), new Tile(3194, 3431, 0),
			new Tile(3177, 3431, 0) });

}
 