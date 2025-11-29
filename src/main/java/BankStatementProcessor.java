import java.time.Month;
import java.util.List;

public class BankStatementProcessor
{
    private static List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions)
    {
        BankStatementProcessor.bankTransactions = bankTransactions;
    }

    // функция, считающий общую сумму по всем транзакциям
    public double calculateTotalAmount()
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            amount += bankTransaction.getAmount();
        }

        return amount;
    }

    // функция, считающая общую сумму в конкретном месяце
    public double calculateTotalAmountInMonth(Month month)
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
    public double calculateTotalAmountForCategory(String category)
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

    public double calculateTotalAmountForCategoryInMonth(String category, Month month)
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(category.equals(bankTransaction.getDescription()) && month == bankTransaction.getDate().getMonth())
            {
                amount += bankTransaction.getAmount();
            }
        }

        return amount;
    }



}
