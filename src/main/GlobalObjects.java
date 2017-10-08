package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GlobalObjects {
	public static int state = 0;
	public static int cardIndex;
	public static int firstindex = -1;
	public static int firstindex2 = -1;
	public static JButton[] player_up;
	public static JButton[] computer_up;
	public static JButton[] player_down;
	public static JButton[] computer_down;
	public static JButton[] player_hand;
	public static JButton[] computer_hand;
	public static JLabel cardTable;
	public static Player player;
	public static Computer computer;
	public static Table table;
	public static Deck deck;
	public static boolean turn;
	public static boolean wait;
	public static JLabel win;
	public static JLabel lost;
	public static JLabel select;
	public static JButton start;
	public static JLabel cardsPlayer;
	public static JLabel cardsComputer;
	public static JButton Change;
	public static JLabel tenCard;
	public static JLabel fourCards;
	public static JLabel computerlost;
	public static JLabel lostRound;
	public static JLabel turnPlayer;
	public static JLabel turnComputer;
	public static JLabel deckEnd;
}

