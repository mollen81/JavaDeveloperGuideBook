import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BankStatementProcessorTest
{
    final static List<BankTransaction> bankTransactions = Arrays.asList(new BankTransaction(
            "Salary",
            LocalDate.of(2020, Month.AUGUST, 18),
            190000), new BankTransaction(
            "Salary",
            LocalDate.of(2020, Month.AUGUST, 20),
            200000));

    final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

    @Test
    public void shouldCalculateTotalAmount()
    {
        double expected = 0.0d;
        for(int i = 0; i < bankTransactions.size(); i++)
        {
            expected += bankTransactions.get(i).getAmount();
        }

        Double result = bankStatementProcessor.calculateTotalAmount();

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForMonth()
    {
        Month month = Month.AUGUST; // месяц транзакций
        double expected = 0.0d; // ожидаемая сумма

        for(int i = 0; i < bankTransactions.size(); i++)
        {
            if(bankTransactions.get(i).getDate().getMonth() == month)
            {
                expected += bankTransactions.get(i).getAmount();
            }
        }

        double result = bankStatementProcessor.calculateTotalAmountInMonth(month); // фактическая сумма

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForCategory()
    {
        String category = "Salary";
        double expected = 0.0d;

        for(BankTransaction bankTransaction : bankTransactions)
        {
            if(bankTransaction.getDescription().equals(category))
            {
                expected += bankTransaction.getAmount();
            }
        }

        double result = bankStatementProcessor.calculateTotalAmountForCategory(category);

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForCategoryInMonth()
    {
        double expected = 0.0d;
        String category = "Salary";
        Month month = Month.AUGUST;
        for(BankTransaction bankTransaction :  bankTransactions)
        {
            if(bankTransaction.getDescription().equals(category) && bankTransaction.getDate().getMonth() == month)
            {
                expected += bankTransaction.getAmount();
            }
        }

        double result = bankStatementProcessor.calculateTotalAmountForCategoryInMonth(category, month);

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldFindLessExpensiveCategoryForMonth()
    {
        String expected = "Salary: 390000.0";
        String result = bankStatementProcessor.findLessExpensiveCategoryForMonth(Month.AUGUST);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void shouldFindMostExpensiveCategoryForMonth()
    {
        String expected = "Salary: 390000.0";
        String result = bankStatementProcessor.findMostExpensiveCategoryForMonth(Month.AUGUST);

        Assert.assertEquals(expected, result);
    }
}
