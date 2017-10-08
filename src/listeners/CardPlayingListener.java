package listeners;

import static main.GlobalObjects.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import main.main;

public class CardPlayingListener   implements ActionListener{

	int cardIndex;

	public CardPlayingListener(int cardIndex) {
		this.cardIndex = cardIndex;
	}
	

	public void finished(){
		deckEnd.setVisible(false);
		turnComputer.setVisible(false);
		turnPlayer.setVisible(false);
		tenCard.setVisible(false);
		cardsPlayer.setVisible(false);
		cardsComputer.setVisible(false);
		computerlost.setVisible(false);
		fourCards.setVisible(false);
		int size=player.cardHand.size();
		for(int i=0;i<size;i++){
			player_hand[i].setVisible(false);
		}
		for(int i=0;i<3;i++){
			player_up[i].setVisible(false);
			player_down[i].setVisible(false);

		}	
		size=computer.cardHand.size();
		for(int i=0;i<size;i++){
			computer_hand[i].setVisible(false);
		}
		size=computer.cardUp.size();
		for(int i=0;i<size;i++){
			computer_up[i].setVisible(false);
		}	
		size=computer.cardDown.size();
		for(int i=0;i<size;i++){
			computer_down[i].setVisible(false);
		}
		cardTable.setVisible(false);

		if(player.getNumberOfCards()==0){
			win.setVisible(true);
		}
		else{
			lost.setVisible(true);
		}
		start.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		boolean check;
		if(state == 2){
			if(turn){
				fourCards.setVisible(false);
				computerlost.setVisible(false);
				check=true;
				if(!player.cardHand.isEmpty()){
					if(cardIndex>30){
						System.out.println("Error");
						check=false;
					}
					else{
						int num=player.cardHand.size();
						for(int i=0;i<num;i++){
							player_hand[i].setVisible(false);
						}
					}
				}
				else if(!player.cardUp.isEmpty()){
					cardIndex-=100;
					if(cardIndex>2 || cardIndex<0){
						System.out.println("Error");
						check=false;
					}
					else{
						player_up[cardIndex].setVisible(false);
					}
				}
				else{
					cardIndex-=103;
					if(cardIndex<0){
						System.out.println("Error");
						check=false;
					}
					else{
						player_down[cardIndex].setVisible(false);
					}

				}
				if(check){
					select.setVisible(false);
					player.Attack(cardIndex);
					if(!player.cardHand.isEmpty()){
						int num=player.cardHand.size();
						for(int i=0;i<num;i++){
							player_hand[i].setIcon(new ImageIcon(main.class.getResource(player.cardHand.get(i).ImageLocation)));
							player_hand[i].setVisible(true);
						}
					}

					turn=table.turn();
					if(turn){
						turnPlayer.setVisible(true);
						turnComputer.setVisible(false);

					}
					else{
						turnPlayer.setVisible(false);
						turnComputer.setVisible(true);

					}
					if(player.getNumberOfCards()==0){
						finished();
						turn=false;
						System.out.println("player win");
						return;
					}
					else{
						if(table.cardTable()!=null){
							cardTable.setVisible(true);
							cardTable.setIcon(new ImageIcon(main.class.getResource(table.cardTable().ImageLocation)));
						}
						else{
							cardTable.setVisible(false);
						}
					}
				}
				else{
					tenCard.setVisible(false);
					select.setVisible(true);
				}
				if(deck.getNumOfCards()==0)
					deckEnd.setVisible(true);

				
			}
			if(!turn){
				fourCards.setVisible(false);
				lostRound.setVisible(false);
				turnPlayer.setVisible(false);
				turn = true;
				while(turn){
					int num=computer.cardHand.size();
					for(int i=0;i<num;i++){
						computer_hand[i].setVisible(false);
					}
					for(int i=0;i<3;i++){
						computer_up[i].setVisible(false);
						computer_down[i].setVisible(false);
					}
					computer.Attack(player.getNumberOfCards());
					num=computer.cardHand.size();
					for(int i=0;i<num;i++){
						computer_hand[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
						computer_hand[i].setVisible(true);
					}

					num=computer.cardUp.size();
					for(int i=0;i<num;i++){
						computer_up[i].setIcon(new ImageIcon(main.class.getResource(computer.cardUp.get(i).ImageLocation)));
						computer_up[i].setVisible(true);
					}
					num=computer.cardDown.size();
					for(int i=0;i<num;i++){
						computer_down[i].setIcon(new ImageIcon(main.class.getResource("/images/back.png")));
						computer_down[i].setVisible(true);
					}
					turn=table.turn();
					if(turn){
						turnPlayer.setVisible(false);
						turnComputer.setVisible(true);

					}
					else{
						turnPlayer.setVisible(true);
						turnComputer.setVisible(false);

					}
					if(computer.getNumberOfCards()==0){
						finished();
						turn=false;
						System.out.println("computer win");

						return;
					}
					else{
						if(table.cardTable()!=null){
							cardTable.setVisible(true);
							cardTable.setIcon(new ImageIcon(main.class.getResource(table.cardTable().ImageLocation)));
						}
						else{
							cardTable.setVisible(false);
						}
					}
				}
				turn = true;
				if(deck.getNumOfCards()==0)
					deckEnd.setVisible(true);
			}
		}
	}
}

