package jinhyuk.service;

import jinhyuk.domain.clusters.Clusters;
import jinhyuk.domain.clusters.ClustersRepository;
import jinhyuk.domain.loadbalancers.LoadBalancers;
import jinhyuk.domain.nodes.Nodes;
import jinhyuk.domain.nodes.NodesRepository;
import jinhyuk.dto.message.CreateClusterRequestMessage;
import jinhyuk.dto.message.DeleteClusterRequestMessage;
import jinhyuk.dto.message.CreateNodeRequestMessage;
import jinhyuk.dto.message.UpdateClusterRequestMessage;
import jinhyuk.dto.request.CreateClusterRequestDto;
import jinhyuk.dto.request.CreateNodeRequestDto;
import jinhyuk.dto.request.UpdateClusterRequestDto;
import jinhyuk.dto.response.ClusterResponseDto;
import jinhyuk.rabbitmq.Publisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClustersService {

    private final Publisher publisher;
    private final NodesRepository nodesRepository;
    private final ClustersRepository clustersRepository;

    @Transactional
    public void publishMessage(CreateClusterRequestDto requestDto) {
        updateDatabaseForCreateClusterRequest(requestDto);
        publisher.publishToCreator(message);
    }

    private void updateDatabaseForCreateClusterRequest(CreateClusterRequestDto requestDto) {
        Clusters clusters = clustersRepository.save(requestDto.toEntity());
        List<Long> nodeIdList = new ArrayList<>();
        int requestServerCount = requestDto.getServerCreateCount();
        for(int i = 0; i < requestServerCount; i++) {
            Nodes nodes = nodesRepository.save(Nodes.builder()
                    .status("INIT")
                    .build());
            clusters.addNode(nodes);
            nodeIdList.add(nodes.getId());
        }
        LoadBalancers loadBalancers = loadBalancersRepository.save(LoadBalancers.builder()
                .loadBalancerName(requestDto.getLoadBalancerName())
                .build());
        clusters.setLoadBalancers(loadBalancers);
    }

}
