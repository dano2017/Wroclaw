
import java.util.ArrayList;

public class Table {
	private ArrayList<Card> cards;
	private Deck deck;
	private boolean turn;

	public Table(Deck d){
		this.deck=d;
		this.cards = new ArrayList<>();
		Card c=deck.getNextCard();
		if(c.getValue()!=10)
			cards.add(0, c);
	}
	public boolean add(Card c){
		turn=false;
		int size=cards.size();
		//if there are no cards, so add a card to the pile
		// we must to make sure if the card is number 10, if the pile is empty and add 10 - it is incorrect, we need to leave the pile still empty.
		if(size==0){
			if(c.getValue()!=10){
				if(c.getValue()==8)
					turn=true;
				cards.add(size,c);
				return true;
			}
			else{
				turn=true;
				return true;
			}

		}
		//if the card is 2, so it has same meaning as empty pile, so add the card to the pile 
		Card prev=cards.get(size-1);
		if(c.getValue()!=10){
			if(prev.getValue()==2){
				if(c.getValue()==8)
					turn=true;
				cards.add(size,c);
				return true;
			}
		}
		//cases for special card
		if(c.specialCard()){
			switch(c.getValue()){
			case 2: cards.add(size,c);
			return true;
			//if we choose card 3, we replace the place of 3 with the last card on top of the pile
			case 3: cards.add(size,cards.get(size-1));
			cards.set(size-1, c);
			return true;
			//if we choose the card of 7, we will make sure if the last card of the pile is less or same as 7.
			case 7: if(7>=prev.getValue()){
				cards.add(size,c);
				return true;
			}
			else 
				return false;
			//if we choose the card number 8, we will make sure that the last cade of the pile is same as 8 or less but different than 7 and we give another turn to the player.
			case 8: if(8>=prev.getValue() && prev.getValue()!=7){
				cards.add(size,c);
				turn=true;
				return true;
			}
			else 
				return false;
			//when we choose the card 10 - we erase the pile and give another turn.
			case 10: cards.clear();
			turn=true;
			return true;
			}

		}
		//regular cases
		else{
			//we check if the previous card is 7, if yes, so we need to put the card number 7 or less.
			if(prev.getValue()==7){
				if(c.getValue()<=prev.getValue()){
					cards.add(size,c);
					return true;
				}
				else
					return false;
			}
			//base case, with no spechial cases, if the current card is higher or same as the last card of the pile, if yes, so add the card to the pile
			if(c.getValue()>=prev.getValue()){
				cards.add(size,c);
				return true;
			}
			else
				return false;

		}
		return false;
	}


	//the function sends the array of the cards from the pile and erase it ( which means that the player lost and takes to his hand all the cards from the pile)
	public ArrayList<Card> getCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		int size=cards.size();
		for(int i=0;i<size;i++){
			temp.add(cards.get(0));
			cards.remove(0);
		}
		return temp;
	}
	//returns the last card from the pile
	public Card cardTable(){
		if(cards.size()>0)
			return cards.get(cards.size()-1);
		return null;
	}
	//prints all the cards from the pile
	public void cardsInTable(){
		System.out.println("deck: "+cards.toString());
	}
	public boolean turn(){
		return turn;
	}
	//adds similar cards to the chosen card' we need to make sure that it matches to the rules of number 3 and 10.
	public void addCardSame(Card c){
		if(c.getValue()!=10){
			if(c.getValue()!=3)
				cards.add(c);
			else{
				int size=cards.size();
				cards.add(size,cards.get(size-1));
				cards.set(size-1, c);
			}
		}

	}
	//checks if there are 4 same cards in the pile, if yes, we need to erase the pile and give another turn.
	public void check4cards(){
		int size=cards.size();
		if(size>3){
			int count=0;
			for(int i=size-4;i<size-1;i++){
				if(cards.get(i).getValue()==cards.get(i+1).getValue())
					count++;
			}
			if(count==3){
				turn=true;
				cards.clear();
			}

		}
	}


}
