package by.stepanovich.xmlparsers.parser.stax;

import by.stepanovich.xmlparsers.entity.*;
import by.stepanovich.xmlparsers.exception.TaskParserException;
import by.stepanovich.xmlparsers.parser.sax.TariffHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class TariffStaxBuilder {
    private static final Logger LOGGER = LogManager.getLogger(TariffStaxBuilder.class);
    private Set<Tariff> tariffs;
    private XMLInputFactory inputFactory;

    public TariffStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        tariffs = new TreeSet<>();
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void buildSetTariffs(String filename) throws TaskParserException {
        if (filename == null) {
            LOGGER.error("Exception in TariffStaxBuilder in method buildSetTariffs filename is null");
            throw new TaskParserException("Exception in TariffStaxBuilder in method buildSetTariffs filename is null");
        }else {
            XMLStreamReader reader;
            String name;
            try (FileInputStream inputStream = new FileInputStream(filename)) {
                reader = inputFactory.createXMLStreamReader(inputStream);
                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamConstants.START_ELEMENT) {
                        name = reader.getLocalName();
                        if (name.equals(TariffXmlTag.TARIFF.getValue())) {
                            Tariff tariff = buildTariff(reader);
                            tariffs.add(tariff);
                        }
                    }
                }
            } catch (XMLStreamException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
        Tariff tariff = new Tariff();
        tariff.setParameters(new Parameters());
        tariff.setTariffID(Integer.valueOf(reader.getAttributeValue(null, TariffXmlTag.TARIFFID.getValue())));
        tariff.setSmsPrice(Double.valueOf(reader.getAttributeValue(null, TariffXmlTag.SMSPRICE.getValue())));

        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {

                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.valueOf(name.toUpperCase())) {
                        case SMSPRICE -> tariff.setSmsPrice(Double.valueOf((getXMLText(reader))));
                        case PARAMETERS -> tariff.setParameters(getXMLParameters(reader));
                        case TARIFFNAME -> tariff.setTariffName(getXMLText(reader));
                        case OPERATORNAME -> tariff.setOperatorName(Operator.valueOf((getXMLText(reader))));
                        case PAYROLL -> tariff.setPayroll(Double.valueOf((getXMLText(reader))));
                        case TARIFFID -> tariff.setTariffID(Integer.valueOf(getXMLText(reader)));
                        case OPENDATE -> tariff.setOpenDate(LocalDate.parse((getXMLText(reader))));
                        case CALLPRICES -> tariff.setCallPrices(getXMLCallPrices(reader));
                            }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (TariffXmlTag.valueOf(name.toUpperCase()) == TariffXmlTag.TARIFF) {
                        return tariff;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <tariff>");
    }

    private Parameters getXMLParameters(XMLStreamReader reader) throws XMLStreamException {
        Parameters parameters = new Parameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (TariffXmlTag.valueOf(name.toUpperCase())) {
                        case FAVORITENUMBERSAVAILABLE -> parameters.setFavoriteNumbersAvailable(Integer.valueOf(getXMLText(reader)));
                        case TARIFFICATION -> parameters.setTariffication(Tariffication.valueOf((getXMLText(reader))));
                        case CONNECTPRICE -> parameters.setConnectPrice(Double.valueOf((getXMLText(reader))));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT :
                    name = reader.getLocalName();
                    if (TariffXmlTag.valueOf(name.toUpperCase()) == TariffXmlTag.PARAMETERS) {
                        return parameters;
                    }
                }
            }
        throw new XMLStreamException("Unknown element in tag <Parameters>");
    }

    private CallPrices getXMLCallPrices(XMLStreamReader reader)
            throws XMLStreamException {
        CallPrices callPrices = new CallPrices();
        int type;
        String name;
        boolean isCityCallPrice = false;
        while (reader.hasNext() && !isCityCallPrice){
                type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();

                        switch (TariffXmlTag.valueOf(name.toUpperCase())) {
                            case INNERCALLPRICE -> callPrices.setInnerCallPrice(Double.valueOf(getXMLText(reader)));
                            case OUTERCALLPRICE -> callPrices.setOuterCallPrice(Double.valueOf((getXMLText(reader))));
                            case INTERNET -> callPrices.setCityCallPrice(Double.valueOf((getXMLText(reader))));
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName();
                        if (TariffXmlTag.valueOf(name.toUpperCase()) == TariffXmlTag.CALLPRICES) {
                            return callPrices;
                        }
                }
            }
        throw new XMLStreamException("Unknown element in tag <CallPrices>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}