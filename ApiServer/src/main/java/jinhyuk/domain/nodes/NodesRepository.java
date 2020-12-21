package jinhyuk.domain.nodes;

import jinhyuk.domain.clusters.Clusters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface NodesRepository extends JpaRepository<Nodes, Long> {
    Nodes findByServerName(String serverName);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Nodes findFirstByClustersAndStatus(Clusters clusters, String status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select n from Nodes n where n.id = :id")
    Optional<Nodes> findByIdForUpdate(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select n from Nodes n where n.id = :id")
    Optional<Nodes> findByIdForShare(@Param("id") Long id);
}