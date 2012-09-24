package Crusher;

import org.powerbot.game.bot.Context;


import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.Bank;

import Crusher.Container.Cons;

public class stopAtEndStrategy {


	public static void run() {

		if (Bank.isOpen()) {
			Bank.close();
			Game.logout(true);
			Context.get().getScriptHandler().stop(); 
		}
 
	}


	public static boolean validate() {
		return Bank.getItem(Cons.SCALE_ID) == null && Cons.stopatend;
	}

}
