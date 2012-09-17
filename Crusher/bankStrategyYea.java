package Crusher;

import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

import Crusher.Container.Cons;

public class bankStrategyYea extends Strategy implements Runnable, Condition {

	@Override
	public void run() {
		
		SceneObject Booth = SceneEntities.getNearest(Cons.BOOTH_ID);
		if (Inventory.getItem(Cons.SCALE_ID) == null) {
			Cons.grindNow = true;
			if (Cons.bankArea.contains(Players.getLocal().getLocation())) {
				if (Bank.isOpen()) {
					if (Inventory.getItem(Cons.DUST_ID) != null) {
						Cons.status = "Deposit";
						Bank.depositInventory();
						Time.sleep(1000, 1500);
					} else {
						if (Bank.getItem(Cons.SCALE_ID) != null) {
							Cons.status = "Withdraw";
							Bank.withdraw(Cons.SCALE_ID, 0);
							Time.sleep(600, 800);
							Bank.close();
						}
					}
				} else if (Booth != null) {
					if (Booth.isOnScreen()) {
						Cons.status = "Open bank";
						Booth.interact("Bank");
						Time.sleep(1000, 1500);
					} else {
						Cons.status = "Turning camera";
						Camera.turnTo(Booth);
					}
					while (Players.getLocal().isMoving()) {
						Cons.status = "Player is Moving";
						Time.sleep(200, 400);
					}
				}

			}

		} else
			Cons.bankNow = false;
	}

	

	@Override
	public boolean validate() {
		return Cons.bankNow == true;
		
	}

}
