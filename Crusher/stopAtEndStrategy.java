package Crusher;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.bot.Context;

import Crusher.Container.Cons;

public class stopAtEndStrategy extends Node {

	@Override
	public void execute() {

		if (Bank.isOpen()) {
			Bank.close();
			Game.logout(true);
			//Context.get().getActiveScript().stop(); 
		}
 
	}

	@Override
	public boolean activate() {
		return Bank.getItem(Cons.SCALE_ID) == null && Cons.stopatend;
	}

}
