package jinhyuk.dto.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetMetricStatisticListResponse {
    private List<MetricStatistic> metricStatisticList;
}
