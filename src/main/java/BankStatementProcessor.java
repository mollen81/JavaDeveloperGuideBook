import java.time.Month;
import java.util.List;

public class BankStatementProcessor
{
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions)
    {
        this.bankTransactions = bankTransactions;
    }

    // функция, считающий общую сумму по всем транзакциям
    public static double calculateTotalAmount(List<BankTransaction> bankTransactions)
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            amount += bankTransaction.getAmount();
        }

        return amount;
    }

    // функция, считающая общую сумму в конкретном месяце
    public static double calculateTotalAmountInMonth(List<BankTransaction> bankTransactions, Month month)
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
    public static double calculateTotalAmountForCategory(List<BankTransaction> bankTransactions, String category)
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
