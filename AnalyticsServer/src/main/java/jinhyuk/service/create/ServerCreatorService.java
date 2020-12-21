package jinhyuk.service.create;

import jinhyuk.domain.nodes.Nodes;
import jinhyuk.domain.nodes.NodesRepository;
import jinhyuk.dto.data.ServerInstance;
import jinhyuk.dto.message.*;
import jinhyuk.service.api.NCloud;
import jinhyuk.service.schedule.StatusCheckScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ServerCreatorService {

    private final NCloud nCloud;
    private final NodesRepository nodesRepository;
    private final StatusCheckScheduler statusCheckScheduler;

    // NCloud 이용한 서버 생성(클러스터 생성용)
    @Transactional
    public void createServerInstanceAndAddSchedule(CreateClusterRequestMessage message) throws ResourceAccessException {
        List<ServerInstance> serverInstanceList = nCloud.createServerInstances(1).getServerInstanceList();
        updateDatabaseForCreateCluster(serverInstanceList);
        statusCheckScheduler.addCreateClusterInstance(serverInstanceList);
    }

    private void updateDatabaseForCreateCluster(List<ServerInstance> serverInstanceList) {
        List<Long> nodeIdList = message.getNodeIdList();
        for (Long nodeId : nodeIdList) {
            Nodes node = nodesRepository.findById(nodeId);
            node.changeStatus();
        }
    }

}
