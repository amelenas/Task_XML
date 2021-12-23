package by.stepanovich.xmlparsers.parser.stax;

import by.stepanovich.xmlparsers.exception.TaskParserException;
import by.stepanovich.xmlparsers.parser.sax.TariffBuilder;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TariffStaxBuilderTest {

    @Test
    public void buildSetTariffs() throws TaskParserException {
        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        staxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(staxBuilder.getTariffs());

        Assert.assertTrue(saxBuilder.getTariffs().equals(staxBuilder.getTariffs()));
    }

    @Test
    public void buildSetTariffsSameAsSax() throws TaskParserException {
        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        staxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(staxBuilder.getTariffs());

        Assert.assertTrue(saxBuilder.getTariffs().equals(staxBuilder.getTariffs()));
    }

    @Test (expected = TaskParserException.class)
    public void buildSNullTariffs() throws TaskParserException {
        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        staxBuilder.buildSetTariffs(null);
        System.out.println(staxBuilder.getTariffs());
    }
}