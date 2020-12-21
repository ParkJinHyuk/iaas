package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MetricStatistic {
    private String instanceNo;
    private List<MetricData> metricDataList;
}
