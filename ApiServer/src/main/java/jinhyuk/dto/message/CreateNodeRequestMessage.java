package jinhyuk.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class CreateNodeRequestMessage {

    private Long clusterId;
    private String loadBalancerInstanceNo;
    private List<Long> nodeIdList;
    private String clusterName;
    private int serverCreateStartNo;
    private int serverCreateCount;

    public CreateNodeRequestMessage(Long clusterId, String loadBalancerInstanceNo, List<Long> nodeIdList, String clusterName, int serverCreateStartNo, int serverCreateCount) {
        this.clusterId = clusterId;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
        this.nodeIdList = nodeIdList;
        this.clusterName = clusterName;
        this.serverCreateStartNo = serverCreateStartNo;
        this.serverCreateCount = serverCreateCount;
    }
}
