package Crusher;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;

import Crusher.Container.*;

public class crushStrategyYea extends Strategy implements Runnable, Condition {

	@Override
	public void run() {

		if (Inventory.getItem(Cons.SCALE_ID) != null) {
			if (Cons.chocolate) {
				if (Cons.grindNow) {
					Cons.status = "Getting bar";
					Inventory.getItem(Cons.SCALE_ID).getWidgetChild()
							.interact("Powder");
					Time.sleep(600, 1000);
				}
				if (Widgets.get(905).getChild(14).validate()) {
					Cons.grindNow = false;
					Cons.status = "Continue";
					Widgets.get(905).getChild(14).click(true);
					Time.sleep(1000, 1500);
					Cons.status = "Grinding";

				}
			}
			if (!Cons.chocolate) {
				if (Cons.grindNow) {
					Cons.status = "Getting scale";
					Inventory.getItem(Cons.SCALE_ID).getWidgetChild()
							.interact("Grind");
					Time.sleep(600, 1000);
				}
				if (Widgets.get(905).getChild(14).validate()) {
					Cons.grindNow = false;
					Cons.status = "Continue";
					Widgets.get(905).getChild(14).click(true);
					Time.sleep(1000, 1500);
					Cons.status = "Grinding";

				}

			}

		}
		if (Inventory.getItem(Cons.SCALE_ID) == null) {
			Cons.bankNow = true;
			Cons.grindNow = true;

		}
	}
	

	@Override
	public boolean validate() {
		return !Cons.bankNow == true;

	}
}
