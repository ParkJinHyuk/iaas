package jinhyuk.service.schedule;

import jinhyuk.dto.list.CreateClusterInstance;
import jinhyuk.dto.list.DeleteClusterInstance;
import jinhyuk.dto.list.DeleteNodeInstance;
import jinhyuk.dto.list.CreateNodeInstance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class StatusCheckScheduler {

    private final StatusCheckService statusCheckService;

    private final Queue<CreateClusterInstance> checkQueue = new LinkedList<>();


    // 프로세스 상태 체크
    @Scheduled(fixedDelay = 30000, initialDelay = 10000)
    private void checkInstance() {
        log.info("상태 체킹을 시작합니다.");

        // CREATE CLUSTER 요청에 따른 서버들이 켜졌는지 체크
        while(!checkQueue.isEmpty()) {
            CreateClusterInstance createClusterInstance = checkQueue.peek();
            if(statusCheckService.isRun(createClusterInstance)) {
                checkQueue.remove();
            } else {
                break;
            }
        }
    }


    public synchronized void addCreateClusterInstance(CreateClusterInstance createClusterInstance) {
        checkQueue.offer(createClusterInstance);
        log.info("서버가 켜짐을 확인하는 목록에 서버 리스트를 추가하였습니다.");
    }

}





