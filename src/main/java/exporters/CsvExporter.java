package exporters;

import domain_objects.SummaryStatistics;

public class CsvExporter implements Exporter
{
    @Override
    public String export(SummaryStatistics statistics)
    {
        String result = "Sum,Max,Min,Average";
        result += statistics.getSum() + ",";
        result += statistics.getMax() + ",";
        result += statistics.getMin() + ",";
        result += statistics.getAverage() + ",";

        return result;
    }
}
