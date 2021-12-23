package by.stepanovich.xmlparsers.parser.dom;

import by.stepanovich.xmlparsers.exception.TaskParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import by.stepanovich.xmlparsers.entity.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class DomBuilder {
    private static final long serialVersionUID = 127L;
    private static final Logger LOGGER = LogManager.getLogger();
    private Set<Tariff> tariffs;
    private DocumentBuilder docBuilder;

    public DomBuilder() {
        tariffs = new TreeSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Exception in  DomBuilder", e);
        }
    }

    public void buildSetTariffs(String filename) throws TaskParserException {
        Document doc;
        if (filename == null) {
            LOGGER.error("exception in DomBuilder buildSetTariffs filename is null");
            throw new TaskParserException("filename is null");
        }
        try {
            doc = docBuilder.parse(new File(filename));
            Element root = doc.getDocumentElement();
            NodeList tariffList = root.getElementsByTagName("tariff");
            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                Tariff tariff = buildTariff(tariffElement);
                tariffs.add(tariff);
            }
        } catch (IOException | SAXException e) {
            LOGGER.error("Exception in method buildSetTariffs ", e);
        }
    }

    private Tariff buildTariff(Element tariffElement) {
        Tariff tariff = new Tariff();
        tariff.setCallPrices(new CallPrices());
        tariff.setParameters(new Parameters());

        tariff.setTariffID(Integer.valueOf(getElementTextContent(tariffElement, TariffXmlTag.TARIFFID.getValue())));
        tariff.setTariffName(getElementTextContent(tariffElement, TariffXmlTag.TARIFFNAME.getValue()));
        tariff.setOperatorName(Operator.valueOf(getElementTextContent(tariffElement, TariffXmlTag.OPERATORNAME.getValue())));
        tariff.setPayroll(Double.valueOf(getElementTextContent(tariffElement, TariffXmlTag.PAYROLL.getValue())));

        Element callPricesElement = (Element) tariffElement.getElementsByTagName( TariffXmlTag.CALLPRICES.getValue()).item(0);
        tariff.getCallPrices().setCityCallPrice(Double.valueOf(getElementTextContent(callPricesElement, TariffXmlTag.INTERNET.getValue())));
        tariff.getCallPrices().setOuterCallPrice(Double.valueOf(getElementTextContent(callPricesElement, TariffXmlTag.OUTERCALLPRICE.getValue())));
        tariff.getCallPrices().setInnerCallPrice(Double.valueOf(getElementTextContent(callPricesElement, TariffXmlTag.INNERCALLPRICE.getValue())));
        tariff.setSmsPrice(Double.valueOf(getElementTextContent(tariffElement, TariffXmlTag.SMSPRICE.getValue())));

        Element parametersElement = (Element) tariffElement.getElementsByTagName(TariffXmlTag.PARAMETERS.getValue()).item(0);
        tariff.getParameters().setFavoriteNumbersAvailable(Integer.valueOf(getElementTextContent(parametersElement, TariffXmlTag.FAVORITENUMBERSAVAILABLE.getValue())));
        tariff.getParameters().setTariffication(Tariffication.valueOf((getElementTextContent(parametersElement, TariffXmlTag.TARIFFICATION.getValue()))));
        tariff.getParameters().setConnectPrice(Double.valueOf(getElementTextContent(parametersElement, TariffXmlTag.CONNECTPRICE.getValue())));
        tariff.setOpenDate(LocalDate.parse(getElementTextContent(tariffElement, TariffXmlTag.OPENDATE.getValue())));
        return tariff;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    public Set<Tariff> getTariffs() {
        return tariffs;
    }
}
