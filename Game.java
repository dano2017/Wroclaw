
import java.util.ArrayList;
import java.util.Scanner;


public class Game {

	public static void main(String[] args) {

		Deck d=new Deck(); 
		Table t=new Table(d);
		Player a=new Player("player1",d,t);
		Player b=new Player("player2",d,t);
		System.out.println(a.toString());
		System.out.println(b.toString());
		a.Choose();
		b.Choose();
		System.out.println("START GAME!!");
		if(t.cardTable()!=null)
			System.out.println("---"+t.cardTable().toString()+"---");
		else
			System.out.println("--- [,] ---");
		boolean turn;
		while(a.getNumberOfCards()>0 && b.getNumberOfCards()>0){
			turn=true;
			while(turn){
				a.Attack();
				if(t.cardTable()!=null){
					System.out.println("---"+t.cardTable().toString()+"---");
					t.cardsInTable();
				}
				else
					System.out.println("--- [,] ---");
				System.out.println("numofCardsindeck "+d.getNumOfCards());
				turn=t.turn();
			}
			if(a.getNumberOfCards()==0)
				break;
			turn=true;
			while(turn){
				b.Attack();
				if(t.cardTable()!=null){
					System.out.println("---"+t.cardTable().toString()+"---");
					t.cardsInTable();
				}
				else
					System.out.println("--- [,] ---");
				System.out.println("numofCardsindeck "+d.getNumOfCards());
				turn=t.turn();
			}
		}
		if(a.getNumberOfCards()==0)
			System.out.println(a.getName()+" WON!");
		else
			System.out.println(b.getName()+" WON!");
	}

}
