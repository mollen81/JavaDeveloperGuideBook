import java.time.Month;
import java.util.HashMap;
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

    public String findMostExpensiveCategoryForMonth(Month month)
    {
        HashMap<String, Double> categoriesTotalAmounts = new HashMap<>();
        Double maxValue = 0.0d;
        String maxCategory = "";

        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(!categoriesTotalAmounts.containsKey(bankTransaction.getDescription()))
            {
               categoriesTotalAmounts.put(bankTransaction.getDescription(), bankTransaction.getAmount());
            }
            else
            {
                categoriesTotalAmounts.replace(bankTransaction.getDescription(),
                        bankTransaction.getAmount(),
                        categoriesTotalAmounts.get(bankTransaction.getDescription()) + bankTransaction.getAmount());
            }
        }

        for(HashMap.Entry<String, Double> entry : categoriesTotalAmounts.entrySet())
        {
            String key = entry.getKey();
            Double value = entry.getValue();

            if(maxValue < value)
            {
                maxValue = value;
                maxCategory = key;
            }
        }

        return maxCategory.concat(": ".concat(maxValue.toString()));
    }

    public String findLessExpensiveCategoryForMonth(Month month)
    {
        HashMap<String, Double> categoriesTotalAmounts = new HashMap<>();
        Double minValue = 0.0d;
        String minCategory = "";

        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(!categoriesTotalAmounts.containsKey(bankTransaction.getDescription()))
            {
                categoriesTotalAmounts.put(bankTransaction.getDescription(), bankTransaction.getAmount());
            }
            else
            {
                categoriesTotalAmounts.replace(bankTransaction.getDescription(),
                        bankTransaction.getAmount(),
                        categoriesTotalAmounts.get(bankTransaction.getDescription()) + bankTransaction.getAmount());
            }
        }

        for(HashMap.Entry<String, Double> entry : categoriesTotalAmounts.entrySet())
        {
            String key = entry.getKey();
            Double value = entry.getValue();

            if(minValue > value)
            {
                minValue = value;
                minCategory = minCategory;
            }
        }

        return minCategory.concat(": ".concat(minValue.toString()));
    }


}
