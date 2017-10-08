package main;
import static main.GlobalObjects.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	public ArrayList<Card> cardHand;
	public ArrayList<Card> cardUp;
	public ArrayList<Card> cardDown;

	public Player() {
		this.cardHand=deck.getNextCards(3);
		sort(cardHand);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);

	}
	//replace, before the game we allow the players to replace the cards of their hands with the cards on the table with face to up
	public void Choose(int x,int y){
		Card c=cardHand.get(x);
		cardHand.set(x, cardUp.get(y));
		cardUp.set(y, c);

	}
	//attack - the player need to choose a card depending on the card on top of the pile. the function checks if it is legal.
	public void Attack(int x){
		if(!cardHand.isEmpty()){
			Card c=cardHand.get(x);
			if(c.getValue()==10)
				tenCard.setVisible(true);
			else
				tenCard.setVisible(false);
			System.out.println("hand - your choosing "+c.toString());
			//checks if the card fills the rules
			if(table.add(c)){
				cardHand.remove(x);
				int same;
				//checks if there are more same cards in the hand
				same=checkSameCards(c);
				int size=cardHand.size();
				for(int i=0;i<size && same!=0 ;i++){
					if(cardHand.get(i).getValue()==c.getValue()){
						//adds the card to the pile
						table.addCardSame(cardHand.get(i));
						cardHand.remove(i);
						same--;
						i--;
					}
				}
				//while the player has less than 3 cards and the pile is not empty, fill up to 3 cards.
				while(deck.hasNext() && cardHand.size()<3){
					cardHand.add(deck.getNextCard());
				}
				//checks if there are 4 same cards, if yes, empty the pile and act again.
				if(table.check4cards())
					fourCards.setVisible(true);
			}
			else{
				lostRound.setVisible(true);
				System.out.println("lost");
				//if the player choose incorrect card, which means lost, so the all pile will join to his hand. and the pile is empty.
				add(table.getCards());	
				table.clear();

			}
			sort(cardHand);
		}
		else if(!cardUp.isEmpty()){
			Card c;
			if(cardUp.size()==2){
				if(x!=0){
					x=x-1;
				}
			}
			else if(cardUp.size()==1){
				x=0;
			}
			c=cardUp.get(x);
			System.out.println("up - your choosing "+c.toString());
			if(!table.add(c)){
				System.out.println("lost");

				//if the card is not match to the rules of the game, we add the all pile to his hand.
				cardHand.add(c);
				add(table.getCards());
				table.clear();
				sort(cardHand);
			}
			else
				table.check4cards();
			cardUp.remove(x);



		}
		else{
			Card c;
			if(cardDown.size()==2){
				if(x!=0){
					x=x-1;
				}
			}
			else if(cardDown.size()==1){
				x=0;
			}
			c=cardDown.get(x);
			System.out.println("down - your choosing "+c.toString());
			if(!table.add(c)){	
				System.out.println("lost");

				cardHand.add(c);
				add(table.getCards());
				sort(cardHand);
				table.clear();
			}
			else
				table.check4cards();
			cardDown.remove(x);


		}
	}
	//end Attack
	private void sort(ArrayList<Card> c) {
		int size=c.size();
		if(size>1){
			ArrayList<Card> cardHandNew = new ArrayList<Card>();
			int valueMin;
			int indexMin;
			while(cardHandNew.size()!=size){
				valueMin=c.get(0).getValue();
				indexMin=0;
				for(int i=1;i<c.size();i++){
					if(c.get(i).getValue()<valueMin){
						indexMin=i;
						valueMin=c.get(i).getValue();
					}
				}
				cardHandNew.add(c.get(indexMin));
				c.remove(indexMin);

			}
			cardHand.clear();
			for(int i=0;i<size;i++){
				cardHand.add(cardHandNew.get(i));
			}
			cardHandNew.clear();
		}
	}
	//checks how many same cards are in the hand.
	private int checkSameCards(Card c) {
		int count=0;
		if(c.specialCard())
			return count;
		int value=c.getValue();
		for(int i=0;i<cardHand.size();i++){
			if(cardHand.get(i).getValue()== value){
				count++;
			}
		}
		return count;
	}
	//the function adds to the hand all the cards from the pile
	public void add(ArrayList<Card> b) {
		int size=b.size();
		for(int i=0;i<size;i++){
			cardHand.add(b.get(i));
		}
	}

	public ArrayList<Card> getCardHand() {
		return cardHand;
	}

	public void setCardHand(ArrayList<Card> cardHand) {
		this.cardHand = cardHand;
	}

	public ArrayList<Card> getCardUp() {
		return cardUp;
	}

	public void setCardUp(ArrayList<Card> cardUp) {
		this.cardUp = cardUp;
	}

	public ArrayList<Card> getCardDown() {
		return cardDown;
	}

	public void setCardDown(ArrayList<Card> cardDown) {
		this.cardDown = cardDown;
	}

	public int getNumberOfCards() {
		return cardHand.size()+cardUp.size()+cardDown.size();
	}

	public String toString(){
		String string="player \nHAND:";
		int count=1;
		for(Card c:cardHand){
			string+="("+count+")";
			string+=c;
			string+="|";
			count++;
		}
		count=1;
		string+="\nUP:";
		for(Card c:cardUp){
			string+="("+count+")";
			string+=c;
			string+="|";
			count++;
		}
		string+="\nDown:";
		for(Card c:cardDown){
			string+="("+count+")";
			string+=c;
			string+="|";
			count++;
		}
		return string;
	}

}