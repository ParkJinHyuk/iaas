package jinhyuk.service.schedule;

import jinhyuk.dto.data.ServerInstance;
import jinhyuk.dto.list.CreateClusterInstance;
import jinhyuk.service.api.NCloud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusCheckService {

    private final NCloud nCloud;
    private static final String RUN = "RUN";
    private static final String NSTOP = "NSTOP";


    public boolean isRun(CreateClusterInstance createClusterInstance) {
        List<ServerInstance> serverInstanceList = nCloud.getServerInstanceList(createClusterInstance.getServerInstanceList()).getServerInstanceList());
        if(isCheck(RUN, serverInstanceList)
            return true;
        return false;
    }

    // 노드들의 상태 체크 알고리즘
    private boolean isCheck(String status, List<ServerInstance> serverInstanceList) {
        boolean isCheck = true;
        for (ServerInstance serverInstance : serverInstanceList) {
            String code = serverInstance.getServerInstanceStatus().getCode();
            if (!code.equals(status)) {
                isCheck = false;
                break;
            }
        }
        return isCheck;
    }


}
