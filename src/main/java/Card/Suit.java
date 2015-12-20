package Card;

public enum Suit
{
    HEARTS,
    SPADES,
    CLUBS,
    DIAMONDS;

    @Override
    public String toString() {
        return name();
    }
}
