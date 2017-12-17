public class Card {
	
	//列舉花色
	enum Suit {Club, Diamond, Heart, Spade }
	
	private Suit suit; //Clubs, Diamonds, Hearts, Spades
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */	
	public Card(Suit s,int r){
		suit=s;
		rank=r;
	}	

	public void printCard(){
		//用來儲存與花色相應的英文
		String s = "";
		//用來儲存與數字相應的英文
		String r = "";
		
		//判斷花色
		switch(suit)
		{
			case Club:
				s ="Club";
				break;
			case Diamond:
				s = "Diamond";
				break;
			case Heart:
				s = "Heart";
				break;
			case Spade:
				s = "Spade";
				break;
		}
		//判斷數字
		switch(rank)
		{
			case 1:
				r ="Ace";
				break;
			case 11:
				r = "Jack";
				break;
			case 12:
				r = "Queen";
				break;
			case 13:
				r = "King";
				break;
			//將A,J,Q,K之外的數值轉為string
			default:
				r = Integer.toString(rank);
				break;
		}

		//輸出撲克牌的花色與數字
		System.out.println(s+" "+r);
	}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
