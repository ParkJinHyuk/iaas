package jinhyuk.service.remote;

import com.jcraft.jsch.*;
import jinhyuk.domain.clusters.Clusters;
import jinhyuk.domain.clusters.ClustersRepository;
import jinhyuk.dto.data.ServerInstance;
import jinhyuk.domain.nodes.Nodes;
import jinhyuk.domain.nodes.NodesRepository;
import jinhyuk.dto.list.CreateClusterInstance;
import jinhyuk.dto.list.CreateNodeInstance;
import jinhyuk.dto.message.UpdateClusterRequestMessage;
import jinhyuk.service.api.NCloud;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class Remoter {

    private final NCloud nCloud;
    private final RemoterTasker remoterTasker;


    // 원격 명령을 통한 클러스터 구성
    public void createCluster(CreateClusterInstance createClusterInstance) throws JSchException, IOException, SftpException {
        List<ServerInstance> serverInstanceList = createClusterInstance.getServerInstanceList();
        for(int i = 0; i < serverInstanceList.size(); i++) {
            String rootPassword = nCloud.getRootPassword(serverInstanceList.get(i));
            Session session = remoterTasker.openSession(serverInstanceList.get(i), rootPassword);
            if(i == 0) {
                remoterTasker.setPolicy(createClusterInstance.getPolicy(), serverInstanceList.size(), session);
            } else {
                remoterTasker.writeHosts(serverInstanceList, session);
                remoterTasker.joinCluster(serverInstanceList.get(0).getServerName(), session);
            }
            session.disconnect();
        }
    }

}