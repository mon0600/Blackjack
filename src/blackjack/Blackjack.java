package blackjack;
import java.util.*;



public class Blackjack implements BlackjackEngine {
	private int playersact ;
	private int initialbt ;
	private ArrayList<Card> dealercards;
	private ArrayList<Card> plyrcards;
	private ArrayList<Card> deck;
	private int numdecks;
	private Random gnrtr;
	private int gameStatus; 

	/**
	 * Constructor you must provide.  Initializes the player's account 
	 * to 200 and the initial bet to 5.  Feel free to initialize any other	
	 * fields. Keep in mind that the constructor does not define the 
	 * deck(s) of cards.
	 * @param randomGenerator
	 * @param numberOfDecks
	 */
	public Blackjack(Random randomGenerator, int numberOfDecks) {
		this.playersact = 200;
		this.initialbt = 5;
		this.dealercards = new ArrayList<Card>();
		this.plyrcards = new ArrayList<Card>();
		this.deck = new ArrayList<Card>();
		this.numdecks = numberOfDecks;
		this.gnrtr = randomGenerator;


	}

	public int getNumberOfDecks() {
		return numdecks;
	}

	public void createAndShuffleGameDeck() {


		//loading the suits and values at the same time. 
		for (CardSuit suit : CardSuit.values()) {
			for ( CardValue value : CardValue.values()) {
				deck.add(new Card(value,suit));	


			}}
		//shuffling the cards based on the randomGenerator provided.
		Collections.shuffle(deck,gnrtr);
	}
	public Card[] getGameDeck() {
		Card[] getDeck = new Card[deck.size()] ;

		for(int i =0;i<deck.size(); i++) {
			getDeck[i]=deck.get(i);
		}
		return getDeck;
	}

	public void deal() {	
//clesar out decks att beginning of a new game
		for(int i =0;i<deck.size(); i++) {
			deck.removeAll(deck);
		}

		for(int i =0;i<plyrcards.size(); i++) {
			plyrcards.removeAll(plyrcards);
		}

		for(int i =0;i<dealercards.size(); i++) {
			dealercards.removeAll(dealercards);
		}



		createAndShuffleGameDeck();


		plyrcards.add(deck.remove(0));
		plyrcards.get(0).setFaceUp();


		dealercards.add(deck.remove(0));
		dealercards.get(0).setFaceDown();


		plyrcards.add(deck.remove(0));
		plyrcards.get(1).setFaceUp();


		dealercards.add(deck.remove(0));
		dealercards.get(1).setFaceUp();



		//		System.out.println(plyrcards.get(0).getValue().ordinal());
		playersact = playersact - initialbt;

		gameStatus = GAME_IN_PROGRESS;

	}

	public Card[] getDealerCards() {

		Card[] getDeel = new Card[dealercards.size()] ;

		for(int i =0;i<dealercards.size(); i++) {
			getDeel[i]=dealercards.get(i);
		}
		return getDeel;

	}

	public int[] getDealerCardsTotal() {

		int dtotal1 = 0; 
		int dtotal2 = 0; 

		for(int i = 0; i < dealercards.size();i++) {

			if(dealercards.get(i).getValue()== CardValue.Ace && dealercards.size()==2) {

				dtotal2 += dealercards.get(i).getValue().getIntValue() + 10;
				break;

			}}
		for(int i = 0; i < dealercards.size();i++) {
			if (dtotal2 > 21) {
				dtotal2 += dealercards.get(i).getValue().getIntValue();
			}else {
				dtotal1 += dealercards.get(i).getValue().getIntValue();

			}



		}

		if(dtotal1 > 21 || dtotal2> 21){
			return null;
		}else
			if (dtotal1 == dtotal2) {
				int [] deelertotal  = {dtotal1};
				return deelertotal;


			}if(dtotal2 == 0) {
				int [] deelertotal   = {dtotal1};
				return deelertotal;}
			else {
				int [] deelertotal  = {dtotal1 ,dtotal2};
				return deelertotal;

			}

	}
	private int getdeelrTotal() {
		//helper method to get the total dealer card as  an  int.
		int count = 0;



		int count2 = 0;

		for(int i = 0; i < dealercards.size();i++) {
			if(dealercards.get(i).getValue() == CardValue.Ace  && dealercards.size()==2) {
				count2 += dealercards.get(i).getValue().getIntValue() + 10;
				break;
			}}
		for(int i = 0; i < dealercards.size();i++) {
			if  (count2 > 21) {
				count2 += dealercards.get(i).getValue().getIntValue();
			}else {

				count += dealercards.get(i).getValue().getIntValue();

			}
		}

		if(count> count2) {
			return count;
		}else {
			return count2;



		}
	}
	public int getDealerCardsEvaluation() {

		boolean check = false;
		//		int deval = 0;
		int count = 0;
		//		for(int i = 0; i < dealercards.size();i++) {
		//			deval += dealercards.get(i).getValue().getIntValue();		
		//		}


		if(getdeelrTotal() < 21 ) {
			count = LESS_THAN_21;
		}else  {
			
				if(dealercards.size() == 2 && getPlyrTotal() == 21) {

					count = BLACKJACK;
					check = true;

				}
		}if(getdeelrTotal() > 21) {
			count = BUST;
		}
		else if(getdeelrTotal() == 21 && check == false){
			count = HAS_21;
		}
		//		System.out.println(deval);
		return count;



	}

	public Card[] getPlayerCards() {
		Card[] getPlay= new Card[plyrcards.size()] ;

		for(int i =0;i<plyrcards.size(); i++) {
			getPlay[i] = plyrcards.get(i);
		}
		return getPlay;
	}

	public int[] getPlayerCardsTotal() {

		int total1 = 0; 
		int total2 = 0; 

		for(int i = 0; i < plyrcards.size();i++) {
			if(plyrcards.get(i).getValue() == CardValue.Ace) {

				total2 += plyrcards.get(i).getValue().getIntValue() + 10;
				break;
			}}
		for(int i = 0; i < plyrcards.size();i++) {
			if(total2 > 21) {
				total2 += plyrcards.get(i).getValue().getIntValue();
			}
			else {

				total1 += plyrcards.get(i).getValue().getIntValue();

			}
		}

		//		System.out.println(total1);
		//		System.out.println(total2);

		if(total1 > 21 || total2 >21 ) {
			return  null;
		}else if (total1 == total2) {
			int [] plyrtotal  = {total1};
			return plyrtotal;
		}if(total2 == 0) {
			int [] plyrtotal  = {total1};
			return plyrtotal;
		}
		else {
			int [] plyrtotal  = {total1 ,total2};
			return plyrtotal;
		}

	}


	private int getPlyrTotal() {
		//helper method to get the total plyr card as  an  int.
		int count = 0;



		int count2 = 0;
		for(int i = 0; i < plyrcards.size();i++) {
			if(plyrcards.get(i).getValue() == CardValue.Ace) {
				count2 += plyrcards.get(i).getValue().getIntValue() + 10;
				break;
			}}	for(int i1 = 0; i1<plyrcards.size();i1++) {

				if  (count2 > 21) {
					count2 += plyrcards.get(i1).getValue().getIntValue();
				}else {

					count += plyrcards.get(i1).getValue().getIntValue();

				}
			}

			if(count> count2) {
				return count;
			}else {
				return count2;
			}		




	}

	public int getPlayerCardsEvaluation() {
		//		int total1 = 0; 
		//		int total2 = 0; 
		int count = 0;
		boolean  pure = false;


		if(getPlyrTotal() < 21 ) {
			count = LESS_THAN_21;
		}else {
			for(int i = 0; i < plyrcards.size();i++) {
				if(plyrcards.get(i).getValue() == CardValue.Ace && plyrcards.size() == 2 && getPlyrTotal() == 21) {

					count = BLACKJACK;
					pure = true;

				}}
			if(getPlyrTotal() > 21) {
				count = BUST;
			}
			else if(getPlyrTotal() == 21 && pure == false){
				count = HAS_21;
			}
		}


		return count;


	}

	public void playerHit() {


		plyrcards.add(this.deck.remove(0));


		if(getPlyrTotal() > 21 ) {
			gameStatus = DEALER_WON ;
		}else {
			gameStatus = GAME_IN_PROGRESS;
		}


	}

	public void playerStand() {
		// sets dealer card faceup and  check if he  has blackjack
		dealercards.get(0).setFaceUp();

		if(getdeelrTotal() == 21&& dealercards.size()==2) {
			gameStatus =  DEALER_WON;
		}else {
			do {
				dealercards.add(deck.remove(0));
			}while(getdeelrTotal() <16);

			if(getdeelrTotal() > getPlyrTotal() && getdeelrTotal() < 21) {
				gameStatus =  DEALER_WON;

			}else if(getPlyrTotal() > getdeelrTotal()){
				gameStatus =  PLAYER_WON;
				playersact += (initialbt*2);
			}	
			else if((getdeelrTotal() > 21 && getPlayerCardsEvaluation() != BUST ) ||getPlyrTotal() ==21) {
				gameStatus =  PLAYER_WON;
				playersact += (initialbt*2);
			}else if(getPlyrTotal() ==  getdeelrTotal() ){
				gameStatus = DRAW;
				playersact += initialbt;
			}
			else {
				gameStatus = GAME_IN_PROGRESS;
				playersact += initialbt;
			}
		}
	}





	public int getGameStatus() {

		return gameStatus;
	}

	public void setBetAmount(int amount) {
		initialbt = amount;

	}

	public int getBetAmount() {
		return initialbt;
	}

	public void setAccountAmount(int amount) {	
		playersact = amount;
	}

	public int getAccountAmount() {
		return playersact;
	}

	/* Feel Free to add any private methods you might need */
}