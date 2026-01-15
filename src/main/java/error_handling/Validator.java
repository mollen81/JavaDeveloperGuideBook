package error_handling;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Validator
{
    private final String description;
    private final String date;
    private final String amount;


    public Validator(final String description, final String date, final String amount)
    {
        this.description = Objects.requireNonNull(description);
        this.date = Objects.requireNonNull(date);
        this.amount = Objects.requireNonNull(amount);
    }


    public Notification validate()
    {

        final Notification notification = new Notification();


        // Description validate
        if(this.description.length() > 100)
        {
            notification.addError("The description is too long");
        }

        // Date validate
        final LocalDate parsedDate;
        try
        {
            parsedDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            if(parsedDate.isAfter(LocalDate.now()))
            {
                notification.addError("Date cannot be in the future");
            }
        }
        catch (DateTimeParseException e)
        {
            notification.addError("Invalid format for date");
        }


        // Amount validate
        final double amount;
        try
        {
            amount = Double.parseDouble(this.amount);
        }
        catch (NumberFormatException e)
        {
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
