package by.stepanovich.xmlparsers.entity;
public enum TariffXmlTag {
    TARIFFS("tariffs"),
    CALLPRICES("callPrices"),
    PARAMETERS("parameters"),
    TARIFF("tariff"),
    TARIFFNAME("tariffName"),
    OPERATORNAME("operatorName"),
    TARIFFID("tariffID"),
    PAYROLL("payroll"),
    INNERCALLPRICE("innerCallPrice"),
    OUTERCALLPRICE("outerCallPrice"),
    INTERNET("internet"),
    SMSPRICE("smsPrice"),
    FAVORITENUMBERSAVAILABLE("favoriteNumbersAvailable"),
    TARIFFICATION("tariffication"),
    CONNECTPRICE("connectPrice"),
    OPENDATE("openDate");

    private String value;
    TariffXmlTag(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
