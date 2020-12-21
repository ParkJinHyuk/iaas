package jinhyuk.dto.message;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DeleteNodeRequestMessage {
    private Long nodeId;
    private Long clusterId;
    private String serverInstanceNo;
    private String loadBalancerInstanceNo;

    public DeleteNodeRequestMessage(Long nodeId, Long clusterId, String serverInstanceNo, String loadBalancerInstanceNo) {
        this.nodeId = nodeId;
        this.clusterId = clusterId;
        this.serverInstanceNo = serverInstanceNo;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
    }
}
