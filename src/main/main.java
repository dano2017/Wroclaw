package main;
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

import listeners.CardFirstListener;
import listeners.CardPlayingListener;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.DropMode;
import javax.swing.JPanel;

import static main.GlobalObjects.*;
import java.awt.Label;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
public class main {


	private JFrame frame;
	int j;

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
	 * @throws InterruptedException 
	 */
	public main(){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){


		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 100, 0));
		frame.setBounds(100, 100, 1150, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Shithead Game"); 
		
		JFrame jFrame_Help=new JFrame();
		
		JLabel Shithead = new JLabel("Shithead");
		Shithead.setBounds(0, 0, 190, 47);
		Shithead.setVerticalAlignment(SwingConstants.BOTTOM);
		Shithead.setFont(new Font("Tahoma", Font.BOLD, 32));
		frame.getContentPane().add(Shithead);

		JLabel by = new JLabel("by Liav Bakish");
		by.setBounds(0, 42, 171, 14);
		by.setFont(new Font("Tahoma", Font.BOLD, 11));
		frame.getContentPane().add(by);


		lost = new JLabel("הפסדת!");
		lost.setForeground(Color.red);
		lost.setVerticalAlignment(SwingConstants.BOTTOM);
		lost.setFont(new Font("Tahoma", Font.BOLD, 32));
		lost.setBounds(460, 115, 190, 47);
		frame.getContentPane().add(lost);
		lost.setVisible(false);

		win = new JLabel("ניצחת!");
		win.setForeground(Color.red);
		win.setVerticalAlignment(SwingConstants.BOTTOM);
		win.setFont(new Font("Tahoma", Font.BOLD, 32));
		win.setBounds(460, 115, 190, 47);
		win.setVisible(false);
		frame.getContentPane().add(win);

		select = new JLabel("אתה לא רשאי לבחור קלף זה");
		select.setFont(new Font("Tahoma", Font.BOLD, 16));
		select.setForeground(new Color(255, 0, 0));
		select.setBounds(60, 335, 220, 32);
		select.setVisible(false);
		frame.getContentPane().add(select);

		turnPlayer = new JLabel("תורך");
		turnPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		turnPlayer.setForeground(new Color(255, 0, 0));
		turnPlayer.setBounds(60, 400, 220, 32);
		turnPlayer.setVisible(false);
		frame.getContentPane().add(turnPlayer);

		deckEnd = new JLabel("החפיסה נגמרה!");
		deckEnd.setFont(new Font("Tahoma", Font.BOLD, 16));
		deckEnd.setForeground(new Color(255, 0, 0));
		deckEnd.setBounds(60, 300, 220, 32);
		deckEnd.setVisible(false);
		frame.getContentPane().add(deckEnd);

		turnComputer = new JLabel("תורו");
		turnComputer.setFont(new Font("Tahoma", Font.BOLD, 16));
		turnComputer.setForeground(new Color(255, 0, 0));
		turnComputer.setBounds(60, 400, 220, 32);
		turnComputer.setVisible(false);
		frame.getContentPane().add(turnComputer);

		lostRound = new JLabel("הפסדת בסיבוב, הערימה עברה אליך");
		lostRound.setFont(new Font("Tahoma", Font.BOLD, 14));
		lostRound.setForeground(new Color(255, 0, 0));
		lostRound.setBounds(60, 335, 300, 32);
		lostRound.setVisible(false);
		frame.getContentPane().add(lostRound);

		computerlost = new JLabel("היריב הפסיד בסיבוב, הערימה עברה אליו");
		computerlost.setFont(new Font("Tahoma", Font.BOLD, 14));
		computerlost.setForeground(new Color(255, 0, 0));
		computerlost.setBounds(60, 335, 300, 32);
		computerlost.setVisible(false);
		frame.getContentPane().add(computerlost);

		fourCards = new JLabel("הוגש רצף של 4 קלפים זהים , הערימה נמחקה ותור מחדש");
		fourCards.setFont(new Font("Tahoma", Font.BOLD, 14));
		fourCards.setForeground(new Color(255, 0, 0));
		fourCards.setBounds(60, 340, 450, 32);
		fourCards.setVisible(false);
		frame.getContentPane().add(fourCards);

		tenCard = new JLabel("הוגש קלף  10, הערימה נמחקה ותור מחדש");
		tenCard.setFont(new Font("Tahoma", Font.BOLD, 14));
		tenCard.setForeground(new Color(255, 0, 0));
		tenCard.setBounds(60, 335, 300, 32);
		tenCard.setVisible(false);
		frame.getContentPane().add(tenCard);

		cardsComputer = new JLabel();
		cardsComputer.setBackground(Color.BLACK);
		cardsComputer.setText("הקלפים של היריב");
		cardsComputer.setFont(new Font("Tahoma", Font.BOLD, 16));
		cardsComputer.setBounds(559, 26, 136, 20);
		frame.getContentPane().add(cardsComputer);
		cardsComputer.setVisible(false);

		cardsPlayer = new JLabel();
		cardsPlayer.setText("הקלפים שלך");
		cardsPlayer.setFont(new Font("Tahoma", Font.BOLD, 16));
		cardsPlayer.setBackground(Color.BLACK);
		cardsPlayer.setBounds(559, 541, 99, 20);
		frame.getContentPane().add(cardsPlayer);
		cardsPlayer.setVisible(false);

		Change = new JButton("סיימתי החלפות");
		Change.setFont(new Font("Tahoma", Font.BOLD, 11));
		Change.setForeground(Color.BLACK);
		Change.setBounds(536, 581, 159, 23);
		frame.getContentPane().add(Change);
		Change.setVisible(false);

		cardTable = new JLabel("");
		cardTable.setBackground(Color.BLACK);
		cardTable.setBounds(290, 233, 71, 96);
		frame.getContentPane().add(cardTable);
		cardTable.setVisible(false);

		JLabel game = new JLabel("");
		game.setBounds(290, 67, 800, 500);
		game.setIcon(new ImageIcon(main.class.getResource("/images/‏‏game.JPG")));
		frame.getContentPane().add(game);


		JMenu Menu = new JMenu();
		JMenuBar jMenuBar1 =new JMenuBar();
		Menu.setText("מידע נוסף");
		JMenuItem Help= new JMenuItem();
		Help.setText("הוראות המשחק");
		JMenuItem About=new JMenuItem();
		About.setText("יוצר המשחק");
		JMenuItem Exit=new JMenuItem();
		Exit.setText("יציאה");
		Help.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				game.setVisible(true);
				jFrame_Help.setBounds(290, 67, 800, 500);
				jFrame_Help.setVisible(true);
				jFrame_Help.getContentPane().add(game);
			}

		});
		About.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "נוצר על ידי ליאב בקיש");     
			}
		});
		Exit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				JOptionPane.showMessageDialog(null,"תודה ששיחקת, מקווה שנהנית ותחזור בקרוב!");
				System.exit(0);            }
		});

		Menu.add(Help);
		Menu.add(About);
		Menu.add(Exit);
		jMenuBar1.add(Menu);
		frame.setJMenuBar(jMenuBar1);

		start = new JButton("התחל משחק");
		start.setBounds(450, 550, 251, 61);
		start.setBackground(new Color(240, 255, 255));
		start.setFont(new Font("Tahoma", Font.BOLD, 24));
		start.setForeground(new Color(119, 136, 153));
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0){
				lost.setVisible(false);
				win.setVisible(false);
				game.setVisible(false);
				state = 1;
				start.setVisible(false);
				deck=new Deck();
				table=new Table();
				player=new Player();
				computer=new Computer();

				player_up = new JButton[3];
				computer_up = new JButton[3];
				player_down = new JButton[3];
				computer_down = new JButton[3];
				player_hand = new JButton[52];
				computer_hand = new JButton[52];

				int k=0;

				for(int i=0;i<45;i++){
					player_hand[i]=new JButton();
					player_hand[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
					player_hand[i].setBounds(150+k, 508, 71, 96);
					frame.getContentPane().add(player_hand[i]);
					player_hand[i].setVisible(false);
					computer_hand[i]=new JButton();
					computer_hand[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
					computer_hand[i].setBounds(150+k, 0, 71, 96);
					frame.getContentPane().add(computer_hand[i]);
					computer_hand[i].setVisible(false);
					k+=40;
				}
				k=0;
				for(int i=0;i<3;i++){
					player_hand[i].setIcon(new ImageIcon(main.class.getResource(player.cardHand.get(i).ImageLocation)));
					player_hand[i].setVisible(true);
					computer_hand[i].setVisible(true);
					player_up[i]=new JButton();
					player_up[i].setIcon(new ImageIcon(main.class.getResource(player.cardUp.get(i).ImageLocation)));
					player_up[i].setBounds(192+k, 366, 71, 96);
					frame.getContentPane().add(player_up[i]);
					computer_up[i]=new JButton();
					computer_up[i].setIcon(new ImageIcon(main.class.getResource(computer.cardUp.get(i).ImageLocation)));
					computer_up[i].setBounds(192+k, 149, 71, 96);
					frame.getContentPane().add(computer_up[i]);
					computer_down[i]=new JButton();
					computer_down[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
					computer_down[i].setBounds(211+k, 114, 71, 96);
					frame.getContentPane().add(computer_down[i]);
					player_down[i]=new JButton();
					player_down[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
					player_down[i].setBounds(211+k, 393, 71, 96);
					frame.getContentPane().add(player_down[i]);

					k+=260;
				}

				player_hand[0].addActionListener(new CardFirstListener(0,0));
				player_hand[1].addActionListener(new CardFirstListener(0,1));
				player_hand[2].addActionListener(new CardFirstListener(0,2));
				player_up[0].addActionListener(new CardFirstListener(1,0));
				player_up[1].addActionListener(new CardFirstListener(1,1));
				player_up[2].addActionListener(new CardFirstListener(1,2));

				cardsComputer.setVisible(true);
				cardsPlayer.setVisible(true);
				Change.setVisible(true);

				Change.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0){
						turnPlayer.setVisible(true);
						state = 2;
						Change.setVisible(false);
						computer.Choose();
						for(int i=0;i<3;i++){
							computer_up[i].setIcon(new ImageIcon(main.class.getResource(computer.cardUp.get(i).ImageLocation)));
						}
						if(table.cardTable()!=null){
							cardTable.setIcon(new ImageIcon(main.class.getResource(table.cardTable().ImageLocation)));
							cardTable.setVisible(true);

						}
						else
							cardTable.setVisible(false);

					}
				});
				turn = true;
				for(j=0;j<45;j++){
					player_hand[j].addActionListener(new CardPlayingListener(j));
				}
				for(j=0;j<3;j++){
					player_up[j].addActionListener(new CardPlayingListener(j+100));
					player_down[j].addActionListener(new CardPlayingListener(j+103));

				}
			}
		});

		frame.getContentPane().add(start);


	}
}
