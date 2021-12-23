package by.stepanovich.xmlparsers.entity;

public enum Tariffication {
    PER_MINUTES ("minutes"),
    PER_1_MONTHS("months");

    private final String value;

    Tariffication(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Tariffication fromValue(String value) {
        for (Tariffication currentEnum: Tariffication.values()) {
            if (currentEnum.value.equals(value)) {
                return currentEnum;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
