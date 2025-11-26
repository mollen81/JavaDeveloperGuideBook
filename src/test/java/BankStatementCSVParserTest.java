import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void shouldParseCorrectLines()
    {
        List<String> lines = new ArrayList<>();
        lines.add("Salary,18-08-2020,190000");
        lines.add("Salary,20-08-2020,200000");

        List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction("Salary", LocalDate.of(2020, Month.AUGUST, 18), 190000));
        expected.add(new BankTransaction("Salary", LocalDate.of(2020, Month.AUGUST, 20), 200000));

        List<BankTransaction> result = bankStatementParser.parseLinesFrom(lines);

        for(int i = 0; i < result.size(); i++)
        {
            Assert.assertEquals(result.get(i).getDescription(), expected.get(i).getDescription());
            Assert.assertEquals(result.get(i).getAmount(), expected.get(i).getAmount(), 0.0d);
            Assert.assertEquals(result.get(i).getDate(), expected.get(i).getDate());
        }
    }
}
