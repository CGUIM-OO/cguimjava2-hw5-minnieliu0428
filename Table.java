import java.util.ArrayList;

public class Table {
	
	private static final int MAXPLAYER = 4;
	private Deck allCards;        //所有的牌
	private Player[] allPlayer;   //所有的玩家
	private Dealer dealer;        //莊家
	private int[] pos_betArray;   //每個玩家在一局下的注
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
	
	public Dealer get_dealer() {
		return dealer;
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
		dealerCard=new ArrayList<Card>();
		
		//發牌給玩家（兩開）
		for(int j = 0; j < allPlayer.length; j++)
		{	
			if(allPlayer[j]!=null && pos_betArray[j]!=0)
			{
				ArrayList<Card> playerCard=new ArrayList<Card>();
				playerCard.add(allCards.getOneCard(true));	
				playerCard.add(allCards.getOneCard(true));
				allPlayer[j].setOneRoundCard(playerCard);
			}
		}
		
		//發牌給莊家（一蓋一開）
		dealerCard.add(allCards.getOneCard(false));
		dealerCard.add(allCards.getOneCard(true));
		dealer.setOneRoundCard(dealerCard);
		System.out.println("Dealer's face up card is ");
		dealer.printAllCard();
	}
	
	//詢問玩家是否要牌
	private void ask_each_player_about_hits() {
		boolean hit = false;
		
		for(Player p : allPlayer)
		{
			do 
			{
				//牌爆掉就不要要牌
				if(p.getTotalValue()<=21) 
				{
					hit = p.hit_me(this);
					if(hit) 
					{
						ArrayList<Card> playersCard=new ArrayList<Card>();
						playersCard = p.getOneRoundCard();
						playersCard.add(allCards.getOneCard(true));
						p.setOneRoundCard(playersCard);
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
			System.out.println(p.getName() + " Final Card:");
			p.printAllCard();
			hit = false;
		}
	}
	
	//詢問莊家是否要牌
	private void ask_dealer_about_hits() {
		boolean hit = false;

		do 
		{
			if(dealer.getTotalValue()<=21) 
			{
				hit = dealer.hit_me(this);	
				if(hit) 
				{
					dealerCard.add(allCards.getOneCard(true));
					dealer.setOneRoundCard(dealerCard);
				}
				else {
					hit = false;
				}
			}
			else {
				hit = false;
			}
			
		}while(hit);
		hit = false;
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
			if((bTotal>21 && pTotal>21) || (bTotal == pTotal))
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
