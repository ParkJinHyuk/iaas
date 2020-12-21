package jinhyuk.consumer;

import jinhyuk.dto.message.*;
import jinhyuk.service.create.ServerCreatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
@RabbitListener(queues = "ha.Creator", concurrency = "10")
public class CreatorConsumer {

    private final ServerCreatorService serverCreatorService;

    @RabbitHandler
    public void consume(CreateClusterRequestMessage message) {
        log.info("클러스터를 생성하라는 메시지를 받았습니다.");
        serverCreatorService.createCluster(message);
    }

    @RabbitHandler
    public void consume(CreateNodeRequestMessage message) {
        log.info("클러스터에 노드를 추가하라는 메시지를 받았습니다.");
        serverCreatorService.createNode(message);
    }

    @RabbitHandler
    public void consume(DeleteClusterRequestMessage message) {
        log.info("클러스터를 삭제하라는 메시지를 받았습니다.");
        serverCreatorService.deleteCluster(message);
    }

    @RabbitHandler
    public void consume(DeleteNodeRequestMessage message) {
        log.info("노드를 삭제하라는 메시지를 받았습니다.");
        serverCreatorService.deleteNode(message);
    }

}
