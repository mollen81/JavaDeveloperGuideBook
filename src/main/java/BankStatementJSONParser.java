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

        for(int i = 0; i < columns.length; i++)
        {
            if(columns[i].substring(columns[i].indexOf(":") + 2, columns[i].length() - 1).startsWith("\""))
            {
                columns[i] = columns[i].substring(columns[i].indexOf(":") + 3, columns[i].length() - 1);
            }
            else
            {
                columns[i] = columns[i].substring(columns[i].indexOf(":") + 2, columns[i].length() - 1);
            }
        }

        String description = columns[0];
        LocalDate date = LocalDate.parse(columns[1], DATE_PATTERN);
        double amount = Double.parseDouble(columns[2]);

        return new BankTransaction(description, date, amount);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines)
    {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        for(String line : lines)
        {
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }
}
