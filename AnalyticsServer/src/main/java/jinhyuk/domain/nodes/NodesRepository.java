package jinhyuk.domain.nodes;

import jinhyuk.domain.clusters.Clusters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface NodesRepository extends JpaRepository<Nodes, Long> {

    Nodes findByServerInstanceNo(String serverInstanceNo);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Nodes> findAllByClustersAndStatus(Clusters clusters, String status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Nodes> findALLByClustersAndStatus(Clusters clusters, String status);

}