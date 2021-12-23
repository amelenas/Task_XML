package by.stepanovich.xmlparsers.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class BaseValidatorMain {
    private static final Logger LOGGER = LogManager.getLogger(BaseValidatorMain.class);
    public boolean isFileValid(File fileName, File schemaName) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        if (fileName == null || schemaName == null) {return false;}
        try {
            Schema schema = factory.newSchema(schemaName);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.setErrorHandler(new TariffErrorHandler());
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            LOGGER.error(fileName + " is not correct or valid");
        }
        return false;
    }
}