package Crusher;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import Crusher.Container.Cons;

@Manifest(authors = { "Aww" }, name = "Crusher AIO beta", description = "Crusher scales/choco bars for money", version = 0.2)
public class Crusher extends ActiveScript implements PaintListener,
		MessageListener {
	@Override
	protected void setup() {
		Cons.scale_price = Cons.getPrice(243);
		Cons.scale_dust_price = Cons.getPrice(241);
		Cons.bar_price = Cons.getPrice(1973);
		Cons.bar_dust_price = Cons.getPrice(1975);
		Cons.startTime = System.currentTimeMillis();
		
		gui.setVisible(true);
		Cons.runTime = new Timer(0);
		if (Inventory.getItem(Cons.SCALE_ID) == null) {

			Cons.bankNow = true;
		} else
			Cons.bankNow = false;

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
		g.drawString("Status : " + Cons.status, 326, 512);
		g.setColor(Cons.color5);
		g.drawString("Money made : " + Cons.scaleGrinded
				* (Cons.dustPrice - Cons.scalePrice), 27, 472);
		g.drawString("Money Hour : " + ((Cons.scaleGrinded
				* (Cons.dustPrice - Cons.scalePrice) * 3600000l) / (System
				.currentTimeMillis() - Cons.startTime)), 27, 499);
		g.drawImage(Cons.img1, 320, 369, null);
		g.drawImage(Cons.img2, 408, 376, null);

	}

	@Override
	public void messageReceived(MessageEvent m) {
		if (m.getMessage().contains("You grind")) {
			Cons.scaleGrinded++;
		}

	}

	JFrame gui = new grinderGUI();
	class grinderGUI extends JFrame {
		public grinderGUI() {
			initComponents();
			setTitle("Aww's Crusher");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		private void startButtonActionPerformed(ActionEvent e) {
			String chosen = Whattocrush.getSelectedItem().toString();
			if (chosen.equals("Blue dragon scales")) {
				Cons.SCALE_ID = 243;
				Cons.DUST_ID = 241;
				Cons.dustPrice = Cons.scale_dust_price;
				Cons.scalePrice = Cons.scale_price;
				Cons.chocolate = false;
			}
			if (chosen.equals("Chocolate bar")) {
				Cons.SCALE_ID = 1973;
				Cons.DUST_ID = 1975;
				Cons.dustPrice = Cons.bar_dust_price;
				Cons.scalePrice = Cons.bar_price;
				Cons.chocolate = true;
			}
			if(checkBox1.isSelected()){
				Cons.stopatend = true;
			}else
				Cons.stopatend = false;
			
			gui.setVisible(false);
			gui.dispose();
			
			provide(new crushStrategyYea());
			provide(new bankStrategyYea());
			provide(new stopAtEndStrategy());

		}


		private void initComponents() {

			label1 = new JLabel();
			label2 = new JLabel();
			Whattocrush = new JComboBox();
			startButton = new JButton();
			checkBox1 = new JCheckBox();

			// ======== this ========
			Container contentPane = getContentPane();

			// ---- label1 ----
			label1.setText("Aww's Crusher");
			label1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

			// ---- label2 ----
			label2.setText("What to crush :");
			label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

			// ---- Whattocrush ----
			Whattocrush.setModel(new DefaultComboBoxModel(new String[] {
					"Blue dragon scales", "Chocolate bar" }));

			// ---- startButton ----
			startButton.setText("Start");
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startButtonActionPerformed(e);
				}
			});
			//---- checkBox1 ----
			checkBox1.setText("Log out/stop if out of items to crush");
			checkBox1.setToolTipText("Stops script if out of items to crush in bank.");
			checkBox1.setSelected(true);


			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout.setHorizontalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addGroup(contentPaneLayout.createParallelGroup()
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGap(55, 55, 55)
								.addComponent(label1))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addGap(118, 118, 118)
								.addComponent(startButton))
							.addGroup(contentPaneLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(checkBox1)
									.addGroup(contentPaneLayout.createSequentialGroup()
										.addComponent(label2)
										.addGap(18, 18, 18)
										.addComponent(Whattocrush, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(55, Short.MAX_VALUE))
			);
			contentPaneLayout.setVerticalGroup(
				contentPaneLayout.createParallelGroup()
					.addGroup(contentPaneLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(label1)
						.addGap(27, 27, 27)
						.addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(label2)
							.addComponent(Whattocrush, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(checkBox1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
						.addComponent(startButton))
			);
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization  //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - muka suka
		private JLabel label1;
		private JLabel label2;
		private JComboBox Whattocrush;
		private JButton startButton;
		private JCheckBox checkBox1;
		
	}


	

}