import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest
{
    BankStatementParser bankStatementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine()
    {
        String line = "Salary,18-08-2020,190000";

        BankTransaction expected = new BankTransaction(
                "Salary",
                LocalDate.of(2020, Month.AUGUST, 18),
                190000);

        BankTransaction result = bankStatementParser.parseFrom(line);

        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }
}
