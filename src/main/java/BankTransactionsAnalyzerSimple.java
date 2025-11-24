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

        System.out.println("Общая сумма транзакций: " + calculateTotalAmount(bankTransactions) + "\n"
        + "Сумма транзакций в Августе: " + calculateTotalAmountInMonth(bankTransactions, Month.AUGUST) + "\n"
        + "Сумма транзакций в Сентябре: " + calculateTotalAmountInMonth(bankTransactions, Month.SEPTEMBER) + "\n"
        + "Сумма транзакций по Salary: " + calculateTotalAmountForCategory(bankTransactions, "Salary") + "\n");

    }


    // функция, считающий общую сумму по всем транзакциям
    private static double calculateTotalAmount(List<BankTransaction> bankTransactions)
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            amount += bankTransaction.getAmount();
        }

        return amount;
    }

    // функция, считающая общую сумму в конкретном месяце
    private static double calculateTotalAmountInMonth(List<BankTransaction> bankTransactions, Month month)
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(bankTransaction.getDate().getMonth() == month)
            {
                amount += bankTransaction.getAmount();
            }
        }

        return amount;
    }

    // функция, считающая сумму транзакций по категориям
    private static double calculateTotalAmountForCategory(List<BankTransaction> bankTransactions, String category)
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(bankTransaction.getDescription().equals(category))
            {
                amount += bankTransaction.getAmount();
            }
        }

        return amount;
    }
}
