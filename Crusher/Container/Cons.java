package Crusher.Container;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Cons {
	public static int Moneymade = Cons.scaleGrinded
			* (Cons.dustPrice - Cons.scalePrice);

	
	
	public static int scale_price;
	public static int scale_dust_price;
	public static int bar_price;
	public static int bar_dust_price;
	//public static int horn_price;
	//public static int horn_dust_price;

	
	
	public static int SCALE_ID;
	public static int DUST_ID;
	public static int BOOTH_ID = 782;
	public static boolean chocolate;
	public static boolean bankNow;
	public static boolean grindNow = true;
	public static boolean stopatend;

	public static Area bankArea = new Area(new Tile[] {
			new Tile(3177, 3447, 0), new Tile(3194, 3447, 0),
			new Tile(3194, 3431, 0), new Tile(3177, 3431, 0) });

	public static Image getImage(String url) {
		try {
			return ImageIO.read(new URL(url));
		} catch (IOException e) {
			return null;
		}
	}
	public static long startTime;
	public static int dustPrice;
	public static int scalePrice;
	public static int scaleGrinded = 0;
	public static Timer runTime;
	public static long timeRun;
	public static Color color1 = new Color(255, 255, 255);
	public static Color color2 = new Color(100, 87, 3);
	public static Color color3 = new Color(7, 0, 123);
	public static Color color4 = new Color(255, 0, 0);
	public static Color color5 = new Color(7, 0, 145);
	public static String status = "Loading...";

	public static BasicStroke stroke1 = new BasicStroke(1);

	public static Font font1 = new Font("Arial", 0, 13);

	public static Image img1 = getImage("http://images4.wikia.nocookie.net/__cb20111118050605/runescape/images/thumb/f/fa/Blue_dragon_scale_detail.png/50px-Blue_dragon_scale_detail.png");
	public static Image img2 = getImage("http://images3.wikia.nocookie.net/__cb20111120062735/runescape/images/thumb/a/ab/Dragon_scale_dust_detail.png/100px-Dragon_scale_dust_detail.png");
	
	// Thanks Coma ;)
	public static int getPrice(int id) {
        try {
            String price;
            URL url = new URL("http://open.tip.it/json/ge_single_item?item=" + id);
            URLConnection con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.contains("mark_price")) {
                    price = line.substring(line.indexOf("mark_price") + 13, line.indexOf(",\"daily_gp") - 1);
                    price = price.replace(",", "");
                    return Integer.parseInt(price);

                }
            }
        } catch (Exception e) {
            return -1;
        }

        return -1;
    }
	
	

}
