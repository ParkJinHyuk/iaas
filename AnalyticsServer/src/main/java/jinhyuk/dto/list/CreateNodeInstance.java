package jinhyuk.dto.list;

import jinhyuk.dto.data.ServerInstance;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CreateNodeInstance {

    private Long clusterId;
    private List<Long> nodeIdList;
    private String loadBalancerInstanceNo;
    private List<ServerInstance> serverInstanceList;

    public CreateNodeInstance(Long clusterId, String loadBalancerInstanceNo, List<Long> nodeIdList, List<ServerInstance> serverInstanceList) {
        this.clusterId = clusterId;
        this.loadBalancerInstanceNo = loadBalancerInstanceNo;
        this.nodeIdList = nodeIdList;
        this.serverInstanceList = serverInstanceList;
    }

}
