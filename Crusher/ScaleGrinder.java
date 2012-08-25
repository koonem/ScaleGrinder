package Crusher;


import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Inventory;


import Crusher.Container.Cons;

@Manifest(authors = { "Koonem" }, name = "ScaleGrinder", description = "Grind Blue dragon scale currently only at Varrock big bank", version = 0.2)
public class ScaleGrinder extends ActiveScript {

	@Override
	protected void setup() {
		provide(new crushStrategyYea());
		provide(new bankStrategyYea());
		if(Inventory.getItem(Cons.SCALE_ID)== null){
			Cons.bankNow = true;
		}else
			Cons.bankNow = false;
		
	}
	

} 