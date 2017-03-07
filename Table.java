
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
		//�� ��� �����, �� ����� ��� ������ �����
		if(size==0){
			if(c.getValue()!=10){
				if(c.getValue()==8)
					turn=true;
				cards.add(size,c);
				return true;
			}
			// ���� ����� �� ���� ��� 10, �� �� ������ ���� �������� 10 - �� ����, �� ������ �� ������ ����
			else{
				//				cards.clear();
				turn=true;
				return true;
			}

		}
		//�� ���� ��� ��� ��� 2 �� ��� ���� ������ ����, �� ����� ��� ������ ����� 
		Card prev=cards.get(size-1);
		if(prev.getValue()==2){
			if(c.getValue()==8)
				turn=true;
			cards.add(size,c);
			return true;
		}
		//����� ���� ��� �����
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
			// ���� ������ ��� 8, ����� �� ���� ������ ������ ���� ��� ���� ����� �-7
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
		//����� ������
		else{
			//�� ����� �� ���� ����� ��� 7, �� �� �� ���� ������ ���� ����� ���� �7 �� ����
			if(prev.getValue()==7){
				if(c.getValue()<=prev.getValue()){
					cards.add(size,c);
					return true;
				}
				else
					return false;
			}
			//���� ����� ��� ����� �������, �� ���� ������ ���� ����� ����� �� ����� ������ �����
			if(c.getValue()>=prev.getValue()){
				cards.add(size,c);
				return true;
			}
			else
				return false;

		}
		return false;
	}


	//�������� ����� �� ���� ������ ������ ������ ���� (����� ����� ����� ����� ����� �� �� ������)
	public ArrayList<Card> getCards() {
		ArrayList<Card> temp = new ArrayList<Card>();
		int size=cards.size();
		for(int i=0;i<size;i++){
			temp.add(cards.get(0));
			cards.remove(0);
		}
		return temp;
	}
	//������ �� ���� ������ ������
	public Card cardTable(){
		if(cards.size()>0)
			return cards.get(cards.size()-1);
		return null;
	}
	//����� �� ������ �������
	public void cardsInTable(){
		System.out.println("deck: "+cards.toString());
	}
	public boolean turn(){
		return turn;
	}
	//����� ����� ����� ���� �����, �� ����� ������ �� 10 �3
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
	//���� �� �� 4 ����� ���� ������, �� �� ���� ���� �� ������ ���� ��� ���
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
