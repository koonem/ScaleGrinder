package Crusher;


import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Crusher.Container.Cons;
  
public class bankStrategyYea extends Node {

	@Override
	public void execute() {
		
		SceneObject Booth = SceneEntities.getNearest(Cons.BOOTH_ID);
		if (Inventory.getItem(Cons.SCALE_ID) == null) {
			Cons.grindNow = true;
			if (Cons.bankArea.contains(Players.getLocal().getLocation())) {
				if (Bank.isOpen()) {
					if (Inventory.getItem(Cons.DUST_ID) != null) {
						Cons.status = "Deposit";
						Bank.depositInventory();
						Task.sleep(1000, 1500);
					} else {
						if (Bank.getItem(Cons.SCALE_ID) != null) {
							Cons.status = "Withdraw";
							Bank.withdraw(Cons.SCALE_ID, 0);
							Task.sleep(600, 800);
							Bank.close();
						}
					}
				} else if (Booth != null) {
					if (Booth.isOnScreen()) {
						Cons.status = "Open bank";
						Booth.interact("Bank");
						Task.sleep(1000, 1500);
					} else {
						Cons.status = "Turning camera";
						Camera.turnTo(Booth);
					}
					while (Players.getLocal().isMoving()) {
						Cons.status = "Player is Moving";
						Task.sleep(200, 400);
					}
				}

			}

		} else
			Cons.bankNow = false;
	}

	

	@Override
	public boolean activate() {
		return Cons.bankNow == true;
		
	}

}
