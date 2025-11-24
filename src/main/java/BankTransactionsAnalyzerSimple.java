import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionsAnalyzerSimple
{
    private static final String RESOURCES = "src/main/resources";
    static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(String... args) throws IOException
    {
        final Path path = Paths.get(RESOURCES + "/transactions.csv");
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);

        System.out.println("Общая сумма транзакций: " + BankStatementProcessor.calculateTotalAmount(bankTransactions) + "\n"
        + "Сумма транзакций в Августе: " + BankStatementProcessor.calculateTotalAmountInMonth(bankTransactions, Month.AUGUST) + "\n"
        + "Сумма транзакций в Сентябре: " + BankStatementProcessor.calculateTotalAmountInMonth(bankTransactions, Month.SEPTEMBER) + "\n"
        + "Сумма транзакций по Salary: " + BankStatementProcessor.calculateTotalAmountForCategory(bankTransactions, "Salary") + "\n");

    }

}
