package by.stepanovich.xmlparsers.entity;

public enum Operator {
    MTS("MTS"),
    A1("A1"),
    LIFE("LIFE"),
    BEELINE ("Beeline"),
    MEGAFON ("MegaFon");

    private final String value;

    Operator(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Operator fromValue(String value) {
        for (Operator currentEnum: Operator.values()) {
            if (currentEnum.value.equals(value)) {
                return currentEnum;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
