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
        double expected = 190000+200000;
        Double result = bankStatementProcessor.calculateTotalAmount();

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForMonth()
    {
        double result = bankStatementProcessor.calculateTotalAmountInMonth(Month.AUGUST);
        double expected = 190000 + 200000;

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForCategory()
    {
        double result = bankStatementProcessor.calculateTotalAmountForCategory("Salary");
        double expected = 190000 + 200000;

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldCalculateTotalForCategoryInMonth()
    {
        double result = bankStatementProcessor.calculateTotalAmountForCategoryInMonth("Salary", Month.AUGUST);
        double expected = 190000 + 200000;

        Assert.assertEquals(expected, result, 0.0d);
    }

    @Test
    public void shouldFindLessExpensiveCategoryForMonth()
    {
        String expected = "Salary: 390000.0";
        String result = bankStatementProcessor.findLessExpensiveCategoryForMonth(Month.AUGUST);

        Assert.assertEquals(expected, result);
    }
}
