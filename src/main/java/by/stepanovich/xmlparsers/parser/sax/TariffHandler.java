package by.stepanovich.xmlparsers.parser.sax;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import by.stepanovich.xmlparsers.entity.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;

public class TariffHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger(TariffHandler.class);
    private Set<Tariff> tariffs;
    private Tariff current;
    private TariffXmlTag currentXmlTag;
    private EnumSet<TariffXmlTag> withText;
    private String element = "tariff";

    public TariffHandler() {
        tariffs = new TreeSet<>();
        withText = EnumSet.range(TariffXmlTag.TARIFF, TariffXmlTag.OPENDATE);
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (element.equals(qName)) {
            current = new Tariff();
            current.setCallPrices(new CallPrices());
            current.setParameters(new Parameters());

        } else {
            try {
                TariffXmlTag temp = TariffXmlTag.valueOf(qName.toUpperCase());
                if (withText.contains(temp)) {
                    currentXmlTag = temp;
                }
            }catch (IllegalArgumentException e){
                LOGGER.error(e);
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (element.equals(qName)) {
            tariffs.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {

            switch (currentXmlTag) {
                case TARIFFNAME -> current.setTariffName(data);
                case OPERATORNAME -> current.setOperatorName(Operator.valueOf(data));
                case TARIFFID -> current.setTariffID(Integer.valueOf(data));
                case PAYROLL -> current.setPayroll(Double.valueOf(data));
                case INTERNET -> current.getCallPrices().setCityCallPrice(Double.valueOf(data));
                case INNERCALLPRICE -> current.getCallPrices().setInnerCallPrice(Double.valueOf(data));
                case OUTERCALLPRICE -> current.getCallPrices().setOuterCallPrice(Double.valueOf(data));
                case SMSPRICE -> current.setSmsPrice(Double.valueOf(data));
                case FAVORITENUMBERSAVAILABLE -> current.getParameters().setFavoriteNumbersAvailable(Integer.valueOf(data));
                case CONNECTPRICE -> current.getParameters().setConnectPrice(Double.valueOf(data));
                case TARIFFICATION -> current.getParameters().setTariffication(Tariffication.valueOf(data));
                case OPENDATE -> current.setOpenDate(LocalDate.parse(data));
                default -> throw new EnumConstantNotPresentException(
                        currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }


    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

}