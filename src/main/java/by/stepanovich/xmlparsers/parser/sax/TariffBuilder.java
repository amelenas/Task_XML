package by.stepanovich.xmlparsers.parser.sax;

import by.stepanovich.xmlparsers.validator.TariffErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import by.stepanovich.xmlparsers.entity.Tariff;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

public class TariffBuilder {
    private static Logger LOGGER = LogManager.getLogger();
    private Set<Tariff> tariffs;
    private TariffHandler handler = new TariffHandler();
    private XMLReader reader;

    public TariffBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            LOGGER.error("Exception TariffBuilder ", e);
        }
        reader.setErrorHandler(new TariffErrorHandler());
        reader.setContentHandler(handler);
    }

    public void buildSetTariffs(String filename) {
       try {
           reader.parse(new InputSource(new FileInputStream(filename)));
        } catch (IOException | SAXException e) {
            LOGGER.error("Exception in method getTariffs ", e);
        }
        tariffs = handler.getTariffs();
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }
}
