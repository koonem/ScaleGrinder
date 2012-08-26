package Crusher;

import java.awt.Graphics;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;
import java.awt.*;
import java.io.IOException;

import Crusher.Container.Cons;

@Manifest(authors = { "Koonem" }, name = "ScaleGrinder", description = "Grind Blue dragon scale currently only at Varrock big bank", version = 0.2)
public class ScaleGrinder extends ActiveScript implements PaintListener,
		MessageListener {

	@Override
	protected void setup() {
		Cons.runTime = new Timer(0);
		provide(new crushStrategyYea());
		provide(new bankStrategyYea());
		provide(new check());
		try {
			Cons.dustPrice = Cons.getPriceOfItem(Cons.DUST_ID);
			Cons.scalePrice = Cons.getPriceOfItem(Cons.SCALE_ID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Inventory.getItem(Cons.SCALE_ID) == null) {
			Cons.bankNow = true;
		} else
			Cons.bankNow = false;
	}

	private class check extends Strategy implements Runnable {

		@Override
		public void run() {

			if (Bank.isOpen()) {
				Bank.close();
				Game.logout(true);
				stop();
			}

		}

		@Override
		public boolean validate() {
			return Bank.getItem(Cons.SCALE_ID) == null;
		}

	}

	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setColor(Cons.color1);
		g.fillRect(5, 394, 508, 132);
		g.setColor(Cons.color2);
		g.setStroke(Cons.stroke1);
		g.drawRect(5, 394, 508, 132);
		g.setFont(Cons.font1);
		g.setColor(Cons.color3);
		g.drawString("Scales crushed : " + Cons.scaleGrinded, 14, 445);
		g.drawString("Run Time: " + Time.format(Cons.runTime.getElapsed()), 57,
				422);
		g.setColor(Cons.color4);
		g.drawString("Status :", 326, 512);
		g.setColor(Cons.color5);
		g.drawString("Money made : " + Cons.scaleGrinded
				* (Cons.dustPrice - Cons.scalePrice), 27, 472);
		g.drawImage(Cons.img1, 320, 369, null);
		g.drawImage(Cons.img2, 408, 376, null);
	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You grind down the blue dragon scale.")) {
			Cons.scaleGrinded++;
		}

	}
}