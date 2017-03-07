
import java.util.ArrayList;
import java.util.Collections;

/** ����� ����� */

public class Deck {

	private ArrayList<Card> cards;
	
/** ���� ����� ���� 52 �����*/
	
	public Deck() {
		int countID=0;
		cards = new ArrayList<>();
		for (int s=0;s<4;s++) {
			for (int v=2;v<15;v++) {
				cards.add(new Card(s, v,countID));
				countID++;
			}
		}
		shuffle();
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public int getNumOfCards() {
		return cards.size();
	}
/** ��� ��� ������, �� �� �� ��� ���� ����� ���� ������� ���� ����� ��� */
	
	public ArrayList<Card> getNextCards(int numOfCards) {
		ArrayList<Card> ret = new ArrayList<Card>();
		for (int i = 0; i < numOfCards && hasNext(); i++) {
			ret.add(cards.get(0));
			cards.remove(0);
		}
		return ret;
	}

	public Card getNextCard() {
		if (hasNext()) {
			Card c = cards.get(0);
			cards.remove(0);
			return c;
		}
		return null;
	}

	public boolean hasNext() {
		return !cards.isEmpty();
	}
/** ����� ������ */
	
	public void shuffle(){
		Collections.shuffle(cards);
	}
	

}
