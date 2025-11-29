import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankStatementJSONParser implements BankStatementParser
{
    @Override
    public BankTransaction parseFrom(String line)
    {
        String[] columns;
        columns = line.split(",");

        String description = "";
        LocalDate date = null;
        double amount = 0.0d;

        for(int i = 0; i < columns.length; i++)
        {
            String field = columns[i].substring(columns[i].indexOf("\"") + 1, columns[i].indexOf(":") - 1);
            switch (field)
            {
                case "amount":
                    amount = Double.parseDouble(columns[i].substring(columns[i].indexOf(":") + 2, columns[i].length() - 1));
                    break;
                case "description":
                    description = columns[i].substring(columns[i].indexOf(":") + 3, columns[i].length() - 1);
                    break;
                case "date":
                    date = LocalDate.parse(columns[i].substring(columns[i].indexOf(":") + 3, columns[i].length() - 1), DATE_PATTERN);
                    break;
                default:
                    amount = 0.0d;
                    description = null;
                    date = null;
            }
        }


        return new BankTransaction(description, date, amount);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines)
    {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        String text = toString(lines);
        text = text.replaceAll("\n,", "");
        text = text.substring(text.indexOf("{"), text.lastIndexOf("}"));

        String[] newLines = text.split("},");

        for(String line : newLines)
        {
            BankTransaction bankTransaction = parseFrom(line);
        }

        return bankTransactions;
    }


    private static String toString(List<String> lines)
    {
        String result = "";
        for(String line : lines)
        {
            result = result.concat(line);
        }

        return result;
    }
}
