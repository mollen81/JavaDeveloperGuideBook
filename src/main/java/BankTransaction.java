import java.time.LocalDate;
import java.util.Objects;

// класс, описывающий транзакцию
public class BankTransaction
{
    private String description;
    private LocalDate date;
    private double amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public BankTransaction(String description, LocalDate date, double amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BankTransaction that = (BankTransaction) o;
        return Double.compare(amount, that.amount) == 0 &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, date, amount);
    }
}
