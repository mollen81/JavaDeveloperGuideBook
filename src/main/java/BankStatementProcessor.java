import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor
{
    static List<BankTransaction> bankTransactions = new ArrayList<>();

    public BankStatementProcessor(List<BankTransaction> bankTransactions)
    {
        this.bankTransactions = bankTransactions;
    }

    // функция, считающий общую сумму по всем транзакциям
    public static double calculateTotalAmount()
    {
        double amount = 0;
        for(BankTransaction bankTransaction : bankTransactions)
        {
            amount += bankTransaction.getAmount();
        }

        return amount;
    }

    // функция, считающая общую сумму в конкретном месяце
    public static double calculateTotalAmountInMonth(Month month)
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
    public static double calculateTotalAmountForCategory(String category)
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

    public static double calculateTotalAmountForCategoryInMonth(String category, Month month)
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
