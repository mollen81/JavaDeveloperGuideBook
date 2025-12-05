import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

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

    @Test
    public void shouldParseCorrectLines()
    {
        List<String> lines = Arrays.asList("<!DOCTYPE html>\n", "<html>\n", "<head>\n", "    <meta charset='UTF-8'>\n",
                "<title>JSON To HTML using codebeautify.org</title>\n", "</head>\n", "<body>\n", "<table border=1>\n",
                "    <thead>\n", "    <tr>\n", "        <th>description</th>\n", "        <th>date</th>\n",
                "        <th>amount</th>\n", "    </tr>\n", "    </thead>\n", "    <tbody>\n", "    <tr>\n",
                "        <td>Salary</td>\n", "        <td>18-08-2020</td>\n", "        <td>190000</td>\n",
                "    </tr>\n", "    <tr>\n", "        <td>Salary</td>\n", "        <td>20-08-2020</td>\n",
                "        <td>200000</td>\n", "    </tr>\n", "    </tbody>\n", "</table>\n", "</body>\n", "</html>\n");

        List<BankTransaction> expected = Arrays.asList(new BankTransaction("Salary",
                LocalDate.of(2020, 8, 18), 190000),
                new BankTransaction("Salary", LocalDate.of(2020, 8, 18),
                        200000));

        List<BankTransaction> result = bankStatementHTMLParser.parseLinesFrom(lines);

        for(int i = 0; i < expected.size(); i++)
        {
            Assert.assertEquals(expected.get(i).getDate(), result.get(i).getDate());
            Assert.assertEquals(expected.get(i).getDescription(), result.get(i).getDescription());
            Assert.assertEquals(expected.get(i).getAmount(), result.get(i).getAmount(), 0.0d);
        }
    }
}