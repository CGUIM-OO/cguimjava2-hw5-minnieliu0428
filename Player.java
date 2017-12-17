import java.util.ArrayList;

public class Player extends Person {

	private String name; //玩家姓名
	private int chips; //現有的籌碼
	private int bet;   //此局下注的籌碼 
	
	/**
	 * @param name name
	 * @param chips chips
	 */	
	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * 下注
	 * @return bet
	 */
	public int makeBet() {
		//檢查是否還有錢
		if(chips != 0 && chips > bet) 
		{
			bet = 100;
		}
		else {
			bet = 0;
		}		
		return bet;
	}
	
	/**
	 * 是否要牌
	 * @return b  (要牌：true, 不要牌:false)
	 */
	public boolean hit_me(Table table) {
		boolean b;
		//基本參考條件：16點以下要牌，17點以上不要牌
		//提示：用oneRoundCard來算
		int cardTotal = getTotalValue();
		if(cardTotal <= 16)
		{
			b = true;
		}
		else {
			b = false;
		}
		return b;
	}
	
	public int getCurrentChips() {
		//回傳玩家現有籌碼
		return chips;
	}
	
	public void increaseChips (int diff) {
		//玩家籌碼變動，setter
		chips = chips + diff;
	}
	
	public void sayHello() {
		//玩家Say Hello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
}
