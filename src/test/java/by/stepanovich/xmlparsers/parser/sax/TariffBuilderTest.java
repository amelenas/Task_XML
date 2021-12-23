package by.stepanovich.xmlparsers.parser.sax;

import by.stepanovich.xmlparsers.exception.TaskParserException;
import by.stepanovich.xmlparsers.parser.dom.DomBuilder;
import by.stepanovich.xmlparsers.parser.stax.TariffStaxBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TariffBuilderTest {

    @Test
    public void buildSetTariffs() throws TaskParserException {
        DomBuilder domBuilder = new DomBuilder();
        domBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(saxBuilder.getTariffs());

        Assert.assertTrue(saxBuilder.getTariffs().equals(domBuilder.getTariffs()));
    }

    @Test
    public void buildSetTariffsSameAsStax() throws TaskParserException {
        TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
        staxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");

        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/tariffs.xml");
        System.out.println(saxBuilder.getTariffs());

        Assert.assertTrue(saxBuilder.getTariffs().equals(staxBuilder.getTariffs()));
    }

    @Test
    public void buildNullTariffs(){
        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("null");
        System.out.println(saxBuilder.getTariffs());
    }

    @Test
    public void buildNotValidTariffs(){
        TariffBuilder saxBuilder = new TariffBuilder();
        saxBuilder.buildSetTariffs("src/main/resources/not_valid_file_tariffs.xml");
        System.out.println(saxBuilder.getTariffs());
    }
}