package Crusher;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.util.Timer;

import Crusher.Container.Cons;

@Manifest(authors = { "Aww" }, name = "Crusher AIO ", description = "All In One crusher. Crushes everything crushable ", website = "http://www.powerbot.org/community/topic/771509-sdnp2pcrusher150k-300kh/", version = 1.0)
public class Crusher extends ActiveScript implements PaintListener,
		MessageListener {

	private Tree jobs = null;

	@Override
	public int loop() {
		if (Cons.guiwait) {
			Task.sleep(200, 400);
		} else {

			if (jobs == null) {
				jobs = new Tree(new Node[] { new bankStrategyYea(),
						new crushStrategyYea() });
			}

			final Node node = jobs.state();
			if (node != null) {

				jobs.set(node);
				getContainer().submit(node);
				node.join();
				return 0;
			}
		}
		return Random.nextInt(200, 300);
	}

	public void onStart() {

		try {
			cursor = ImageIO.read(new URL(CURSOR_URL));
		} catch (final Exception e) {
			e.printStackTrace();
		}

		Cons.scale_price = Cons.getPrice(243);
		Cons.scale_dust_price = Cons.getPrice(241);
		Cons.bar_price = Cons.getPrice(1973);
		Cons.bar_dust_price = Cons.getPrice(1975);
		Cons.horn_price = Cons.getPrice(9735);
		Cons.horn_dust_price = Cons.getPrice(9736);
		Cons.uni_horn_price = Cons.getPrice(237);
		Cons.uni_horn_dust_price = Cons.getPrice(235);
		Cons.runTime = new Timer(0);
		Cons.startTime = System.currentTimeMillis();


		gui.setVisible(true);

		if (Inventory.getItem(Cons.SCALE_ID) == null) {

			Cons.bankNow = true;
		} else
			Cons.bankNow = false;
	}

	private static Image cursor;
	private static final String CURSOR_URL = "http://imageshack.us/a/img411/2391/glowingcursor3.png";

	public void onRepaint(Graphics g1) {
		if (Tabs.getCurrent().equals(Tabs.INVENTORY) && !Cons.guiwait) {
			Graphics2D g = (Graphics2D) g1;
			if (cursor != null) { // check if null just in-case hosting service
									// is
				// down or something
				final Point mouse = Mouse.getLocation();
				g.drawImage(cursor, mouse.x, mouse.y, null);
				// you may need to fiddle with offsets depending on your image.
			}
			g.setColor(Cons.color1);
			g.fillRoundRect(355, 55, 163, 333, 32, 32);

			// bold black
			g.setFont(Cons.font_bold);
			g.setColor(Cons.color3);
			g.drawString("Crushed : ", 366, 145);
			g.drawString("Money made : ", 366, 195);
			g.drawString("Run Time: " + Time.format(Cons.runTime.getElapsed()),
					366, 115);
			g.drawString("Item: " + Cons.cHoosed, 366, 245);

			// kaldus black
			// crushed/H
			g.setFont(Cons.font_kaldus);
			g.drawString(
					Cons.scaleGrinded + "/ " + (Cons.scaleGrinded * 3600000l)
							/ (System.currentTimeMillis() - Cons.startTime)
							+ "p/h", 388, 165);
			// gp/H
			g.drawString(
					Cons.scaleGrinded
							* (Cons.dustPrice - Cons.scalePrice)
							+ "gp / "
							+ ((Cons.scaleGrinded
									* (Cons.dustPrice - Cons.scalePrice) * 3600000l) / (System
									.currentTimeMillis() - Cons.startTime))
							+ "gp/h", 388, 215);

			// bold red
			g.setFont(Cons.font_bold);
			g.setColor(Cons.color4);
			g.drawString("Status: " + Cons.status, 366, 275);

			// img
			g.drawImage(Cons.img1, 360, 55, null);

		}
	}
	JFrame gui = new grinderGUI();
	class grinderGUI extends JFrame {
		public grinderGUI() {
			initComponents();
			setTitle("Aww's AIO Crusher");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		private void startButtonActionPerformed(ActionEvent e) {
			String chosen = Whattocrush.getSelectedItem().toString();
			if (chosen.equals("Blue dragon scale")) {
				Cons.SCALE_ID = 243;
				Cons.DUST_ID = 241;
				Cons.dustPrice = Cons.scale_dust_price;
				Cons.scalePrice = Cons.scale_price;
				Cons.cHoosed = "Blue dragon scale";
			}
			if (chosen.equals("Chocolate bar")) {
				Cons.SCALE_ID = 1973;
				Cons.DUST_ID = 1975;
				Cons.dustPrice = Cons.bar_dust_price;
				Cons.scalePrice = Cons.bar_price;
				Cons.chocolate = true;
				Cons.cHoosed = "Chocolate bars";
			}
			if (chosen.equals("Desert goat horn")) {
				Cons.SCALE_ID = 9735;
				Cons.DUST_ID = 9736;
				Cons.dustPrice = Cons.horn_dust_price;
				Cons.scalePrice = Cons.horn_price;
				Cons.cHoosed = "Desert goat horn";
			}
			if (chosen.equals("Unicorn horn")) {
				Cons.SCALE_ID = 237;
				Cons.DUST_ID = 235;
				Cons.dustPrice = Cons.uni_horn_dust_price;
				Cons.scalePrice = Cons.uni_horn_price;
				Cons.cHoosed = "Unicorn horn";
			}
			if (checkBox1.isSelected()) {
				Cons.stopatend = true;
			} else {
				Cons.stopatend = false;
			}
			gui.setVisible(false);
			gui.dispose();
			Cons.guiwait = false;

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
			label1.setText("Aww's AIO Crusher");
			label1.setFont(new Font("Comic Sans MS", Font.BOLD, 18));

			// ---- label2 ----
			label2.setText("What to crush :");
			label2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));

			// ---- Whattocrush ----
			Whattocrush.setModel(new DefaultComboBoxModel(new String[] {
					"Blue dragon scale", "Chocolate bar", "Desert goat horn",
					"Unicorn horn" }));

			// ---- startButton ----
			startButton.setText("Start");
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startButtonActionPerformed(e);
				}
			});
			// ---- checkBox1 ----
			checkBox1.setText("Log out/stop if out of items to crush");
			checkBox1
					.setToolTipText("Stops script if out of items to crush in bank.");
			checkBox1.setSelected(false);
			checkBox1.setEnabled(false);

			GroupLayout contentPaneLayout = new GroupLayout(contentPane);
			contentPane.setLayout(contentPaneLayout);
			contentPaneLayout
					.setHorizontalGroup(contentPaneLayout
							.createParallelGroup()
							.addGroup(
									contentPaneLayout
											.createSequentialGroup()
											.addGroup(
													contentPaneLayout
															.createParallelGroup()
															.addGroup(
																	contentPaneLayout
																			.createSequentialGroup()
																			.addGap(55,
																					55,
																					55)
																			.addComponent(
																					label1))
															.addGroup(
																	contentPaneLayout
																			.createSequentialGroup()
																			.addGap(118,
																					118,
																					118)
																			.addComponent(
																					startButton))
															.addGroup(
																	contentPaneLayout
																			.createSequentialGroup()
																			.addContainerGap()
																			.addGroup(
																					contentPaneLayout
																							.createParallelGroup(
																									GroupLayout.Alignment.TRAILING)
																							.addComponent(
																									checkBox1)
																							.addGroup(
																									contentPaneLayout
																											.createSequentialGroup()
																											.addComponent(
																													label2)
																											.addGap(18,
																													18,
																													18)
																											.addComponent(
																													Whattocrush,
																													GroupLayout.PREFERRED_SIZE,
																													GroupLayout.DEFAULT_SIZE,
																													GroupLayout.PREFERRED_SIZE)))))
											.addContainerGap(55,
													Short.MAX_VALUE)));
			contentPaneLayout
					.setVerticalGroup(contentPaneLayout
							.createParallelGroup()
							.addGroup(
									contentPaneLayout
											.createSequentialGroup()
											.addContainerGap()
											.addComponent(label1)
											.addGap(27, 27, 27)
											.addGroup(
													contentPaneLayout
															.createParallelGroup(
																	GroupLayout.Alignment.BASELINE)
															.addComponent(
																	label2)
															.addComponent(
																	Whattocrush,
																	GroupLayout.PREFERRED_SIZE,
																	GroupLayout.DEFAULT_SIZE,
																	GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.UNRELATED)
											.addComponent(checkBox1)
											.addPreferredGap(
													LayoutStyle.ComponentPlacement.RELATED,
													9, Short.MAX_VALUE)
											.addComponent(startButton)));
			pack();
			setLocationRelativeTo(getOwner());
			// JFormDesigner - End of component initialization
			// //GEN-END:initComponents
		}

		// JFormDesigner - Variables declaration - DO NOT MODIFY
		// //GEN-BEGIN:variables
		// Generated using JFormDesigner Evaluation license - muka suka
		private JLabel label1;
		private JLabel label2;
		private JComboBox Whattocrush;
		private JButton startButton;
		private JCheckBox checkBox1;

	}

	public void messageReceived(org.powerbot.core.event.events.MessageEvent m) {
		if (m.getMessage().contains("You grind")) {
			Cons.scaleGrinded++;
		}
	}

	@Override
	@Deprecated
	protected void setup() {
		// TODO Auto-generated method stub

	}

}