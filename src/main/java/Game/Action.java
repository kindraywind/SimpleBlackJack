package Game;

/**
 * Created by woramet on 12/21/15.
 */
public enum Action {

    HIT(1),
    STAND(2),
    INSURANCE(3),
    DOUBLE(4),
    SURRENDER(5),
    SPLIT(6);

    private int action;

    private Action (int action)
    {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    @Override
    public String toString() {
        return this.getAction()+". "+name();
    }

}
