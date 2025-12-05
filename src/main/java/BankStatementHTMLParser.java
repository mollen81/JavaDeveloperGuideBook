import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankStatementHTMLParser implements BankStatementParser
{

    @Override
    public BankTransaction parseFrom(String line) {

        line = line.replaceAll("<tr>", "");
        line = line.replaceAll("</tr>", "");
        line = line.replaceAll("</td><td>", ",");
        line = line.replaceAll("<td>", "");
        line = line.replaceAll("</td>", "");

        String[] columns = line.split(",");
        String description = columns[0];
        LocalDate date = LocalDate.parse(columns[1], DATE_PATTERN);
        double amount = Double.parseDouble(columns[2]);

        return new BankTransaction(description, date, amount);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines)
    {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        String linesTotext = lines.toString();
        linesTotext = linesTotext.substring(linesTotext.indexOf("<tbody>"), linesTotext.indexOf("</tbody>"));
        linesTotext = linesTotext.substring(linesTotext.indexOf("<tr>"), linesTotext.lastIndexOf("</tr>"));
        linesTotext = linesTotext.replaceAll("</tr><tr>", ",");

        String[] newLines = linesTotext.split(",");

        for(String line : newLines)
        {
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }


}
