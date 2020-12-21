package jinhyuk.domain.nodes;

import jinhyuk.domain.clusters.Clusters;
import jinhyuk.dto.data.ServerInstance;
import jinhyuk.dto.list.DeleteNodeInstance;
import jinhyuk.dto.list.CreateNodeInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Entity
public class Nodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverName;

    private String serverInstanceNo;

    private String privateIp;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clusters clusters;


    @Builder
    public Nodes(String serverName, String serverInstanceNo, String privateIp, String status) {
        this.serverName = serverName;
        this.serverInstanceNo = serverInstanceNo;
        this.privateIp = privateIp;
        this.status = status;
    }

    public ServerInstance toServerInstance() {
        ServerInstance serverInstance = new ServerInstance();
        serverInstance.setServerName(serverName);
        serverInstance.setServerInstanceNo(serverInstanceNo);
        serverInstance.setPrivateIp(privateIp);
        return serverInstance;
    }

    public CreateNodeInstance toCreateNodeInstance(Long clusterId, String loadBalancerInstanceNo) {
        List<Long> nodesIdList = new ArrayList<>();
        nodesIdList.add(this.id);
        List<ServerInstance> serverInstanceList = new ArrayList<>();
        serverInstanceList.add(this.toServerInstance());
        return new CreateNodeInstance(clusterId, loadBalancerInstanceNo, nodesIdList, serverInstanceList);
    }

    public DeleteNodeInstance toDeleteNodeInstance(String loadBalancerInstanceNo) {
        return new DeleteNodeInstance(id, serverInstanceNo, loadBalancerInstanceNo);
    }


    public void update(String status, String serverInstanceNo, String privateIp) {
        this.status = status;
        this.serverInstanceNo = serverInstanceNo;
        this.privateIp = privateIp;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

}
