package by.stepanovich.xmlparsers.service;

import by.stepanovich.xmlparsers.exception.TaskParserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.stepanovich.xmlparsers.entity.Tariff;
import by.stepanovich.xmlparsers.parser.dom.DomBuilder;
import by.stepanovich.xmlparsers.parser.sax.TariffBuilder;
import by.stepanovich.xmlparsers.parser.stax.TariffStaxBuilder;
import by.stepanovich.xmlparsers.tablebuild.TariffSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class ParsersHandler extends HttpServlet {
    private static final long serialVersionUID = 192L;
    private static final Logger LOGGER = LogManager.getLogger(ParsersHandler.class);
    private static String filePath;

    public ParsersHandler() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("parsers.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        Set<Tariff> set = null;
        String parser = request.getParameter("parser");

        switch (parser) {
            case "DOM": {
                LOGGER.info("im DOM");
                DomBuilder domBuilder = new DomBuilder();
                try {
                    domBuilder.buildSetTariffs(filePath);
                } catch (TaskParserException e) {
                    LOGGER.error(e);
                }
                set = domBuilder.getTariffs();
                break;
            }
            case "SAX": {
                TariffBuilder saxBuilder = new TariffBuilder();
                saxBuilder.buildSetTariffs(filePath);
                set = saxBuilder.getTariffs();
                break;
            }
            case "StAX": {
                TariffStaxBuilder staxBuilder = new TariffStaxBuilder();
                try {
                    staxBuilder.buildSetTariffs(filePath);
                } catch (TaskParserException e) {
                    LOGGER.error(e);
                }
                set = staxBuilder.getTariffs();
                break;
            }

            default:
                LOGGER.info("Incorrect Parser type " + parser);
        }
        TariffSet tariffbean = new TariffSet(set);
        request.setAttribute("tariffbean", tariffbean);
        try {
            request.getRequestDispatcher("table.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
           LOGGER.error("Exception in method doPost", e);
        }
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        ParsersHandler.filePath = filePath;
    }
}
