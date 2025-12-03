import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class BankStatementHTMLParserTest
{
    BankStatementHTMLParser bankStatementHTMLParser = new BankStatementHTMLParser();
    @Test
    public void shouldParseOneLine()
    {
        String line = "<tr><td>Salary</td><td>18-08-2020</td><td>190000</td></tr>";

        BankTransaction expected = new BankTransaction("Salary",
                LocalDate.of(2020, 8, 18),
                190000);

        BankTransaction result = bankStatementHTMLParser.parseFrom(line);

        Assert.assertEquals(expected.getDescription(), result.getDescription());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDate(), result.getDate());
    }
}
