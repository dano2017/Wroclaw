package main;
import static main.GlobalObjects.*;
import java.util.ArrayList;
import java.util.Collections;


public class Computer  {
	public ArrayList<Card> cardHand;
	public ArrayList<Card> cardUp;
	public ArrayList<Card> cardDown;

	public Computer() {

		this.cardHand=deck.getNextCards(3);
		sort(cardHand);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);

	}
	public void Choose(){
		boolean done;
		Card c;
		for(int i=0;i<3;i++){
			done=false;
			if(cardUp.get(i).getValue()!=2 && cardUp.get(i).getValue()!=3 && cardUp.get(i).getValue()!=10){
				for(int j=0;j<3;j++){
					if(cardHand.get(j).getValue()==2 || cardHand.get(j).getValue()==3 || cardHand.get(j).getValue()==10){
						c=cardHand.get(j);
						cardHand.set(j, cardUp.get(i));
						cardUp.set(i,c);
						sort(cardHand);
						done=true;
						break;
					}
				}
				if(!done){
					if(cardUp.get(i).getValue()<cardHand.get(cardHand.size()-1).getValue()){
						c=cardHand.get(cardHand.size()-1);
						cardHand.set(cardHand.size()-1, cardUp.get(i));
						cardUp.set(i,c);
						sort(cardHand);
					}
				}

			}
		}
	}
	public void Attack(int numOfCardsOfPlayer){
		Card prev=table.cardTable();
		boolean done=false;
		if(!cardHand.isEmpty()){
			Card temp=cardHand.get(0);
			int size=cardHand.size();
			if(numOfCardsOfPlayer<3 && prev.getValue()!=7){
					temp=cardHand.get(size-1);
					cardHand.remove(size-1);
					done=true;
			}
			else{
				if(prev==null || prev.getValue()==2){
					for(int i=0;i<size;i++){
						if(cardHand.get(i).getValue()!=2 && cardHand.get(i).getValue()!=3 && cardHand.get(i).getValue()!=10){
							temp=cardHand.get(i);
							cardHand.remove(i);
							done=true;
							break;
						}	
					}
					if(!done){
						temp=cardHand.get(0);
						cardHand.remove(0);
						done=true;
					}
				}
				else if(prev.getValue()==7){
					for(int i=0;i<size;i++){
						if(cardHand.get(i).getValue()!=2 && cardHand.get(i).getValue()!=3 && cardHand.get(i).getValue()!=10 && cardHand.get(i).getValue()<8){
							temp=cardHand.get(i);
							cardHand.remove(i);
							done=true;
							break;
						}	
					}
					if(!done){
						for(int i=0;i<size;i++){
							if(cardHand.get(i).getValue()==2 || cardHand.get(i).getValue()==3 || cardHand.get(i).getValue()==10){
								temp=cardHand.get(i);
								cardHand.remove(i);
								done=true;
								break;
							}
						}
					}
				}
				else{
					for(int i=0;i<size;i++){
						if(cardHand.get(i).getValue()!=2 && cardHand.get(i).getValue()!=3 && cardHand.get(i).getValue()!=10 && cardHand.get(i).getValue()>=prev.getValue()){
							temp=cardHand.get(i);
							cardHand.remove(i);
							done=true;
							break;
						}	
					}
					if(!done){
						for(int i=0;i<size;i++){
							if(cardHand.get(i).getValue()==2 || cardHand.get(i).getValue()==3 || cardHand.get(i).getValue()==10){
								temp=cardHand.get(i);
								cardHand.remove(i);
								done=true;
								break;
							}
						}
					}
				}
				if(table.add(temp)){
					System.out.println("hand - computer choosing "+temp.toString());
					int same;
					//checks if there are more same cards in the hand
					same=checkSameCards(temp);
					for(int j=0;j<size && same!=0 ;j++){
						if(cardHand.get(j).getValue()==temp.getValue()){
							//adds the card to the pile
							table.addCardSame(cardHand.get(j));
							cardHand.remove(j);
							same--;
							j--;
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
					computerlost.setVisible(true);
					if(done)
						System.out.println("Error!!!");
					System.out.println("hand - lost - computer choosing "+temp.toString());
					add(table.getCards());	
					table.clear();
				}
				sort(cardHand);
			}
		}//end cardHand
		else if(!cardUp.isEmpty()){
			Card temp=cardUp.get(0);
			int size=cardUp.size();
			if(prev==null || prev.getValue()==2){
				temp=cardUp.get(cardUp.size()-1);
				cardUp.remove(cardUp.size()-1);
				done=true;
			}
			else if(prev.getValue()==7){
				for(int i=0;i<size;i++){
					if(cardUp.get(i).getValue()!=2 && cardUp.get(i).getValue()!=3 && cardUp.get(i).getValue()!=10 && cardUp.get(i).getValue()<8){
						temp=cardUp.get(i);
						cardUp.remove(i);
						done=true;
						break;
					}	
				}
				if(!done){
					for(int i=0;i<size;i++){
						if(cardUp.get(i).getValue()==2 || cardUp.get(i).getValue()==3 || cardUp.get(i).getValue()==10){
							temp=cardUp.get(i);
							cardUp.remove(i);
							done=true;
							break;
						}
					}
				}
			}
			else{
				for(int i=0;i<size;i++){
					if(cardUp.get(i).getValue()>=prev.getValue() && cardUp.get(i).getValue()!= 10 && cardUp.get(i).getValue()!= 2 && cardUp.get(i).getValue()!= 3 ){
						temp=cardUp.get(i);
						cardUp.remove(i);
						done=true;
						break;
					}
				}
				if(!done){
					for(int i=0;i<size;i++){
						if(cardUp.get(i).getValue()>=prev.getValue()){
							temp=cardUp.get(i);
							cardUp.remove(i);
							done=true;
							break;
						}
					}
					if(!done){
						for(int i=0;i<size;i++){
							if(cardUp.get(i).getValue()==3 || cardUp.get(i).getValue()==2 || cardUp.get(i).getValue()==10){
								temp=cardUp.get(i);
								cardUp.remove(i);
								done=true;
								break;
							}
						}

					}
				}
			}
			if(done && table.add(temp)){
				System.out.println("up - computer choosing "+temp.toString());
				table.check4cards();
			}
			else{
				System.out.println("up - lost - computer choosing "+temp.toString());

				add(table.getCards());
				table.clear();
				cardHand.add(temp);
				sort(cardHand);
				cardUp.remove(0);
			}

		}//end cardUp
		else{
			Card rnd=cardDown.get(0);
			if(table.add(rnd)){
				System.out.println("down - computer choosing "+rnd.toString());
				table.check4cards();
			}
			else{
				System.out.println("down - lost - computer choosing "+rnd.toString());
				add(table.getCards());
				table.clear();
				cardHand.add(cardDown.get(0));
				sort(cardHand);
			}
			cardDown.remove(0);

		}//end cardDown

	}
	//checks how many same cards are in the hand.
	private int checkSameCards(Card c) {
		int count=0;
		if(c.getValue()==2 || c.getValue()==3 || c.getValue()==7 || c.getValue()==8 || c.getValue()==10 )
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
		return cardHand.size() + cardUp.size() + cardDown.size();
	}

	public String toString(){
		String string= "computer \nHAND:"+cardHand.size()+" cards";
		int count=1;
		string+="\nUP:";
		for(Card c:cardUp){
			string+="("+count+")";
			string+=c;
			string+="|";
			count++;
		}
		string+="\nDOWN:"+cardDown.size()+" cards";
		return string;
	}
}
