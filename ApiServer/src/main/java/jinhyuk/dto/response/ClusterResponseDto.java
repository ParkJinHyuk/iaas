package jinhyuk.dto.response;

import jinhyuk.domain.clusters.Clusters;
import jinhyuk.domain.loadbalancers.LoadBalancers;
import jinhyuk.domain.nodes.Nodes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ClusterResponseDto {

    private Long clusterId;

    private Integer nodeCnt;

    private String policy;

    private List<NodeResponseDto> nodesList;

    private LoadBalancerResponseDto loadBalancer;

    @Builder
    public ClusterResponseDto(Long  clusterId, Integer nodeCnt, String policy, List<Nodes> nodesList, LoadBalancers loadBalancers) {
        this.clusterId = clusterId;
        this.nodeCnt = nodeCnt;
        this.policy = policy;

        List<NodeResponseDto> nodeResponseDtoList = new ArrayList<>();
        for(Nodes nodes : nodesList) {
            nodeResponseDtoList.add(new NodeResponseDto().builder()
                    .id(nodes.getId())
                    .serverName(nodes.getServerName())
                    .serverInstanceNo(nodes.getServerInstanceNo())
                    .privateIp(nodes.getPrivateIp())
                    .status(nodes.getStatus())
                    .build());
        }
        this.nodesList = nodeResponseDtoList;

        this.loadBalancer = new LoadBalancerResponseDto().builder()
                .id(loadBalancers.getId())
                .loadBalancerName(loadBalancers.getLoadBalancerName())
                .loadBalancerInstanceNo(loadBalancers.getLoadBalancerInstanceNo())
                .build();
    }
}
