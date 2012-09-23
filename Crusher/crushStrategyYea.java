package Crusher;


import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;


import Crusher.Container.*;

public class crushStrategyYea extends Node {
 
	@Override
	public void execute() {

		if (Inventory.getItem(Cons.SCALE_ID) != null) {
			if (Cons.chocolate) {
				if (Cons.grindNow) {
					Cons.status = "Getting bar";
					Inventory.getItem(Cons.SCALE_ID).getWidgetChild()
							.interact("Powder");
					Task.sleep(600, 1000);
				}
				if (Widgets.get(905).getChild(14).validate()) {
					Cons.grindNow = false;
					Cons.status = "Continue";
					Widgets.get(905).getChild(14).click(true);
					Task.sleep(1000, 1500);
					Cons.status = "Grinding";

				}
			}
			if (!Cons.chocolate) {
				if (Cons.grindNow) {
					Cons.status = "Getting scale";
					Inventory.getItem(Cons.SCALE_ID).getWidgetChild()
							.interact("Grind");
					Task.sleep(600, 1000);
				}
				if (Widgets.get(905).getChild(14).validate()) {
					Cons.grindNow = false;
					Cons.status = "Continue";
					Widgets.get(905).getChild(14).click(true);
					Task.sleep(1000, 1500);
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
	public boolean activate() {
		return !Cons.bankNow == true;

	}
}
