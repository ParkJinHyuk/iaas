package jinhyuk.domain.nodes;

import jinhyuk.domain.clusters.Clusters;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Nodes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String serverName;

    @Column(unique = true)
    private String serverInstanceNo;

    @Column(unique = true)
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

    public void setClusters(Clusters clusters) {
        this.clusters = clusters;
    }

}
