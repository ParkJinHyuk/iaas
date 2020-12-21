package jinhyuk.domain.clusters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface ClustersRepository extends JpaRepository<Clusters, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Clusters c where c.id = :id")
    Optional<Clusters> findByIdForShare(@Param("id") Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Clusters c where c.id = :id")
    Optional<Clusters> findByIdForUpdate(@Param("id") Long id);
}