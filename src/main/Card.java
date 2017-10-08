package main;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Card implements Comparable {


	private int suit;
	private int value;
    public String ImageLocation;

	public Card(int s, int v) {
		this.suit=s;
		this.value=v;
		ImageLocation="/images/"+suit+""+value+".gif";
	}

	
	public int getValue() {
		return value;
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


}
