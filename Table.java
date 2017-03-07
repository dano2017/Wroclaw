
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
		//אם אין קלפים, אז תוסיף קלף לערימת קלפים
		if(size==0){
			if(c.getValue()!=10){
				if(c.getValue()==8)
					turn=true;
				cards.add(size,c);
				return true;
			}
			// חשוב לוודא אם הקלף הוא 10, כי אם הערימה ריקה ומוסיפים 10 - לא תקין, יש להשאיר את הערימה ריקה
			else{
//				cards.clear();
				turn=true;
				return true;
			}

		}
		//אם הקלף הוא בעל ערך 2 אז הוא שקול לערימה ריקה, אז תוסיף קלף לערימת קלפים 
		Card prev=cards.get(size-1);
		if(prev.getValue()==2){
			cards.add(size,c);
			return true;
		}
		//מקרים עבור קלף מיוחד
		if(c.specialCard()){
			switch(c.getValue()){
			case 2: cards.add(size,c);
			return true;
			case 3: cards.add(size,cards.get(size-1));
			cards.set(size-1, c);
			return true;
			case 7: if(7>=prev.getValue()){
				cards.add(size,c);
				return true;
			}
			else 
				return false;
			// כאשר בוחרים קלף 8, נוודא כי הקלף האחרון בערימה הינו קטן ממנו ושונה מ-7
			case 8: if(8>=prev.getValue() && prev.getValue()!=7){
				cards.add(size,c);
				turn=true;
				return true;
			}
			else 
				return false;
			case 10: cards.clear();
			turn=true;
			return true;
			}

		}
		//מקרים רגילים
		else{
			//יש לבדוק אם הקלף הקודם הוא 7, אם כן אז הקלף הנוכחי חייב להיות שווה ל7 או מתחת
			if(prev.getValue()==7){
				if(c.getValue()<=prev.getValue()){
					cards.add(size,c);
					return true;
				}
				else
					return false;
			}
			//מקרה בסיסי ללא מקרים מיוחדים, אם הקלף הנוכחי גדול מהקלף הקודם אז תוסיף לערימת קלפים
			if(c.getValue()>=prev.getValue()){
				cards.add(size,c);
				return true;
			}
			else
				return false;

		}
		return false;
	}


	//הפונקציה שולחת את מערך הקלפים שבקופה ומוחקת אותו (כלומר השחקן הפסיד ולוקח לעצמו את כל הקלפים)
	public ArrayList<Card> getCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		int size=cards.size();
		for(int i=0;i<size;i++){
			temp.add(cards.get(0));
			cards.remove(0);
		}
		return temp;
	}
	//מחזירה את הקלף האחרון בערימה
	public Card cardTable(){
		if(cards.size()>0)
			return cards.get(cards.size()-1);
		return null;
	}
	//הדפסת כל הקלפים שבערימה
	public void cardsInTable(){
		System.out.println("deck: "+cards.toString());
	}
	public boolean turn(){
		return turn;
	}


}
