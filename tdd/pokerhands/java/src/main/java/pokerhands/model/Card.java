package pokerhands.model;

import static pokerhands.model.Rank.*;
import static pokerhands.model.Suit.*;

/**
 * Created by tvo on 8/27/16.
 */
public class Card {
    public static final Card TWO_HEART = new Card(TWO, HEART);
    public static final Card TWO_CLUB = new Card(TWO, CLUB);
    public static final Card TWO_DIAMOND = new Card(TWO, DIAMOND);
    public static final Card TWO_SPADE = new Card(TWO, SPADE);

    public static final Card THREE_HEART = new Card(THREE, HEART);
    public static final Card THREE_CLUB = new Card(THREE, CLUB);
    public static final Card THREE_DIAMOND = new Card(THREE, DIAMOND);
    public static final Card THREE_SPADE = new Card(THREE, SPADE);

    public static final Card FOUR_HEART = new Card(FOUR, HEART);
    public static final Card FOUR_CLUB = new Card(FOUR, CLUB);
    public static final Card FOUR_DIAMOND = new Card(FOUR, DIAMOND);
    public static final Card FOUR_SPADE = new Card(FOUR, SPADE);

    public static final Card FIVE_HEART = new Card(FIVE, HEART);
    public static final Card FIVE_CLUB = new Card(FIVE, CLUB);
    public static final Card FIVE_DIAMOND = new Card(FIVE, DIAMOND);
    public static final Card FIVE_SPADE = new Card(FIVE, SPADE);

    public static final Card SIX_HEART = new Card(SIX, HEART);
    public static final Card SIX_CLUB = new Card(SIX, CLUB);
    public static final Card SIX_DIAMOND = new Card(SIX, DIAMOND);
    public static final Card SIX_SPADE = new Card(SIX, SPADE);

    public static final Card SEVEN_HEART = new Card(SEVEN, HEART);
    public static final Card SEVEN_CLUB = new Card(SEVEN, CLUB);
    public static final Card SEVEN_DIAMOND = new Card(SEVEN, DIAMOND);
    public static final Card SEVEN_SPADE = new Card(SEVEN, SPADE);

    public static final Card EIGHT_HEART = new Card(EIGHT, HEART);
    public static final Card EIGHT_CLUB = new Card(EIGHT, CLUB);
    public static final Card EIGHT_DIAMOND = new Card(EIGHT, DIAMOND);
    public static final Card EIGHT_SPADE = new Card(EIGHT, SPADE);

    public static final Card NINE_HEART = new Card(NINE, HEART);
    public static final Card NINE_CLUB = new Card(NINE, CLUB);
    public static final Card NINE_DIAMOND = new Card(NINE, DIAMOND);
    public static final Card NINE_SPADE = new Card(NINE, SPADE);

    public static final Card TEN_HEART = new Card(TEN, HEART);
    public static final Card TEN_CLUB = new Card(TEN, CLUB);
    public static final Card TEN_DIAMOND = new Card(TEN, DIAMOND);
    public static final Card TEN_SPADE = new Card(TEN, SPADE);

    public static final Card JACK_HEART = new Card(JACK, HEART);
    public static final Card JACK_CLUB = new Card(JACK, CLUB);
    public static final Card JACK_DIAMOND = new Card(JACK, DIAMOND);
    public static final Card JACK_SPADE = new Card(JACK, SPADE);

    public static final Card QUEEN_HEART = new Card(QUEEN, HEART);
    public static final Card QUEEN_CLUB = new Card(QUEEN, CLUB);
    public static final Card QUEEN_DIAMOND = new Card(QUEEN, DIAMOND);
    public static final Card QUEEN_SPADE = new Card(QUEEN, SPADE);

    public static final Card KING_HEART = new Card(KING, HEART);
    public static final Card KING_CLUB = new Card(KING, CLUB);
    public static final Card KING_DIAMOND = new Card(KING, DIAMOND);
    public static final Card KING_SPADE = new Card(KING, SPADE);

    public static final Card ACE_HEART = new Card(ACE, HEART);
    public static final Card ACE_CLUB = new Card(ACE, CLUB);
    public static final Card ACE_DIAMOND = new Card(ACE, DIAMOND);
    public static final Card ACE_SPADE = new Card(ACE, SPADE);

    private final Rank rank;
    private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card{" + rank +
                ", " + suit +
                '}';
    }

    public Rank getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rank != card.rank) return false;
        if (suit != card.suit) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rank.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }
}
