package Model;

public enum CardEnum {
    ACE(1),
    TWO(2),
    THREE(4),
    FOUR(8),
    FIVE(16),
    SIX(32),
    SEVEN(64),
    EIGHT(128),
    NINE(256),
    TEN(512),
    JACK(1028),
    QUEEN(2056),
    KING(4112);

    private final int value;

    CardEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
