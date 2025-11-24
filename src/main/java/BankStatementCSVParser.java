import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser
{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // парсер строки в объект класса BankTransaction
    private BankTransaction parseFromCSV(final String line)
    {
        String[] columns = line.split(",");

        final String description = columns[0];
        final LocalDate date = LocalDate.parse(columns[1], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[2]);


        return new BankTransaction(description, date, amount);
    }


    // парсер всех строк CSV файла в массив объектов класса BankTransaction
    List<BankTransaction> parseLinesFromCSV(final List<String> lines)
    {
        final List<BankTransaction> bankTransactions = new ArrayList<>();

        for (String line : lines)
        {
            bankTransactions.add(parseFromCSV(line));
        }

        return bankTransactions;
    }

}
