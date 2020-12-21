package jinhyuk.service.create;


import jinhyuk.domain.nodes.NodesRepository;
import jinhyuk.dto.data.LoadBalancerInstance;
import jinhyuk.dto.list.CreateClusterInstance;
import jinhyuk.service.api.NCloud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoadBalancerCreatorService {

    private final NCloud nCloud;
    private final NodesRepository nodesRepository;
    private final HealthCheckScheduler healthCheckScheduler;


    // NCloud 이용한 로드밸런서 생성
    @Transactional
    public void createLoadBalancer(CreateClusterInstance createClusterInstance) {
        LoadBalancerInstance loadBalancerInstance = nCloud.createLoadBalancerInstance();
        updateDatabaseForCreateLoadBalancer();
        healthCheckScheduler.addHealthCheck(loadBalancerInstance.getLoadBalancerInstanceNo());
    }


    private void updateDatabaseForCreateLoadBalancer() {
        //
    }

}
