package by.stepanovich.xmlparsers.tablebuild;

import by.stepanovich.xmlparsers.entity.Tariff;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;

public class TableBuilder extends TagSupport {
    private static final long serialVersionUID = 11234L;
    private TariffSet set;

    public TariffSet getSet() {
        return set;
    }
    public void setSet(TariffSet set) {
        this.set = set;
    }

    @Override
    public int doStartTag() {
        ArrayList<Tariff> tariffs = new ArrayList<>();
        StringBuilder row;
        ArrayList<String> strings = new ArrayList<>();
       try {
            JspWriter out = pageContext.getOut();
            out.write("<html>");
            out.write("<head>");
            out.write("<title>Servlet upload</title>");
            out.write("</head>");
            out.write("<body>");
            out.write("<div align = center>");
            out.write("Tariffs");
            out.write("</div>");
            out.write("<table border=2 name=Tariffs>");
            out.write("<tr><th>Tariff Name</th><th>Tariff ID</th><th>Operator</th>" +
                    "<th>Inner Call Price</th><th>Outer Call Price</th><th>Internet GB</th><th>Payroll</th>" +
                    "<th>SMS Price</th><th>Amount of Favorite Numbers</th><th>Tariffication</th>" +
                    "<th>After GB end 100MB Price</th></tr>");
           for (Object objTariff : set.getSet()) {
               tariffs.add((Tariff) objTariff);
           }
           for (Tariff tariff : tariffs) {
               row = new StringBuilder();
               row.append("<tr>");
               row.append("<td>");
               row.append(tariff.getTariffName());
               row.append("</td><td>");
               row.append(tariff.getTariffID());
               row.append("</td><td>");
               row.append(tariff.getOperatorName().toString());
               row.append("</td><td>");
               row.append(tariff.getCallPrices().getInnerCallPrice().toString());
               row.append("</td><td>");
               row.append(tariff.getCallPrices().getOuterCallPrice().toString());
               row.append("</td><td>");
               row.append(tariff.getCallPrices().getCityCallPrice().toString());
               row.append("</td><td>");
               row.append(tariff.getPayroll().toString());
               row.append("</td><td>");
               row.append(tariff.getSmsPrice().toString());
               row.append("</td><td>");
               row.append(tariff.getParameters().getFavoriteNumbersAvailable().toString());
               row.append("</td><td>");
               row.append(tariff.getParameters().getTariffication().toString());
               row.append("</td><td>");
               row.append(tariff.getParameters().getConnectPrice().toString());
               row.append("</td>");
               row.append("</tr>");
               strings.add(row.toString());
           }
               for (String string : strings) {
                   out.write(string);
               }
               out.write("</table>");
               out.write("<br><br>");
               out.write("<a href = parsers.jsp>BACK TO PARSERS</a></div>");
               out.write("<br><br>");
               out.write("<a href = index.jsp>BACK TO MAIN PAGE</a></div>");
               out.write("</body>");
               out.write("</html>");
               out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
