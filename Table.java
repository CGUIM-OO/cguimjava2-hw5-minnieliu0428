import java.util.ArrayList;

public class Table {
	
	private static final int MAXPLAYER = 4;
	private Deck allCards;        //所有的牌
	private Player[] allPlayer;   //所有的玩家
	private Dealer dealer;        //莊家
	private int[] pos_betArray;   //每個玩家在一局下的注
	//private ArrayList<Card> playerCard;   //暫存玩家的卡
	private ArrayList<Card> player1Card;  //玩家1的卡
	private ArrayList<Card> player2Card;  //玩家2的卡
	private ArrayList<Card> player3Card;  //玩家3的卡
	private ArrayList<Card> player4Card;  //玩家4的卡
	private ArrayList<Card> dealerCard;   //莊家的卡
	
	public Table(int nDeck) {
		allCards = new Deck(nDeck);
		allPlayer = new Player[MAXPLAYER];
	}
	
	public void set_player(int pos, Player p) {
		allPlayer[pos] = p;
	}
	
	public Player[] get_player() {
		return allPlayer;
	}
	
	public void set_dealer(Dealer d) {
		dealer = d;
	}
	
	public Card get_face_up_card_of_dealer() {
		return dealerCard.get(1);
	}
	
	private void ask_each_player_about_bets() {
		pos_betArray = new int[allPlayer.length];
		
		//請每個玩家打招呼
		for(Player p : allPlayer)
		{
			p.sayHello();
		}
		//請每個玩家下注並將下注存入pos_betArray
		for(int i = 0; i < pos_betArray.length;i++)
		{
			pos_betArray[i] = allPlayer[i].makeBet();
		}
	}
	
	private void distribute_cards_to_dealer_and_players() {
		//playerCard=new ArrayList<Card>();
		player1Card=new ArrayList<Card>();
		player2Card=new ArrayList<Card>();
		player3Card=new ArrayList<Card>();
		player4Card=new ArrayList<Card>();
		dealerCard=new ArrayList<Card>();
		
		/*for(int j = 0; j < allPlayer.length; j++)
		{
			for(int i = 0; i <= 2; i++)
			{		
				playerCard.add(allCards.getOneCard(true));				
			}
			playerCard.add(allCards.getOneCard(false));
			allPlayer[j].setOneRoundCard(playerCard);
			playerCard.clear();
		}*/
			
		//發牌給玩家（兩開一蓋）
		player1Card.add(allCards.getOneCard(true));
		player1Card.add(allCards.getOneCard(true));
		player1Card.add(allCards.getOneCard(false));
		allPlayer[0].setOneRoundCard(player1Card);
		
		player2Card.add(allCards.getOneCard(true));
		player2Card.add(allCards.getOneCard(true));
		player2Card.add(allCards.getOneCard(false));
		allPlayer[1].setOneRoundCard(player2Card);
		
		player3Card.add(allCards.getOneCard(true));
		player3Card.add(allCards.getOneCard(true));
		player3Card.add(allCards.getOneCard(false));
		allPlayer[2].setOneRoundCard(player3Card);
		
		player4Card.add(allCards.getOneCard(true));
		player4Card.add(allCards.getOneCard(true));
		player4Card.add(allCards.getOneCard(false));
		allPlayer[3].setOneRoundCard(player4Card);
		
		//發牌給莊家（一開）
		dealerCard.add(allCards.getOneCard(false));
		dealerCard.add(allCards.getOneCard(true));
		dealer.setOneRoundCard(dealerCard);
		System.out.println("Dealer's face up card is ");
		dealer.printAllCard();
	}
	
	//詢問玩家是否要牌
	private void ask_each_player_about_hits() {
		boolean hit = false;
		
		do 
		{
			//判斷牌有沒有爆
			if(allPlayer[0].getTotalValue()<=21) 
			{
				hit = allPlayer[0].hit_me(this);
				if(hit) 
				{
					player1Card = allPlayer[0].getOneRoundCard();
					player1Card.add(allCards.getOneCard(true));
					allPlayer[0].setOneRoundCard(player1Card);
					System.out.print("Hit! ");
					System.out.println(allPlayer[0].getName() + "'s cards now:");
					allPlayer[0].printAllCard();
				}
				else {
					System.out.println(allPlayer[0].getName() + " Pass hit!");
				}
			}
			else {
				hit = false;   //break;
			}
			
		}while(hit);
		hit = false;
		do 
		{
			if(allPlayer[1].getTotalValue()<=21) 
			{
				hit = allPlayer[1].hit_me(this);
				if(hit) 
				{
					player2Card = allPlayer[1].getOneRoundCard();
					player2Card.add(allCards.getOneCard(true));
					allPlayer[1].setOneRoundCard(player2Card);
					System.out.print("Hit! ");
					System.out.println(allPlayer[1].getName() + "'s cards now:");
					allPlayer[1].printAllCard();
				}
				else {
					System.out.println(allPlayer[1].getName() + " Pass hit!");
				}
			}
			else {
				hit = false;
			}
			
		}while(hit);
		hit = false;
		do 
		{
			if(allPlayer[2].getTotalValue()<=21) 
			{
				hit = allPlayer[2].hit_me(this);
				if(hit) 
				{
					player3Card = allPlayer[2].getOneRoundCard();
					player3Card.add(allCards.getOneCard(true));
					allPlayer[2].setOneRoundCard(player3Card);
					System.out.print("Hit! ");
					System.out.println(allPlayer[2].getName() + "'s cards now:");
					allPlayer[2].printAllCard();
				}
				else {
					System.out.println(allPlayer[2].getName() + " Pass hit!");
				}
			}
			else {
				hit = false;
			}
			
		}while(hit);
		hit = false;
		do 
		{
			if(allPlayer[3].getTotalValue()<=21) 
			{
				hit = allPlayer[3].hit_me(this);
				if(hit) 
				{
					player4Card = allPlayer[3].getOneRoundCard();
					player4Card.add(allCards.getOneCard(true));
					allPlayer[3].setOneRoundCard(player4Card);
					System.out.print("Hit! ");
					System.out.println(allPlayer[3].getName() + "'s cards now:");
					allPlayer[3].printAllCard();
				}
				else {
					System.out.println(allPlayer[3].getName() + " Pass hit!");
				}
			}
			else {
				hit = false;
			}
			
		}while(hit);
		/*
		for(Player p : allPlayer)
		{
			do 
			{
				if(p.getTotalValue()<=21) 
				{
					hit = p.hit_me(this);
					if(hit) 
					{
						playerCard = p.getOneRoundCard();
						playerCard.add(allCards.getOneCard(true));
						p.setOneRoundCard(playerCard);
						System.out.print("Hit! ");
						System.out.println(p.getName() + "'s cards now:");
						p.printAllCard();
					}
					else {
						System.out.println(p.getName() + " Pass hit!");
					}
				}
				else {
					hit = false;
				}
				
			}while(hit);
			playerCard.clear();
			hit = false;
		}*/	
	}
	
	//詢問莊家是否要牌
	private void ask_dealer_about_hits() {
		dealer.hit_me(this);	
		System.out.println("Dealer's hit is over!");
	}
	
	//判斷勝負＆計算籌碼
	private void calculate_chips() {
		int bTotal = dealer.getTotalValue();
		System.out.println("Dealer's card value is "+ bTotal + ", Cards:");
		dealer.printAllCard();
		for(int i = 0; i < allPlayer.length;i++)
		{
			int pTotal = allPlayer[i].getTotalValue();
			Player p = allPlayer[i];
			
			System.out.print(p.getName() + " card value is " + pTotal);
			//平手(1.banker>21, player>21 2.banker=player)
			if((bTotal>21 && bTotal>21) || (bTotal == pTotal))
			{
				System.out.println(", chips have no change! The Chips now is: " + p.getCurrentChips());
			}
			//莊家贏(banker<=21, 1.player>21 2.player<banker) -bet
			else if(bTotal<=21 && (pTotal>21 || pTotal<bTotal))
			{
				p.increaseChips(-pos_betArray[i]);
				System.out.println(", Loss " + pos_betArray[i] + " Chips, the Chip now is: " + p.getCurrentChips());
			}
			//玩家贏(player<=21, 1.banker>21 2.banker<player) +bet
			else if(pTotal<=21 && (bTotal>21 || bTotal<pTotal))
			{
				p.increaseChips(pos_betArray[i]);
				System.out.println(", Get " + pos_betArray[i] + " Chips, the Chips now is: " + p.getCurrentChips());
			}
		}
	}
	
	public int[] get_palyers_bet() {
		return pos_betArray;
	}
	
	public void play() {
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
}
