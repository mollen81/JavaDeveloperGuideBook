import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementJSONParserTest
{
    BankStatementParser bankStatementJSONParser = new BankStatementJSONParser();
    @Test
    public void shouldParseOneCorrectLine()
    {
        String line = "{\n" +
                "  \"description\": \"Salary\",\n" +
                "  \"date\": \"18-08-2020\",\n" +
                "  \"amount\": 190000}";
        BankTransaction expected = new BankTransaction(
                "Salary",
                LocalDate.of(2020, Month.AUGUST, 18),
                190000);

        BankTransaction result = bankStatementJSONParser.parseFrom(line);

        double delta = 0.0d;
        Assert.assertEquals(expected.getAmount(), result.getAmount(), delta);
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseCorrectLines()
    {
        List<String> lines = new ArrayList<>();
        lines.add("[\n");
        lines.add("  {\n");
        lines.add("    \"description\": \"Salary\",\n");
        lines.add("    \"date\": \"18-08-2020\",\n");
        lines.add("    \"amount\": 190000\n");
        lines.add("  }\n");
        lines.add("]");


        List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction(
                "Salary",
                LocalDate.of(2020, Month.AUGUST, 18),
                190000));

        List<BankTransaction> result = bankStatementJSONParser.parseLinesFrom(lines);
    }

}
