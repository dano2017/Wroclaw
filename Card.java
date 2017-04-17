import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Card implements Comparable {


	private int suit;
	private int value;
	private int id;
	public ImageIcon card_front;
    public ImageIcon card_back;
    String ImageLocation;

	public Card(int s, int v,int id) {
		this.suit=s;
		this.value=v;
		this.id=id;
		ImageLocation="src/images/"+suit+""+value+".gif";
		card_back =  new ImageIcon ("src/images/back.png");
		card_front = new ImageIcon (ImageLocation);
	}

	public int getSuit() {
		return suit;
	}
	public int getValue() {
		return value;
	}

	private void setSuit(int s) {
		this.suit = s;
	}
	private void setValue(int v) {
		this.value = v;
	}

	public boolean compareTo(Card c) {
		if (c.value > value || (c.value == value && suit != c.suit)) 
			return true;
		else 
			return false;
	} 
	public boolean specialCard(){
		if(this.value==2||this.value==3||this.value==7||this.value==10||this.value==8)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "[" + value + "," + suit + "]";
	}

	@Override
	public int compareTo(Object o) {
		int temp=((Card)o).getValue();
		return this.value-temp;
		
	}
	public ArrayList<Card> cardSort(){
		return null;
		
		
	}
	public static void main(String[] args){
		Card c = new Card(0,14,1);
		System.out.println(c.ImageLocation);
		
	}


}
