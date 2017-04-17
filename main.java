import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class main {

	private JFrame frame;
	Player player;
	Computer compuer;
	Table table;
	Deck deck;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 100, 0));
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton cardUp2_1 = new JButton();
		cardUp2_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp2_1.setBounds(192, 366, 71, 96);
		frame.getContentPane().add(cardUp2_1);
		cardUp2_1.setVisible(false);

		JButton cardUp2_2 = new JButton();
		cardUp2_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp2_2.setBounds(455, 366, 71, 96);
		frame.getContentPane().add(cardUp2_2);
		cardUp2_2.setVisible(false);
		
		JButton cardUp2_3 = new JButton();
		cardUp2_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp2_3.setBounds(718, 366, 71, 96);
		frame.getContentPane().add(cardUp2_3);
		cardUp2_3.setVisible(false);

		JButton cardUp1_1 = new JButton();
		cardUp1_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp1_1.setBounds(192, 149, 71, 96);
		frame.getContentPane().add(cardUp1_1);
		cardUp1_1.setVisible(false);

		JButton cardUp1_2 = new JButton();
		cardUp1_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp1_2.setBounds(455, 149, 71, 96);
		frame.getContentPane().add(cardUp1_2);
		cardUp1_2.setVisible(false);

		JButton cardUp1_3 = new JButton();
		cardUp1_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardUp1_3.setBounds(718, 149, 71, 96);
		frame.getContentPane().add(cardUp1_3);
		cardUp1_3.setVisible(false);
		
		JButton cardDown1_1 = new JButton();
		cardDown1_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown1_1.setBounds(211, 114, 71, 96);
		frame.getContentPane().add(cardDown1_1);
		cardDown1_1.setVisible(false);
		JButton cardDown1_2 = new JButton();
		cardDown1_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown1_2.setBounds(476, 114, 71, 96);
		frame.getContentPane().add(cardDown1_2);
		cardDown1_2.setVisible(false);
		
		JButton cardDown1_3 = new JButton();
		cardDown1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		cardDown1_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown1_3.setBounds(734, 114, 71, 96);
		frame.getContentPane().add(cardDown1_3);
		cardDown1_3.setVisible(false);
		JButton cardDown2_3 = new JButton();
		cardDown2_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown2_3.setBounds(734, 393, 71, 96);
		frame.getContentPane().add(cardDown2_3);
		cardDown2_3.setVisible(false);

		JButton cardDown2_2 = new JButton();
		cardDown2_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown2_2.setBounds(476, 393, 71, 96);
		frame.getContentPane().add(cardDown2_2);
		cardDown2_2.setVisible(false);

		JButton cardDown2_1 = new JButton();
		cardDown2_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardDown2_1.setBounds(211, 393, 71, 96);
		frame.getContentPane().add(cardDown2_1);
		cardDown2_1.setVisible(false);

		JButton cardHand2_1 = new JButton();
		cardHand2_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand2_1.setBounds(280, 508, 71, 96);
		frame.getContentPane().add(cardHand2_1);
		cardHand2_1.setVisible(false);

		JButton cardHand2_2 = new JButton();
		cardHand2_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand2_2.setBounds(366, 508, 71, 96);
		frame.getContentPane().add(cardHand2_2);
		cardHand2_2.setVisible(false);

		JButton cardHand2_3 = new JButton();
		cardHand2_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand2_3.setBounds(455, 508, 71, 96);
		frame.getContentPane().add(cardHand2_3);
		cardHand2_3.setVisible(false);

		JButton cardHand1_1 = new JButton( );
		cardHand1_1.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand1_1.setBounds(280, 0, 71, 96);
		frame.getContentPane().add(cardHand1_1);
		cardHand1_1.setVisible(false);

		JButton cardHand1_2 = new JButton( );
		cardHand1_2.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand1_2.setBounds(366, 0, 71, 96);
		frame.getContentPane().add(cardHand1_2);
		cardHand1_2.setVisible(false);

		JButton cardHand1_3 = new JButton( );
		cardHand1_3.setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
		cardHand1_3.setBounds(455, 0, 71, 96);
		frame.getContentPane().add(cardHand1_3);
		cardHand1_3.setVisible(false);

		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.setBackground(new Color(240, 248, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnNewButton.setForeground(new Color(119, 136, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				btnNewButton.setVisible(false);
				deck=new Deck();
				table=new Table(deck);
				player=new Player("liav",deck,table);
				compuer=new Computer("computer",deck,table);
				
				cardUp2_1.setVisible(true);
				cardUp2_1.setIcon(new ImageIcon(player.cardUp.get(0).ImageLocation));
				cardUp2_2.setVisible(true);
				cardUp2_2.setIcon(new ImageIcon(player.cardUp.get(1).ImageLocation));
				cardUp2_3.setVisible(true);
				cardUp2_3.setIcon(new ImageIcon(player.cardUp.get(2).ImageLocation));
				cardUp1_1.setVisible(true);
				cardUp1_1.setIcon(new ImageIcon(compuer.cardUp.get(0).ImageLocation));
				cardUp1_2.setVisible(true);
				cardUp1_2.setIcon(new ImageIcon(compuer.cardUp.get(1).ImageLocation));
				cardUp1_3.setVisible(true);
				cardUp1_3.setIcon(new ImageIcon(compuer.cardUp.get(2).ImageLocation));
				cardDown1_2.setVisible(true);
				cardDown1_3.setVisible(true);
				cardDown2_3.setVisible(true);
				cardDown2_2.setVisible(true);
				cardDown2_1.setVisible(true);
				cardHand2_1.setVisible(true);
				cardHand2_1.setIcon(new ImageIcon(player.cardHand.get(0).ImageLocation));
				cardHand2_2.setVisible(true);
				cardHand2_2.setIcon(new ImageIcon(player.cardHand.get(1).ImageLocation));
				cardHand2_3.setVisible(true);
				cardHand2_3.setIcon(new ImageIcon(player.cardHand.get(2).ImageLocation));
				cardHand1_1.setVisible(true);
				cardHand1_2.setVisible(true);
				cardHand1_3.setVisible(true);
				cardDown1_1.setVisible(true);

			}
		});
		btnNewButton.setBounds(366, 193, 251, 61);
		frame.getContentPane().add(btnNewButton);
		
		JLabel game = new JLabel("");
		game.setBackground(new Color(0, 0, 0));
		game.setForeground(UIManager.getColor("Button.darkShadow"));
		game.setBounds(313, 241, 71, 96);
		frame.getContentPane().add(game);
		
		
		
		
		
		
	}
}
