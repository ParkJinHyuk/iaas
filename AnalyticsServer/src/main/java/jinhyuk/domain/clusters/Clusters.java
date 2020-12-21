package jinhyuk.domain.clusters;

import jinhyuk.domain.loadbalancers.LoadBalancers;
import jinhyuk.domain.nodes.Nodes;
import jinhyuk.dto.data.ServerInstance;
import jinhyuk.dto.list.CreateClusterInstance;
import jinhyuk.dto.list.DeleteClusterInstance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Clusters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer serverCreateCount;

    private String clusterName;

    private Integer serverCreateStartNo;

    private String policy;

    private Integer minNodeCnt;

    private String status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LoadBalancers loadBalancers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "clusters")
    private List<Nodes> nodesList = new ArrayList<>();


    @Builder
    public Clusters(Integer serverCreateStartNo, Integer serverCreateCount, String clusterName, String policy, Integer minNodeCnt, String status) {
        this.serverCreateStartNo = serverCreateStartNo;
        this.serverCreateCount = serverCreateCount;
        this.clusterName = clusterName;
        this.policy = policy;
        this.minNodeCnt = minNodeCnt;
        this.status = status;
    }

    public CreateClusterInstance toCreateClusterInstance() {
        List<Long> nodeIdList = new ArrayList<>();
        List<ServerInstance> serverInstanceList = new ArrayList<>();
        for(Nodes nodes : nodesList) {
            nodeIdList.add(nodes.getId());
            serverInstanceList.add(nodes.toServerInstance());
        }
        return new CreateClusterInstance(id, nodeIdList, loadBalancers.getId(), loadBalancers.getLoadBalancerName(), policy, serverInstanceList);
    }

    public DeleteClusterInstance toDeleteClusterInstance() {
        List<String> serverInstanceNoList = new ArrayList<>();
        for(Nodes nodes : nodesList) {
            serverInstanceNoList.add(nodes.getServerInstanceNo());
        }
        return new DeleteClusterInstance(id, serverInstanceNoList);
    }

    public void changeStatus(String status) {
        this.status = status;
    }

}
