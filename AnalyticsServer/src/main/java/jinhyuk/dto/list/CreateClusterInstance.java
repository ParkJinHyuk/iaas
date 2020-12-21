package jinhyuk.dto.list;

import jinhyuk.dto.data.ServerInstance;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CreateClusterInstance {

    private Long clusterId;
    private List<Long> nodeIdList;
    private Long loadBalancerId;
    private String loadBalancerName;
    private String policy;
    private List<ServerInstance> serverInstanceList;

    public CreateClusterInstance(Long clusterId, List<Long> nodeIdList, Long loadBalancerId, String loadBalancerName, String policy, List<ServerInstance> serverInstanceList) {
        this.clusterId = clusterId;
        this.nodeIdList = nodeIdList;
        this.loadBalancerId = loadBalancerId;
        this.loadBalancerName = loadBalancerName;
        this.policy = policy;
        this.serverInstanceList = serverInstanceList;
    }
}
