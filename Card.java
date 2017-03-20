import java.util.ArrayList;


public class Card implements Comparable {


	private int suit;
	private int value;
	private int id;

	public Card(int s, int v,int id) {
		this.suit=s;
		this.value=v;
		this.id=id;
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


}
