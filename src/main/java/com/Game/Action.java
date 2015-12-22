package com.Game;

/**
 * Created by woramet on 12/21/15.
 */
public enum Action {

    HIT(1),
    STAND(2),
    INSURANCE(3),
    DOUBLE(4),
    SURRENDER(5),
    SPLIT(6),
    INVALID(-1);

    private int action;

    private Action (int action)
    {
        this.action = action;
    }

    public int getAction() {

        return action;
    }
    public static Action fromInteger(int x) {
        switch(x) {
            case 1:
                return HIT;
            case 2:
                return STAND;
            case 3:
                return INSURANCE;
            case 4:
                return DOUBLE;
            case 5:
                return SURRENDER;
            case 6:
                return SPLIT;
            default:
                return INVALID;
        }
    }
    @Override
    public String toString() {
        return this.getAction()+". "+name();
    }

}
