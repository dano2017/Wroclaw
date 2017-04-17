
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	public ArrayList<Card> cardHand;
	public ArrayList<Card> cardUp;
	public ArrayList<Card> cardDown;
	private Deck deck;
	private Table table;


	public Player(String name, Deck k,Table t) {
		this.deck=k;
		this.name = name;
		this.cardHand=deck.getNextCards(3);
		sort(cardHand);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);
		this.table=t;

	}
	//replace, before the game we allow the players to replace the cards of their hands with the cards on the table with face to up
	public void Choose(){
		System.out.println(name+" replace the cards, 0=exit or choose(HAND)");
		Scanner console = new Scanner(System.in);
		int x = console.nextInt(); 
		while(x!=0){
			System.out.println("instead of choose(UP)");
			int y=console.nextInt();
			Card c=cardHand.get(x-1);
			cardHand.set(x-1, cardUp.get(y-1));
			cardUp.set(y-1, c);
			System.out.println(this.toString());
			System.out.println(name+" replace the cards, 0=exit or choose(HAND)");
			x = console.nextInt(); 
		}
	}
	//attack - the player need to choose a card depending on the card on top of the pile. the function checks if it is legal.
	public void Attack(){
		System.out.println(this.toString());
		if(!cardHand.isEmpty()){
			System.out.println("choose(HAND)");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardHand.get(x-1);
			System.out.println("your choosing "+c.toString());
			//checks if the card fills the rules
			if(table.add(c)){
				cardHand.remove(x-1);
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
				table.check4cards();
			}
			else{
				//if the player choose incorrect card, which means lost, so the all pile will join to his hand. and the pile is empty.
				add(table.getCards());	

			}
			sort(cardHand);
		}
		else if(!cardUp.isEmpty()){
			System.out.println("choose(UP)");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardUp.get(x-1);
			System.out.println("your choosing "+c.toString());
			if(!table.add(c)){
				//if the card is not match to the rules of the game, we add the all pile to his hand.
				cardHand.add(c);
				add(table.getCards());
				//				ArrayList<Card> temp=table.getCards();
				//				int size=temp.size();
				//				for(int i=0;i<size;i++){
				//					cardHand.add(temp.get(i));
				//				}
				//				cardHand.add(c);
				sort(cardHand);
			}

			table.check4cards();
			cardUp.remove(x-1);



		}
		else{
			System.out.println("choose(Down) "+cardDown.size()+" cards");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardDown.get(x-1);
			System.out.println("your choosing "+c.toString());
			if(!table.add(c)){		
				cardHand.add(c);
				add(table.getCards());
				//				ArrayList<Card> temp=table.getCards();
				//				int size=temp.size();
				//				for(int i=0;i<size;i++){
				//					cardHand.add(temp.get(i));
				//				}
				//				cardHand.add(c);
				sort(cardHand);
			}
			table.check4cards();
			cardDown.remove(x-1);


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
	//checks how many same cards are in the hand.
	private int checkSameCards(Card c) {
		int count=0;
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
		return cardHand.size()+cardUp.size()+cardDown.size();
	}

	public String toString(){
		String string=name+ "\nHAND:";
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
		string+="\nDOWN: "+cardDown.size()+" cards";
		return string;
	}

}