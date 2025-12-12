import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankTransactionsAnalyzerSimple
{
    private static final String RESOURCES = "src/main/resources";
    static final BankStatementParser bankStatementParser = new BankStatementHTMLParser();

    public static void main(String... args) throws IOException
    {
        final Path path = Paths.get(RESOURCES + "/transactions.html");
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    static void collectSummary(BankStatementProcessor bankStatementProcessor)
    {
        System.out.println("Общая сумма транзакций: " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("Сумма транзакций в Августе: " + bankStatementProcessor.calculateTotalAmountInMonth(Month.AUGUST));
        System.out.println("Сумма транзакций в Сентябре: " + bankStatementProcessor.calculateTotalAmountInMonth(Month.SEPTEMBER));
        System.out.println("Сумма транзакций по Salary: " + bankStatementProcessor.calculateTotalAmountForCategory("Salary"));
    }

}
