package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Card;

import static main.GlobalObjects.*;

public class CardFirstListener  implements ActionListener{

	int index;
	int index2;

public CardFirstListener(int index, int index2) {
		super();
		this.index = index;
		this.index2 = index2;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(state){
		case 0:
			break;
		case 1:
			if(firstindex == index || firstindex2 == -1){
				firstindex = index;
				firstindex2 = index2;
				
			}
			else{
				JButton temp = new JButton();
				if(firstindex == 0){
					//switch currentCard with firstCard
					temp.setIcon(player_hand[firstindex2].getIcon());
					player_hand[firstindex2].setIcon(player_up[index2].getIcon());
					player_up[index2].setIcon(temp.getIcon());
					player.Choose(firstindex2, index2);
				}
				else{
					//switch currentCard with firstCard
					temp.setIcon(player_hand[index2].getIcon());
					player_hand[index2].setIcon(player_up[firstindex2].getIcon());
					player_up[firstindex2].setIcon(temp.getIcon());
					player.Choose(index2, firstindex2);
					
				}
				firstindex2 = -1;
			}
			
		case 2:
			break;
		}
	}

}
