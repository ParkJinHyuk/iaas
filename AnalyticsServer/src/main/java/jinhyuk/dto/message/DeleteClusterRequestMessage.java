package jinhyuk.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@Getter
public class DeleteClusterRequestMessage {

    private Long clusterId;
    private List<Long> nodeIdList;
    private List<String> serverInstanceNoList;
    private String loadBalancerInstanceNo;

    public DeleteClusterRequestMessage(Long clusterId, List<Long> nodeIdList, List<String> serverInstanceNoList, String loadBalancerInstanceNo) {
        this.clusterId = clusterId;
        this.nodeIdList = nodeIdList;
        this.serverInstanceNoList = serverInstanceNoList;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
    }

}
