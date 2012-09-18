package Crusher;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.bot.Context;

import Crusher.Container.Cons;

public class stopAtEndStrategy extends Strategy implements Runnable {

	@Override
	public void run() {

		if (Bank.isOpen()) {
			Bank.close();
			Game.logout(true);
			Context.get().getActiveScript().stop(); 
		}
 
	}

	@Override
	public boolean validate() {
		return Bank.getItem(Cons.SCALE_ID) == null && Cons.stopatend;
	}

}
