
public class Game {

	public static void main(String[] args) throws InterruptedException {

		Deck d=new Deck(); 
		Table t=new Table(d);
		Player a=new Player("Liav",d,t);
		Computer b=new Computer("Computer",d,t);
		System.out.println(a.toString());
		System.out.println(b.toString());
		a.Choose();
		b.Choose();
		System.out.println();
		System.out.println("* * * * *");
		Thread.sleep(1000);
		System.out.println("START GAME!!");
		Thread.sleep(1000);
		if(t.cardTable()!=null)
			System.out.println("---"+t.cardTable().toString()+"---");
		else
			System.out.println("--- [,] ---");
		boolean turn;
		while(a.getNumberOfCards()>0 && b.getNumberOfCards()>0){
			turn=true;
			while(turn){
				Thread.sleep(1000);
				a.Attack();
				if(t.cardTable()!=null){
					System.out.println("---"+t.cardTable().toString()+"---");
					
				}
				else
					System.out.println("--- [,] ---");
				t.cardsInTable();
				System.out.println(d.getNumOfCards());
				turn=t.turn();
			}
			if(a.getNumberOfCards()==0)
				break;
			turn=true;
			while(turn){
				Thread.sleep(1000);
				b.Attack();
				if(t.cardTable()!=null){
					System.out.println("---"+t.cardTable().toString()+"---");
					
				}
				else
					System.out.println("--- [,] ---");
				t.cardsInTable();
				System.out.println(d.getNumOfCards());
				turn=t.turn();
			}
		}
		if(a.getNumberOfCards()==0)
			System.out.println(a.getName()+" WON!");
		else
			System.out.println(b.getName()+" WON!");
	}

}
