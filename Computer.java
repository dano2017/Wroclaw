
import java.util.ArrayList;
import java.util.Collections;


public class Computer  {
	private String name;
	private ArrayList<Card> cardHand;
	private ArrayList<Card> cardUp;
	private ArrayList<Card> cardDown;
	private Deck deck;
	private Table table;

	public Computer(String name, Deck k,Table t) {
		this.deck=k;
		this.name = name;
		this.cardHand=deck.getNextCards(3);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);

	}
	@SuppressWarnings("unchecked")
	public void Choose(){
		Card c,x;
		int countSpecialCard=0;
		boolean hasSpecialCard=false;
		for(int i=0;i<3;i++){
			c=cardUp.get(i);
			if(!c.specialCard()){
				for(int j=0;j<3;j++){
					x=cardHand.get(j);
					if(x.specialCard()){
						countSpecialCard++;
						cardUp.set(i, x);
						cardHand.set(j, c);
						hasSpecialCard=true;
						break;
					}
				}
				if(!hasSpecialCard){
					Collections.sort(cardHand);
					x=cardHand.get(2);
					if(c.getValue()<x.getValue()){
						cardUp.set(i, x);
						cardHand.set(2,c);
					}
				}
				hasSpecialCard=false;

			}
			else
				countSpecialCard++;
		}
		System.out.println("count "+countSpecialCard);
	}

	public void Attack(){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		String string=name+ " Hand: ";
		for(Card c:cardHand){
			string+=c;
		}
		string+=" cardUp ";
		for(Card c:cardUp){
			string+=c;
		}
		return string;
	}
}
