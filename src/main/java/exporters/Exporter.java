package exporters;
import domain_objects.SummaryStatistics;

public interface Exporter
{
    String export(SummaryStatistics statistics);
}
