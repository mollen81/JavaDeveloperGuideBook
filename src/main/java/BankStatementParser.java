import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface BankStatementParser
{
    static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    BankTransaction parseFrom(final String line);
    List<BankTransaction> parseLinesFrom(final List<String> lines);
}
