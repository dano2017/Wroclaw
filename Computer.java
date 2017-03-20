
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
		sort(cardHand);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);
		this.table=t;

	}
	public void Choose(){
		boolean done;
		for(int i=0;i<3;i++){
			done=false;
			if(cardUp.get(i).getValue()!=2 && cardUp.get(i).getValue()!=3 && cardUp.get(i).getValue()!=10){
				for(int j=0;j<3;j++){
					if(cardHand.get(j).getValue()==2 || cardHand.get(j).getValue()==3 || cardHand.get(j).getValue()==10){
						Card c=cardHand.get(j);
						cardHand.set(j, cardUp.get(i));
						cardUp.set(i,c);
						sort(cardHand);
						done=true;
						break;
					}
				}
				if(!done){
					if(cardUp.get(i).getValue()<cardHand.get(cardHand.size()-1).getValue()){
						Card c=cardHand.get(cardHand.size()-1);
						cardHand.set(cardHand.size()-1, cardUp.get(i));
						cardUp.set(i,c);
						sort(cardHand);
					}
				}

			}
		}
		System.out.println(this.toString());
	}
	public void Attack(){
		System.out.println(this.toString());
		Card c=table.cardTable();
		boolean done=false;
		Card temp=null;
		if(!cardHand.isEmpty()){
			int size=cardHand.size();
			if(c==null || c.getValue()==2){
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
			else if(c.getValue()==7){
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
					if(cardHand.get(i).getValue()!=2 && cardHand.get(i).getValue()!=3 && cardHand.get(i).getValue()!=10 && cardHand.get(i).getValue()>=c.getValue()){
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
			if(done){
				table.add(temp);
				System.out.println("computer choosing "+temp.toString());
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
				table.check4cards();
			}
			else
				add(table.getCards());	
			sort(cardHand);
		}
		else if(!cardUp.isEmpty()){
			if(c==null || c.getValue()==2){
				temp=cardUp.get(cardUp.size()-1);
				cardUp.remove(cardUp.size()-1);
				done=true;

			}
			else{
				int size=cardUp.size();
				for(int i=0;i<size;i++){
					if(cardUp.get(i).getValue()>c.getValue() && cardUp.get(i).getValue()!= 10 && cardUp.get(i).getValue()!= 2 && cardUp.get(i).getValue()!= 3 ){
						temp=cardUp.get(i);
						cardUp.remove(i);
						done=true;
						break;
					}
				}
				if(!done){
					for(int i=0;i<size;i++){
						if(cardUp.get(i).getValue()>c.getValue()){
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
			if(done){
				table.add(temp);
				System.out.println("computer choosing "+temp.toString());
				table.check4cards();
			}
			else{
				add(table.getCards());
				cardHand.add(cardUp.get(0));
				sort(cardHand);
				cardUp.remove(0);
			}

		}
		else{
			Card rnd=cardDown.get(0);
			if(c==null || c.getValue()==2 || c.getValue()<rnd.getValue()){
				table.add(rnd);
				System.out.println("computer choosing "+rnd.toString());
				table.check4cards();
			}
			else{
				add(table.getCards());
				cardHand.add(cardDown.get(0));
				sort(cardHand);
			}
			cardDown.remove(0);

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
		//		ArrayList<Card> res = new ArrayList<Card>();
		//		int size=cardHand.size();
		//		for (int i = 0; i < size; i++) {
		//			res.add(cardHand.get(0));
		//			cardHand.remove(0);
		//		}
		//		size=b.size();
		//		for (int i = 0; i < size; i++) {
		//			res.add(b.get(0));
		//			b.remove(0);
		//		}
		//		return res;
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
		return string;
	}
}
