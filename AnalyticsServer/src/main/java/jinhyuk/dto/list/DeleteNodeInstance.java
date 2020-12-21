package jinhyuk.dto.list;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class DeleteNodeInstance {

    private Long nodeId;
    private String serverInstanceNo;
    private String loadBalancerInstanceNo;

    public DeleteNodeInstance(Long nodeId, String serverInstanceNo, String loadBalancerInstanceNo) {
        this.nodeId = nodeId;
        this.serverInstanceNo = serverInstanceNo;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
    }

}
