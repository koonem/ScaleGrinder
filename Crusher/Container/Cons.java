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
	public static int horn_price;
	public static int horn_dust_price;
	public static int uni_horn_price;
	public static int uni_horn_dust_price;

	
	
	public static int SCALE_ID;
	public static int DUST_ID;
	public static int BOOTH_ID = 782;
	public static boolean chocolate = false;
	public static boolean bankNow;
	public static boolean grindNow = true;
	public static boolean stopatend;
	public static boolean guiwait = true;

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
	public static Color color1 = new Color(0, 67, 239, 210);
	public static Color color2 = new Color(100, 87, 3, 240);
	public static Color color3 = new Color(0, 0, 0, 240);
	public static Color color4 = new Color(255, 0, 0, 240);
	public static Color color5 = new Color(0, 0, 0, 240);
	public static String status = "Waiting GUI";
	public static String cHoosed = "Waiting GUI";

	public static BasicStroke stroke1 = new BasicStroke(1);

	public static Font font_bold = new Font("Comic Sans MS", 1, 13);
	public static Font font_kaldus = new Font("Comic Sans MS", 2, 13);

	public static Image img1 = getImage("http://imageshack.us/a/img684/6568/67922958.png");
	
	
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
