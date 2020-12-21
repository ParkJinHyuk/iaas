package jinhyuk.dto.message;

import jinhyuk.domain.clusters.Clusters;
import jinhyuk.dto.request.CreateClusterRequestDto;
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


    public CreateClusterRequestMessage(Long clusterId, List<Long> nodeIdList, Long loadBalancerId, CreateClusterRequestDto requestDto) {
        this.clusterId = clusterId;
        this.nodeIdList = nodeIdList;
        this.loadBalancerId = loadBalancerId;
        this.serverCreateCount = requestDto.getServerCreateCount();
        this.clusterName = requestDto.getClusterName();
        this.loadBalancerName = requestDto.getLoadBalancerName();
        this.policy = requestDto.getPolicy();
    }
}
