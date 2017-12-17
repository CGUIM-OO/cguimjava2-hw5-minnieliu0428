import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;      //所有的牌
	private ArrayList<Card> usedCards;  //所有發出去的牌
	private ArrayList<Card> openCards;  //所有打開的牌
	public int nUsed;

	/**
	 * 建立牌組
	 * @param nDeck 產生幾副排
	 * @return
	 */
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCards=new ArrayList<Card>();
		openCards=new ArrayList<Card>();
		
		//產生幾副牌
		for(int i = 0; i < nDeck ;i++) 
		{
			//產生花色suit
			for(Card.Suit s : Card.Suit.values())
			{
				//產生數字rank
				for(int r = 1; r <= 13; r++)
				{
					Card card = new Card(s,r);
					//將撲克牌放入ArrayList
					cards.add(card);
				}
			}
		}	
		shuffle();
	}	
	

	//洗牌
	public void shuffle() {
		
		//如果有發過牌的話
		if(usedCards.size() != 0)
		{
			//將牌收回來
			for(Card c : usedCards)
			{
				cards.add(c);
			}
			
			//將紀錄清空
			openCards.clear();
			usedCards.clear();
			nUsed = 0;
		}

		Random rnd = new Random();
		
		//亂數重排
		for(int i = 0; i < cards.size(); i++)
		{
			int j = rnd.nextInt(cards.size());
			Card tempCard = cards.get(j);
			cards.set(j, cards.get(i));
			cards.set(i, tempCard);
		}
	}
	
	/**
	 * 發牌	
	 * @param isOpened 是否要開牌 (開牌：true, 蓋牌:false)
	 * @return c 一張牌
	 */
	public Card getOneCard(boolean isOpened){
		
		//取arrayList裡的第一張牌
		Card c = cards.get(0);
		
		//如果決定開牌的話紀錄
		if(isOpened != true)
		{
			openCards.add(c);
		}
		
		//紀錄至發過的牌及數量
		usedCards.add(c);
		nUsed++;
		
		//清除發出去的牌
		cards.remove(c);
		
		//如果牌發完了的話洗牌
		if (cards.size() == 0)
		{
			shuffle();
		}

		return c;
	}
	
	public void printDeck(){

		for(Card c:cards)
		{
			c.printCard();
		}

	}
	
	public ArrayList<Card> getAllCards(){
		return cards;
	}	
	
	public ArrayList getOpenedCard(){
		return openCards;
	}
}

