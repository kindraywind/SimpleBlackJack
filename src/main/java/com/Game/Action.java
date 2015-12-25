package com.Game;

/**
 * Created by woramet on 12/21/15.
 */
public enum Action {

    //TODO: remove insurance, split
    HIT(1),
    STAND(2),
    DOUBLE(3),
    SURRENDER(4),
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
                return DOUBLE;
            case 4:
                return SURRENDER;
            default:
                return INVALID;
        }
    }
    @Override
    public String toString() {
        return this.getAction()+". "+name();
    }

}
