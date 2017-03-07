
import java.util.ArrayList;
import java.util.Scanner;

public class Player {

	private String name;
	private ArrayList<Card> cardHand;
	private ArrayList<Card> cardUp;
	private ArrayList<Card> cardDown;
	private Deck deck;
	private Table table;
	private ArrayList<Card> cardSame;


	public Player(String name, Deck k,Table t) {
		this.deck=k;
		this.name = name;
		this.cardHand=deck.getNextCards(3);
		this.cardUp=deck.getNextCards(3);
		this.cardDown=deck.getNextCards(3);
		this.table=t;
		this.cardSame= new ArrayList<Card>();

	}
	public void Choose(){
		System.out.println(name+" Change 0 = EXIT, Choose (HAND)");
		Scanner console = new Scanner(System.in);
		int x = console.nextInt(); 
		while(x!=0){
			System.out.println("instead of Choose (UP)");
			int y=console.nextInt();
			Card c=cardHand.get(x-1);
			cardHand.set(x-1, cardUp.get(y-1));
			cardUp.set(y-1, c);
			System.out.println(this.toString());
			System.out.println(name+" Change 0 = EXIT,Choose(HAND)");
			x = console.nextInt(); 
		}
	}
	public void Attack(){
		System.out.println(this.toString());
		if(!cardHand.isEmpty()){
			System.out.println("Choose(HAND)");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardHand.get(x-1);
			System.out.println("your choosing "+c.toString());
			//בודק אם הקלף הנבחר עומד בחוקי המשחק
			if(table.add(c)){
				cardHand.remove(x-1);
				int same;
				//בודק אם יש עוד קלפים זהים ביד
				same=checkSameCards(c);
				int size=cardHand.size();
				for(int i=0;i<size && same!=0 ;i++){
					if(cardHand.get(i).getValue()==c.getValue()){
						//מוסיף את הקלף לערימה בשולחן
						table.addCardSame(cardHand.get(i));
						cardHand.remove(i);
						same--;
						i--;
					}
				}
				while(deck.hasNext() && cardHand.size()<3){
					cardHand.add(deck.getNextCard());
				}
				table.check4cards();
			}
			else{
				cardHand=res(table.getCards());	
			}
		}
		else if(!cardUp.isEmpty()){
			System.out.println("Choose(UP)");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardUp.get(x-1);
			System.out.println("your choosing "+c.toString());
			if(!table.add(c)){					
				ArrayList<Card> temp=table.getCards();
				int size=temp.size();
				for(int i=0;i<size;i++){
					cardHand.add(temp.get(i));
				}
				cardHand.add(c);
			}
			cardUp.remove(x-1);

		}
		else{
			System.out.println("Choose(Down) "+cardDown.size()+" cards");
			Scanner console = new Scanner(System.in);
			int x = console.nextInt();
			Card c=cardDown.get(x-1);
			System.out.println("your choosing "+c.toString());
			if(!table.add(c)){					
				ArrayList<Card> temp=table.getCards();
				int size=temp.size();
				for(int i=0;i<size;i++){
					cardHand.add(temp.get(i));
				}
				cardHand.add(c);
			}
			cardDown.remove(x-1);
		}

	}
	//בדיקה כמה קלפים זהים יש בקלפי היד
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
	//הפונקציה מוסיפה לקלפים שביד את כל הקלפים שהיו בערימת קלפים כי הפסיד
	public ArrayList<Card> res(ArrayList<Card> b) {
		ArrayList<Card> res = new ArrayList<Card>();
		int size=cardHand.size();
		for (int i = 0; i < size; i++) {
			res.add(cardHand.get(0));
			cardHand.remove(0);
		}
		size=b.size();
		for (int i = 0; i < size; i++) {
			res.add(b.get(0));
			b.remove(0);
		}
		return res;
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
		return string;
	}

}