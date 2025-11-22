import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionsAnalyzerSimple
{
    private static final String RESOURCES = "src/main/resources";

    public static void main(String... args) throws IOException
    {
        final Path path = Paths.get(RESOURCES + "/transactions.csv");
        List<String> lines = Files.readAllLines(path);
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        // простой подсчет общей суммы транзакций
        double totalAmount = 0;

        for(String el : lines)
        {
            String[] columns = el.split(",");

            totalAmount += Double.parseDouble(columns[2]); // парсим в Double и прибавляем к общей сумме
        }

        // вывод
        System.out.println("Total amount: " + totalAmount);


        // простой подсчет суммы транзакций в августе
        double augAmount = 0;

        for(String el : lines)
        {
            String[] columns = el.split(",");
            final LocalDate date = LocalDate.parse(columns[1], DATE_PATTERN); // считываем дату по формату DATE_PATTERN
            if(date.getMonth() == Month.AUGUST)
            {
                augAmount += Double.parseDouble(columns[2]); // парсим в Double и прибавляем к сумме транзакций в августе
            }
        }

        // вывод
        System.out.println("Total amount in August: " + augAmount);
    }
}
