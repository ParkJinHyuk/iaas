package jinhyuk.dto.message;

import jinhyuk.domain.clusters.Clusters;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Getter
public class CreateClusterRequestMessage {
    private Long clusterId;
    private List<Long> nodeIdList;
    private Long loadBalancerId;
    private int serverCreateCount;
    private String clusterName;
    private String loadBalancerName;
    private String policy;

}
