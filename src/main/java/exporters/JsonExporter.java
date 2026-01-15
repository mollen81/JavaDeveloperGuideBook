package exporters;

import domain_objects.SummaryStatistics;

public class JsonExporter implements Exporter
{
    @Override
    public String export(SummaryStatistics statistics)
    {
        String result = "{";
        result += "sum: " + statistics.getSum() + ", ";
        result += "max: " + statistics.getMax() + ", ";
        result += "min: " + statistics.getMin() + ", ";
        result += "average: " + statistics.getAverage() + ", ";

        return result;
    }
}
