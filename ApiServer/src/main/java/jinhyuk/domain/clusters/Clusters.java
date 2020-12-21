package jinhyuk.domain.clusters;

import jinhyuk.domain.loadbalancers.LoadBalancers;
import jinhyuk.domain.nodes.Nodes;
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

    @Column(unique = true)
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

    public void addNode(Nodes nodes) {
        nodesList.add(nodes);
        this.serverCreateCount++;
        this.serverCreateStartNo++;
        nodes.setClusters(this);
    }

    public void deleteNode(Nodes nodes) {
        this.getNodesList().remove(nodes);
        this.serverCreateCount--;
    }

    public void setLoadBalancers(LoadBalancers loadBalancers) { this.loadBalancers = loadBalancers; }

    public void updatePolicy(String policy) {
        this.policy = policy;
    }

    public void changeStatus(String status) {
        this.status = status;
    }

}
