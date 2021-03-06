package jinhyuk.domain.loadbalancers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoadBalancersRepository extends JpaRepository<LoadBalancers, Long> {

}