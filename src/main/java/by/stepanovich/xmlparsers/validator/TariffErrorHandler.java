package by.stepanovich.xmlparsers.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class TariffErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    public void warning(SAXParseException e) {
        LOGGER.warn(getLineColumnNumber(e) + "-" + e.getMessage());
    }

    public void error(SAXParseException e) {
        LOGGER.error(getLineColumnNumber(e) + " - " + e.getMessage());
    }

    public void fatalError(SAXParseException e) {
        LOGGER.fatal(getLineColumnNumber(e) + " - " + e.getMessage());
    }

    private String getLineColumnNumber(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
