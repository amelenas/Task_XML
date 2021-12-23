package by.stepanovich.xmlparsers.parser.dom;

import by.stepanovich.xmlparsers.exception.TaskParserException;
import by.stepanovich.xmlparsers.parser.sax.TariffBuilder;
import by.stepanovich.xmlparsers.parser.stax.TariffStaxBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DomBuilderTest {

    @Test
    public void buildSetTariffs() throws TaskParserException {
        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(domBuilder.getTariffs());
        Assert.assertTrue(saxBuilder.getTariffs().equals(domBuilder.getTariffs()));
    }

    @Test
    public void buildSetTariffsSameAsStax() throws TaskParserException {
        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        staxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(domBuilder.getTariffs());
        Assert.assertTrue(staxBuilder.getTariffs().equals(domBuilder.getTariffs()));
    }
    @Test (expected = TaskParserException.class)
    public void buildТNullTariffs() throws TaskParserException {
        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetTariffs(null);
    }
    @Test
    public void buildТInvalidFile() throws TaskParserException {
        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetTariffs("src/main/resources/not_valid_file_tariffs.xml");
    }
}