package jinhyuk.service.schedule;


import jinhyuk.service.api.MyApi;
import jinhyuk.domain.clusters.Clusters;
import jinhyuk.domain.nodes.Nodes;
import jinhyuk.domain.nodes.NodesRepository;
import jinhyuk.dto.data.LoadBalancedServerInstance;
import jinhyuk.dto.data.LoadBalancerInstance;
import jinhyuk.dto.data.MetricStatistic;
import jinhyuk.dto.data.ServerInstance;
import jinhyuk.service.api.NCloud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class HealthCheckService {

    private final MyApi myApi;
    private final NCloud nCloud;
    private final NodesRepository nodesRepository;


    // 로드밸런서의 헬스체크 기능을 이용한 헬스체킹
    public boolean checkHealth(LoadBalancerInstance loadBalancerInstance) {
        boolean ret = true;
        for (LoadBalancedServerInstance loadBalancedServerInstance : loadBalancerInstance.getLoadBalancedServerInstanceList()) {
            boolean isNormal = loadBalancedServerInstance.getServerHealthCheckStatusList().get(0).getServerStatus();
            if (!isNormal) {
                ret = false;
                log.info(loadBalancedServerInstance.getServerInstance().getServerName() + " 이 서버에 문제가 있습니다.");
                Nodes nodes = nodesRepository.findByServerInstanceNo(loadBalancedServerInstance.getServerInstance().getServerInstanceNo());
                myApi.deleteNode(nodes.getId());
                myApi.createNode(nodes.getClusters().getId());
            }
        }
        return ret;
    }


    // Monitoring API 이용한 CPU 체킹
    public boolean checkCPU(LoadBalancerInstance loadBalancerInstance) {
        boolean ret = true;
        int minNodeCnt;
        Nodes nodes;
        List<ServerInstance> serverInstanceList = new ArrayList<>();
        List<LoadBalancedServerInstance> loadBalancedServerInstanceList = loadBalancerInstance.getLoadBalancedServerInstanceList();
        for (LoadBalancedServerInstance loadBalancedServerInstance : loadBalancedServerInstanceList) {
            serverInstanceList.add(loadBalancedServerInstance.getServerInstance());
        }


        // CPU 사용량 확인
        List<MetricStatistic> metricStatisticList = nCloud.getMetricStatisticList(serverInstanceList).getMetricStatisticList();
        double CPUUtilization = 0;
        for (MetricStatistic metricStatistic : metricStatisticList) {
            CPUUtilization += Double.parseDouble(metricStatistic.getMetricDataList().get(0).getAverage());
        }
        CPUUtilization /= metricStatisticList.size();
        log.info(loadBalancerInstance.getLoadBalancerName() + " : 평균 CPUUtilization : " + CPUUtilization);


        // SCALE-OUT 체크
        if (CPUUtilization > 50) {
            log.info("CPU 사용량이 높아 스케일 아웃을 진행합니다.");
            ret = false;
            nodes = nodesRepository.findByServerInstanceNo(metricStatisticList.get(0).getInstanceNo());
            myApi.createNode(nodes.getClusters().getId());
        }


        // SCALE-IN 체크
        else if (CPUUtilization < 1) {
            nodes = nodesRepository.findByServerInstanceNo(metricStatisticList.get(0).getInstanceNo());
            Clusters clusters = nodes.getClusters();
            minNodeCnt = clusters.getMinNodeCnt();
            if (minNodeCnt < serverInstanceList.size()) {
                log.info("CPU 사용량이 낮아 스케일 인을 진행합니다.");
                myApi.deleteNode(nodes.getId());
            }
        }

        return ret;
    }
}
