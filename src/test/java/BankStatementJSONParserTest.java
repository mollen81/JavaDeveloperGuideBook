import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementJSONParserTest
{
    BankStatementParser bankStatementJSONParser = new BankStatementJSONParser();
    private static final String RESOURCES = "src/main/resources";
    final Path path = Paths.get(RESOURCES + "/transactions.json");


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
        lines.add("  },\n");
        lines.add("  {\n");
        lines.add("    \"description\": \"Salary\",\n");
        lines.add("    \"date\": \"18-08-2020\",\n");
        lines.add("    \"amount\": 200000\n");
        lines.add("  }\n");
        lines.add("]");



        List<BankTransaction> expected = new ArrayList<>();
        expected.add(new BankTransaction(
                "Salary",
                LocalDate.of(2020, Month.AUGUST, 18),
                190000));
        expected.add(new BankTransaction(
                "Salary",
                LocalDate.of(2020, Month.AUGUST, 18),
                200000));

        List<BankTransaction> result = bankStatementJSONParser.parseLinesFrom(lines);

        for(int i = 0; i < result.size(); i++)
        {
            Assert.assertEquals(result.get(i).getDate(), expected.get(i).getDate());
            Assert.assertEquals(result.get(i).getAmount(), expected.get(i).getAmount(), 0.0d);
            Assert.assertEquals(result.get(i).getDescription(), expected.get(i).getDescription());
        }
    }

}
